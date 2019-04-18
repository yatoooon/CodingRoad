package com.yatoooon.design_patterns.factory;

import timber.log.Timber;

public class Circle implements Shape {
    @Override
    public void draw() {
        Timber.d("draw: Circle");
    }
}
