package com.yatoooon.generics.shop;

import com.yatoooon.generics.model.Apple;

public class AppleShop implements Shop<Apple> {
    @Override
    public Apple buy(float money) {
        return new Apple();
    }

    @Override
    public float refund(Apple apple) {
        return apple.getPrice();
    }
}
