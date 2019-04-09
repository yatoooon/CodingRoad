package com.yatoooon.annotation_lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  //纯反射方式
@Target(ElementType.FIELD)
public @interface BindView {
    //    int id();
    int value();
}
