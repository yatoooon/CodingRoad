package com.yatoooon.kotlin;

public class Java {

    public static void main(String[] args) {
        KtModel ktModel = new KtModel(12, "yatoooon");
        Integer age = ktModel.age;
        String sName = StaticFileAndMethodKt.sName;
        StaticFileAndMethod1.INSTANCE.getName();
        staticFileAndMethod2.Companion.getName();
        StaticFileAndMethod1.getSjvmName();
        staticFileAndMethod2.getSjvmName();
    }
}
