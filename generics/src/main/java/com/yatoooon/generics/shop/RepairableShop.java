package com.yatoooon.generics.shop;

public interface RepairableShop<T> extends Shop<T> {
    void repair(T t);
}
