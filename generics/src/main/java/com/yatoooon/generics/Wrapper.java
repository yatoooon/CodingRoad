package com.yatoooon.generics;

public class Wrapper<T> {
    T instance;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }
}
