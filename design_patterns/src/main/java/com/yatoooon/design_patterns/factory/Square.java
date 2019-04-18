package com.yatoooon.design_patterns.factory;

import timber.log.Timber;

public class Square implements Shape {
    @Override
    public void draw() {
        Timber.d("draw: Square");

    }
}
