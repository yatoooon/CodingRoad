package com.yatoooon.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import timber.log.Timber;

public class TouchFeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchfeedback);
        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("view点击了....");
            }
        });
        findViewById(R.id.view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("view2点击了....");
            }
        });
        findViewById(R.id.viewgroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("viewgroup点击了....");
            }
        });
    }
}
