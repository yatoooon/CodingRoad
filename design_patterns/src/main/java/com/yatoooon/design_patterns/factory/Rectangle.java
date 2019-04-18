package com.yatoooon.design_patterns.factory;

import timber.log.Timber;

public class Rectangle  implements Shape {
    @Override
    public void draw() {
        Timber.d("draw: Rectangle");
    }
}
