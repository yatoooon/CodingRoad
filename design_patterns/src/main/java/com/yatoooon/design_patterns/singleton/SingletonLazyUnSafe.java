package com.yatoooon.design_patterns.singleton;

public class SingletonLazyUnSafe {

    private static SingletonLazyUnSafe singletonLazySafe;

    private SingletonLazyUnSafe() {

    }

    public  static SingletonLazyUnSafe getInstance() {
        if (singletonLazySafe == null) {
            singletonLazySafe = new SingletonLazyUnSafe();
        }
        return singletonLazySafe;
    }


}
