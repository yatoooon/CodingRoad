package com.yatoooon.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import timber.log.Timber;

public class SimulateHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Android Handler 机制  :在某个指定的运行中的线程上执行代码.

        CustomerThread customerThread = new CustomerThread();
        customerThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        customerThread.handler.post(new Runnable() {
            @Override
            public void run() {
                Timber.d("在运行中的线程上执行代码了");
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        customerThread.handler.quit();

    }


    class CustomerThread extends Thread {
        Handler handler = new Handler();

        @Override
        public void run() {
            handler.looper.loop();
        }
    }

    class Looper {
        private Runnable task;
        private boolean quit = false;

        public void setTask(Runnable task) {
            this.task = task;
        }

        synchronized boolean quit() {
            return true;
        }


        public void loop() {
            while (!quit) {
                synchronized (this) {
                    if (task != null) {
                        task.run();
                        task = null;
                    }
                }
                Timber.d("线程正在运行........");
            }
        }
    }

    class Handler {
        Looper looper;

        Handler() {
            this.looper = new Looper();
        }

        void post(Runnable runnable) {
            looper.setTask(runnable);
        }

        void quit() {
            looper.quit();
        }
    }
}
