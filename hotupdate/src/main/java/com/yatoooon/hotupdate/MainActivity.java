package com.yatoooon.hotupdate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvHello;
    private File apk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = findViewById(R.id.tv_hello);
        TextView btShowTitle = findViewById(R.id.bt_show_title);
        TextView btHotFix = findViewById(R.id.bt_hotfix);
        TextView btRemoveHotFix = findViewById(R.id.bt_remove_hotfix);
        TextView btKillApp = findViewById(R.id.bt_kill_app);


//        apk = new File(getCacheDir() + "/hotupdate-debug.apk");
        apk = new File(getCacheDir() + "/hotupdate-title.dex");

        btShowTitle.setOnClickListener(this);
        btHotFix.setOnClickListener(this);
        btRemoveHotFix.setOnClickListener(this);
        btKillApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_show_title:
                tvHello.setText(Title.getTitle());
                break;
            case R.id.bt_hotfix:
                if (!apk.exists()) {
                    try {
//                        InputStream is = getAssets().open("hotupdate-debug.apk");
                        InputStream is = getAssets().open("hotupdate-title.dex");
                        int size = is.available();
                        byte[] buffer = new byte[size];
                        is.read(buffer);
                        is.close();


                        FileOutputStream fos = new FileOutputStream(apk);
                        fos.write(buffer);
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                tvHello.setText(Title.getTitle());
                break;
            case R.id.bt_remove_hotfix:
                if (apk.exists()) {
                    apk.delete();
                }
                break;
            case R.id.bt_kill_app:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            default:
                break;
        }
    }
}
