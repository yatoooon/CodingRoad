package com.yatoooon.annotation_lib_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)//这里改为了source
@Target(ElementType.FIELD)
public @interface BindView {
    //    int id();
    int value();
}
