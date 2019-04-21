package com.yatoooon.design_patterns.proxy;

import timber.log.Timber;

public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void disPlay() {
        Timber.d("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        Timber.d("Loading " + fileName);
    }
}
