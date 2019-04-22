package com.yatoooon.design_patterns.template;

import timber.log.Timber;

public class Football extends Game {

    @Override
    void endPlay() {
        Timber.d("Football Game Finished!");
    }

    @Override
    void initialize() {
        Timber.d("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        Timber.d("Football Game Started. Enjoy the game!");
    }
}
