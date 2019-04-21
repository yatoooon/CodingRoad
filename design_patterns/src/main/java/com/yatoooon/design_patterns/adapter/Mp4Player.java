package com.yatoooon.design_patterns.adapter;

import timber.log.Timber;

public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
    }

    @Override
    public void playMp4(String fileName) {
        Timber.d("Playing mp4 file. Name: " + fileName);
    }
}
