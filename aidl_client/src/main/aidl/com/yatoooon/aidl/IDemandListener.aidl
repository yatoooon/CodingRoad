// IDemandListener.aidl
package com.yatoooon.aidl;
import com.yatoooon.aidl.MessageBean;
// Declare any non-default types here with import statements

interface IDemandListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onDemandReceiver(in MessageBean msg);
}
