package com.yatoooon.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class LayoutActivity extends AppCompatActivity {

//    private SquareImageView view;//1
//    private CircleView view;//2

    //整体   1.测量流程 :  从rootview 递归调用每一级子view 的measure()方法
    //      2.布局流程 :  从rootview 递归调用每一级子view 的layout()方法 把测量过程得出的子view的位置和尺寸传给子view 子view保存


    //个体   1. 开发者在xml文件里写layout_xxx
    //      2. 父view 在onLayout()中  根据开发者在xml中对子view的要求  和自己的可用空间  得出子view的具体要求
    //      3. 子view在onMeasure()中  根据自己的特性算出自己的期望尺寸 (如果是viewgroup 还会调用每个子view的measure()进行测量)
    //      4. 父view 在子view计算出期望尺寸后  得出子view的实际尺寸和位置
    //      5. 子view在layout()中 将父view传进来的自己的实际尺寸和位置保存  (如果是viewgroup 还会在onLayout()方法中调用每个自view的layout()把他们的位置传给他们)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
//        view = findViewById(R.id.view);

//        setExample1();
        setExample2();
    }

    private void setExample2() {

    }

//    private void setExample1() {
//        view.setImageResource(R.drawable.head);
//    }
}
