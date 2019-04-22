package com.yatoooon.design_patterns.template;

import timber.log.Timber;

public class Cricket extends Game {

    @Override
    void endPlay() {
        Timber.d( "Cricket Game Finished!");
    }

    @Override
    void initialize() {
        Timber.d( "Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        Timber.d( "Cricket Game Started. Enjoy the game!");
    }
}
