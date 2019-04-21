package com.yatoooon.design_patterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.design_patterns.builder.ActualBuilder;
import com.yatoooon.design_patterns.builder.Director;
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

        //2.单例模式
        SingletonLazyUnSafe.getInstance();
        SingletonLazySafe.getInstance();
        SingletonHungry.getInstance();
        SingletonDoubleCheck.getInstance();
        SingletonStaticInnerClass.getInstance();

        //3.构造者模式
        ActualBuilder actualBuilder = new ActualBuilder();
        Director director = new Director(actualBuilder);
        director.construct1();
        String result = actualBuilder.getResult();
        Timber.d("ActualBuilder" + result);


    }
}
