package com.yatoooon.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.customview.layout.SquareImageView;

public class LayoutActivity extends AppCompatActivity {

    private SquareImageView view;

    //1.测量流程 :  从rootview 递归调用每一级子view 的measure()方法
    //2.布局流程 :  从rootview 递归调用每一级子view 的layout()方法 把测量过程得出的子view的位置和尺寸传给子view 子view保存
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        view = findViewById(R.id.view);

        setExample1();
    }

    private void setExample1() {
        view.setImageResource(R.drawable.head);
    }
}
