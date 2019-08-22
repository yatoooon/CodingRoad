package com.yatoooon.generics.model;

import java.util.List;

public class Apple implements Fruit {
    private float price;

    public float getPrice() {
        return price;
    }

    @Override
    public float getWeight() {
        return 1;
    }


    public void addMeToList(List<? super Apple> list) {
        list.add(this);
    }

}
