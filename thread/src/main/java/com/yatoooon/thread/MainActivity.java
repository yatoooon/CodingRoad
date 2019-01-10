package com.yatoooon.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import timber.log.Timber;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setExample1();   //最简单的Thread
//        setExample2();   //最简单的Runnable
//        setExample3();   //ThreadFactory
//        setExample4();   //Executor 线程池
//        setExample5();   //Callable Future
//        setExample6();   //引入线程同步  和  monitor
//        setExample7();   //volatile   AtomicInteger...
        setExample8();    //Lock


    }


    private int l = 0;

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    private void count() {
        writeLock.lock();
        try {
            x++;
        } finally {
            writeLock.unlock();
        }
    }

    private void print(int time) {
        readLock.lock();
        try {
            for (int i = 0; i < time; i++) {
                Timber.d("print" + x);
            }
        } finally {
            readLock.unlock();
        }
    }

    Lock lock = new ReentrantLock();

    private void setExample8() {
        lock.lock();   //一般不会直接这么用  常用的是  ReadWriteLock
        try {
            x++;
        } finally {
            lock.unlock();
        }
    }

    volatile int v = 0;
    volatile AtomicInteger A = new AtomicInteger(0);

    private void setExample7() {
        //volatile保证可见性，不保证原子性，部分保证有序性（仅保证被volatile修饰的变量）。
        //java.util.concurrent.atomic 下面的原子操作类 AtomicInteger等   保证原子性
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    v++;
                    A.getAndIncrement();
                }
                Timber.d(String.valueOf("countV" + v + "       countA" + A.get()));
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    v++;
                    A.getAndIncrement();
                }
                Timber.d(String.valueOf("countV" + v + "        countA" + A.get()));
            }
        }.start();
    }


    private Object monitor1 = new Object();
    private Object monitor2 = new Object();

    private int x = 0;
    private int y = 0;
    private int z = 0;

    private void count2(int newValue) {  //线程同步第三种简单写法添加两个monitor      这样可以让两个线程分别访问这两个方法
        synchronized (monitor1) {
            x = newValue;
            y = newValue;
            if (x != y) {
                Timber.d("x: " + x + ", y:" + y);
            }
        }

    }

    private void countZ2() {
        synchronized (monitor2) {
            z++;
        }
    }

    private synchronized void count(int newValue) {  //线程同步第一种简单写法     count和countZ都被一个线程占有了
        x = newValue;
        y = newValue;
        if (x != y) {
            Timber.d("x: " + x + ", y:" + y);
        }
    }

    private void countZ() {
        synchronized (this) {  //线程同步第二种简单写法
            z++;
        }
    }

    private void setExample6() {
        //synchronized 的本质:1.保证方法内部或者代码块内部资源(数据)的互斥访问.同一时间,由同一个monitor监视的代码,最多只能有一个线程在访问.
        //                   2.保证线程之间对监视资源的数据同步.任何线程在获取到monitor后的第一时间,会先将共享内存中的数据复制到自己的缓存中;任何线程在释放monitor的第一时间,会先将缓存中的数据复制到共享内存中.


        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count(i);
                    countZ();
                }
                Timber.d(String.valueOf("countZ" + z));
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count(i);
                    countZ();
                }
                Timber.d(String.valueOf("countZ" + z));
            }
        }.start();
    }

    private void setExample5() {
        Callable<String> callable = new Callable<String>() {   //带返回值
            @Override
            public String call() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Callable End";
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(callable);
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (submit.isDone()) {
                try {
                    String result = submit.get(); //阻塞  需要判断是否isDone
                    Timber.d(result);
                    break;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void setExample4() {
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
        ExecutorService executorService = new ThreadPoolExecutor(
                NUMBER_OF_CORES
                , NUMBER_OF_CORES * 2
                , KEEP_ALIVE_TIME
                , KEEP_ALIVE_TIME_UNIT, taskQueue
                , new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("自定义线程名字");
                return thread;
            }
        }
                , new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Timber.d("ThreadPoolExecutor  " + Thread.currentThread().getName() + "run");
            }
        };
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

        //系统也给提供了几种常用的线程池   不过不推荐使用这几种     阿里巴巴手册上写着   通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
//        Executors.newCachedThreadPool();
//        Executors.newFixedThreadPool();
//        Executors.newScheduledThreadPool();


    }

    private void setExample3() {
        ThreadFactory threadFactory = new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Timber.d("ThreadFactory" + Thread.currentThread().getName() + "run");
            }
        };
        threadFactory.newThread(runnable).start();
        threadFactory.newThread(runnable).start();
    }

    private void setExample2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Timber.d("Runnable run");
            }
        };
        new Thread(runnable).start();
    }

    private void setExample1() {
        new Thread() {
            @Override
            public void run() {
                Timber.d("Thread run");
            }
        }.start();
    }
}
