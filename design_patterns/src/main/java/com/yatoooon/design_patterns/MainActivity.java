package com.yatoooon.design_patterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.design_patterns.adapter.AudioPlayer;
import com.yatoooon.design_patterns.builder.ActualBuilder;
import com.yatoooon.design_patterns.builder.Director;
import com.yatoooon.design_patterns.combination.Employee;
import com.yatoooon.design_patterns.factory.ShapeFactory;
import com.yatoooon.design_patterns.observer.BinaryObserver;
import com.yatoooon.design_patterns.observer.HexaObserver;
import com.yatoooon.design_patterns.observer.OctalObserver;
import com.yatoooon.design_patterns.observer.Subject;
import com.yatoooon.design_patterns.proxy.Image;
import com.yatoooon.design_patterns.proxy.ProxyImage;
import com.yatoooon.design_patterns.responsibilitychain.AbstractLogger;
import com.yatoooon.design_patterns.responsibilitychain.ConsoleLogger;
import com.yatoooon.design_patterns.responsibilitychain.DebugLogger;
import com.yatoooon.design_patterns.responsibilitychain.ErrorLogger;
import com.yatoooon.design_patterns.singleton.*;
import com.yatoooon.design_patterns.strategy.Context;
import com.yatoooon.design_patterns.strategy.OperationAdd;
import com.yatoooon.design_patterns.strategy.OperationMultiply;
import com.yatoooon.design_patterns.strategy.OperationSubstract;
import com.yatoooon.design_patterns.template.Cricket;
import com.yatoooon.design_patterns.template.Football;
import com.yatoooon.design_patterns.template.Game;
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

        //4.适配器模式
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");

        //5.组合模式
        Employee ceo = new Employee("我是CEO", "CEO", 10000);
        Employee fuzong1 = new Employee("我是副总1", "FUZONG", 5000);
        Employee fuzong2 = new Employee("我是副总2", "FUZONG", 5000);
        Employee chanping1 = new Employee("我是产品1", "CHANPING", 3000);
        Employee chanping2 = new Employee("我是产品2", "CHANPING", 3000);
        Employee xiaoshou1 = new Employee("我是销售1", "XIAOSHOU", 3000);
        Employee xiaoshou2 = new Employee("我是销售2", "XIAOSHOU", 3000);
        ceo.add(fuzong1);
        ceo.add(fuzong2);
        fuzong1.add(chanping1);
        fuzong1.add(chanping2);
        fuzong2.add(xiaoshou1);
        fuzong2.add(xiaoshou2);

        Timber.d(ceo.toString());
        for (Employee employee : ceo.getSubordinates()) {
            Timber.d(employee.toString());
            for (Employee sub : employee.getSubordinates()) {
                Timber.d(sub.toString());
            }
        }

        //6.代理模式
        Image image = new ProxyImage("test.img");
        image.disPlay();
        System.out.println();
        image.disPlay();

        //7.责任链模式
        AbstractLogger logger = new ErrorLogger(AbstractLogger.ERROR).setNextLogger(new DebugLogger(AbstractLogger.DEBUG).setNextLogger(new ConsoleLogger(AbstractLogger.INFO)));
        logger
                .logMessage(AbstractLogger.INFO, "This is an information.")
                .logMessage(AbstractLogger.DEBUG, "This is a debug level information.")
                .logMessage(AbstractLogger.ERROR, "This is an error information.");
        //8.观察者模式
        Subject subject = new Subject();
        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);
        Timber.d("First state change: 15");
        subject.setState(15);
        Timber.d("Second state change: 10");
        subject.setState(10);

        //9.策略模式
        Context context = new Context(new OperationAdd());
        Timber.d("10 + 5 = " + context.executeStrategy(10, 5));
        context = new Context(new OperationSubstract());
        Timber.d("10 - 5 = " + context.executeStrategy(10, 5));
        context = new Context(new OperationMultiply());
        Timber.d("10 * 5 = " + context.executeStrategy(10, 5));

        //10.模板模式
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
