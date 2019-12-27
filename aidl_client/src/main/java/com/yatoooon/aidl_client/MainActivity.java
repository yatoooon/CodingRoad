package com.yatoooon.aidl_client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yatoooon.aidl.IDemandListener;
import com.yatoooon.aidl.IDemandManager;
import com.yatoooon.aidl.MessageBean;

public class MainActivity extends AppCompatActivity {
    private IDemandManager mIDemandManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ServiceConnection mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                IDemandListener.Stub listener = new IDemandListener.Stub() {
                    @Override
                    public void onDemandReceiver(MessageBean msg) throws RemoteException {
                        //该方法运行在Binder线程池中，是非ui线程
                        Log.d("aidl_client", msg.getContent());

                    }
                };
                mIDemandManager = IDemandManager.Stub.asInterface(iBinder);
                try {
                    mIDemandManager.registerListener(listener);
                    mIDemandManager.setDemandIn(new MessageBean("我绑定上你了", 5));
                    Log.d("aidl_client", mIDemandManager.getDemand().toString());
                    mIDemandManager.setDemandIn(new MessageBean("初次见面，失礼失礼", 1));
                    mIDemandManager.setDemandOut(new MessageBean("测试", 1));  //这里需要服务器发
                    mIDemandManager.setDemanInOut(new MessageBean("测试", 1));  //这个可以两边发

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent = new Intent();
        intent.setAction("com.test.aidl");
        intent.setPackage("com.yatoooon.aidl");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
}
