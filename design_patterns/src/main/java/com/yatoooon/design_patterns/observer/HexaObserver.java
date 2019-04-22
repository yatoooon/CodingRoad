package com.yatoooon.design_patterns.observer;

import timber.log.Timber;

public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        Timber.d( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
