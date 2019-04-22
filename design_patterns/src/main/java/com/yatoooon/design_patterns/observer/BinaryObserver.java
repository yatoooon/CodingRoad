package com.yatoooon.design_patterns.observer;

import timber.log.Timber;

public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        Timber.d( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
