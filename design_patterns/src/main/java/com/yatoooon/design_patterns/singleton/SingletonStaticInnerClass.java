package com.yatoooon.design_patterns.singleton;

public class SingletonStaticInnerClass {

    private static class Holder {
        private static final SingletonStaticInnerClass INSTANCE = new SingletonStaticInnerClass();
    }

    private SingletonStaticInnerClass() {

    }

    public static final SingletonStaticInnerClass getInstance() {
        return Holder.INSTANCE;
    }
}
