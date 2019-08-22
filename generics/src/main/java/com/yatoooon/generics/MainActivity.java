package com.yatoooon.generics;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yatoooon.generics.model.Apple;
import com.yatoooon.generics.model.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<? super Apple> superList = new ArrayList<Fruit>();//? super
        List<? extends Fruit> extendsList = new ArrayList<Apple>();//? extends


        List<Apple> appleList = new ArrayList<Apple>();  ////这里的T是Type Argument 泛型实例化
        appleList.add(new Apple());
        totalWeiget(appleList);
        new Apple().addMeToList(superList);

    }

    float totalWeiget(List<? extends Fruit> fruits) {
        float totalWiget = 0;
        for (Fruit fruit :
                fruits) {
            totalWiget = totalWiget + fruit.getWeight();
        }
        return totalWiget;
    }


    //泛型方法
    @Override
    public <T extends View> T findViewById(@IdRes int id) {
        return getWindow().findViewById(id);
    }



}
