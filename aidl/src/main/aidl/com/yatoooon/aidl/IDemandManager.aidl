// IDemandManager.aidl
package com.yatoooon.aidl;
import com.yatoooon.aidl.MessageBean;
import com.yatoooon.aidl.IDemandListener;
// Declare any non-default types here with import statements

interface IDemandManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     MessageBean getDemand();
     void setDemandIn(in MessageBean msg);
     void setDemandOut(out MessageBean msg);
     void setDemanInOut(inout MessageBean msg);
     void registerListener(IDemandListener listener);
     void unregisterListener(IDemandListener listener);
}
