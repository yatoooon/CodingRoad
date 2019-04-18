package com.yatoooon.design_patterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.design_patterns.factory.ShapeFactory;
import com.yatoooon.design_patterns.singleton.*;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.工厂模式
        ShapeFactory.getShape(ShapeFactory.TYPE_CIRCLE).draw();
        ShapeFactory.getShape(ShapeFactory.TYPE_RECTANGLE).draw();
        ShapeFactory.getShape(ShapeFactory.TYPE_SQUARE).draw();
        //2/单例模式
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    if (SingletonLazyUnSafe.getInstance() != SingletonLazyUnSafe.getInstance()) {
                        Timber.d("SingletonLazyUnSafe" + "error");
                    }
                    if (SingletonLazySafe.getInstance() != SingletonLazySafe.getInstance()) {
                        Timber.d("SingletonLazySafe" + "error");
                    }
                    if (SingletonHungry.getInstance() != SingletonHungry.getInstance()) {
                        Timber.d("SingletonHungry" + "error");
                    }
                    if (SingletonDoubleCheck.getInstance() != SingletonDoubleCheck.getInstance()) {
                        Timber.d("SingletonDoubleCheck" + "error");
                    }
                    if (SingletonStaticInnerClass.getInstance() != SingletonStaticInnerClass.getInstance()) {
                        Timber.d("SingletonStaticInnerClass" + "error");
                    }
                }
                Timber.d("end........");
            }
        }).start();


    }
}
