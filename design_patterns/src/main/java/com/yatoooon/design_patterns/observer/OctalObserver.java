package com.yatoooon.design_patterns.observer;

import timber.log.Timber;

public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        Timber.d(  "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
