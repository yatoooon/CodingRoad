package com.yatoooon.design_patterns.responsibilitychain;

import timber.log.Timber;

public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
       Timber.d("Standard Console::Logger: " + message);
    }
}
