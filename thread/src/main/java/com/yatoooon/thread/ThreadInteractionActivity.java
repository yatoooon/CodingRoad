package com.yatoooon.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import timber.log.Timber;

public class ThreadInteractionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setExample1();  //interrupt;
//        setExample2();   //wait  notify  notifyAll



    }

    private String sharedString;

    private synchronized void initString() {
        sharedString = "rengwuxian";
//        notify();   万一有多个线程在wait着
        notifyAll();
    }

    private synchronized void printString() {
        while (sharedString == null) {  //注意用while    用if的话  万一唤醒的时候还是不满足条件
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        {
            //逻辑代码
            Timber.d("String: %s", sharedString);
        }

    }

    private void setExample2() {
        final Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printString();
            }
        };

        final Thread thread2 = new Thread() {
            @Override
            public void run() {
//                try {
//                    thread1.join();  //join 插入线程
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Thread.yield();//yield 让同优先级的线程先执行
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initString();
            }
        };


        thread1.start();
        thread2.start();

    }

    private void setExample1() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
//                    isInterrupted();//这个方法不会清除中断状态
                    if (Thread.interrupted()) {  //用一次后这个方法    会清除中断状态

                        return;  //结束线程 但是如果已经过了这里而且线程睡了
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {  //会清除中断状态

                        return;    //在睡的情况下立即中断线程
                    }

                    Timber.d(String.valueOf(i));
                }
            }
        };

        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.stop();//已经弃用   不可控
        thread.interrupt();  //只是将线程置为中断状态    需要与线程配合使用

    }
}
