package com.yatoooon.generics.shop;

import com.yatoooon.generics.model.Fruit;

public interface FruitShop<T extends Fruit> extends Shop<T> {
}

