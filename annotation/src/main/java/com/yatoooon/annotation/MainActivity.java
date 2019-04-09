package com.yatoooon.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.yatoooon.annotation_lib.Binding;
import com.yatoooon.annotation_lib_annotations.BindView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_hello)
    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);
        tvHello.setText("你好");
    }
}
