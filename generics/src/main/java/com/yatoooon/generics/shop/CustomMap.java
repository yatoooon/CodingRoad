package com.yatoooon.generics.shop;

public interface CustomMap<K, V> {
    void put(K key, V value);

    V get(K key);
}
