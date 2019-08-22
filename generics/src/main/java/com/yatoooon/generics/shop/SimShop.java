package com.yatoooon.generics.shop;

public interface SimShop<T, C> extends Shop<T> {
    C getSim(String name,float money);
}
