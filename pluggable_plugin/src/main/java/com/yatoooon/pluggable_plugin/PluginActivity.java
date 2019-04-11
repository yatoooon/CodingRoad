package com.yatoooon.pluggable_plugin;

import android.app.Activity;

public class PluginActivity {
    public PluginActivity() {
    }

    Activity proxyActivity;

    public void setProxyActivity(Activity proxyActivity) {
        this.proxyActivity = proxyActivity;
    }

    public void onCreate() {
        proxyActivity.setContentView(R.layout.activity_plugin);
    }
}
