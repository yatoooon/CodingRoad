package com.yatoooon.design_patterns.responsibilitychain;

public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    //责任链中的下一个元素
    protected AbstractLogger nextLogger;

    public AbstractLogger setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
        return this;
    }

    public AbstractLogger logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
        return this;
    }

    abstract protected void write(String message);

}
