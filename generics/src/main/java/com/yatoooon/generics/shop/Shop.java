package com.yatoooon.generics.shop;

public interface Shop<T> { //这里的T是Type parameter 泛型的创建
    T buy(float money);

    float refund(T t);

}
