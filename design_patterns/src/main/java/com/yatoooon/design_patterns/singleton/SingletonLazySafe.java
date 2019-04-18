package com.yatoooon.design_patterns.singleton;

public class SingletonLazySafe {

    private static SingletonLazySafe singletonLazySafe;

    private SingletonLazySafe() {

    }

    public static synchronized SingletonLazySafe getInstance() {
        if (singletonLazySafe == null) {
            singletonLazySafe = new SingletonLazySafe();
        }
        return singletonLazySafe;
    }
}
