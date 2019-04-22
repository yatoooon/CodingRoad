package com.yatoooon.design_patterns.responsibilitychain;

import timber.log.Timber;

public class DebugLogger extends AbstractLogger {
    public DebugLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
       Timber.d("Debug::Logger: " + message);
    }
}
