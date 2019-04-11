package com.yatoooon.pluggable_plugin;

import android.app.Activity;
import android.content.res.Resources;
import android.widget.TextView;

public class PluginActivity {
    public PluginActivity() {
    }

    Activity proxyActivity;

    public void setProxyActivity(Activity proxyActivity) {
        this.proxyActivity = proxyActivity;
    }

    public void onCreate() {
        proxyActivity.setContentView(R.layout.activity_plugin);
        Resources resources = proxyActivity.getResources();
        ((TextView) proxyActivity.findViewById(R.id.tv_plugin)).setText(resources.getText(R.string.Test));

    }
}
