package com.yatoooon.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.support.annotation.Nullable;
import android.util.Log;

public class AIDLService extends Service {
    private static final int WHAT_MSG = 10010;

    public AIDLService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("aidl_server", "onbind excute");
//        mHandler.sendEmptyMessageDelayed(WHAT_MSG, 3000);  //定时向客户端发送消息
        return demandManager;
    }


    IDemandManager.Stub demandManager = new IDemandManager.Stub() {
        @Override
        public MessageBean getDemand() throws RemoteException {
            MessageBean demand = new MessageBean("首先，看到我要敬礼", 1);
            return demand;
        }

        @Override
        public void setDemandIn(MessageBean msg) throws RemoteException { //客户端数据流向服务端
            Log.i("aidl_server", "程序员setDemandIn：" + msg.toString());
        }

        @Override
        public void setDemandOut(MessageBean msg) throws RemoteException {//服务端数据流向客户端
            Log.i("aidl_server", "程序员setDemandOut：" + msg.toString());  //msg必定为空
            msg.setContent("我不想听解释，下班前把所有工作都搞好！");
            msg.setLevel(5);
        }

        @Override
        public void setDemanInOut(MessageBean msg) throws RemoteException {//数据互通
            Log.i("aidl_server", "程序员setDemanInOut:" + msg.toString());
            msg.setContent("把用户交互颜色都改成粉色");
            msg.setLevel(3);
        }

        @Override
        public void registerListener(IDemandListener listener) throws RemoteException {
            demandList.register(listener);
        }

        @Override
        public void unregisterListener(IDemandListener listener) throws RemoteException {
            demandList.unregister(listener);
        }


    };
    int count = 0;
    private RemoteCallbackList<IDemandListener> demandList = new RemoteCallbackList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (demandList != null) {
                int nums = demandList.beginBroadcast();
                for (int i = 0; i < nums; i++) {
                    MessageBean messageBean = new MessageBean("biu", count);
                    count++;
                    try {
                        demandList.getBroadcastItem(i).onDemandReceiver(messageBean);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                demandList.finishBroadcast();
            }
            mHandler.sendEmptyMessageDelayed(WHAT_MSG, 3000);
        }
    };
}