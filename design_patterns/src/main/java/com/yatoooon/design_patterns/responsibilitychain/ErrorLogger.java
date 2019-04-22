package com.yatoooon.design_patterns.responsibilitychain;

import timber.log.Timber;

public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        Timber.d("Error Console::Logger: " + message);
    }
}
