package com.yatoooon.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import timber.log.Timber;

import java.util.concurrent.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setExample1();   //最简单的Thread
        setExample2();   //最简单的Runnable
        setExample3();   //ThreadFactory
        setExample4();   //Executor 线程池


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
