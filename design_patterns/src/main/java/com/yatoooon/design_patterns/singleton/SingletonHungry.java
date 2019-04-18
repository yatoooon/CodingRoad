package com.yatoooon.design_patterns.singleton;

public class SingletonHungry {

    private static  SingletonHungry singletonHungry = new SingletonHungry();

    private SingletonHungry() {

    }

    public static SingletonHungry getInstance() {
        return singletonHungry;
    }
}
