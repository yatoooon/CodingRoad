package com.yatoooon.customview.layout;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class SquareImageView extends AppCompatImageView {


    //继承已有view  简单改写尺寸:重写 onMeasure();
    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  //计算期望尺寸

        int width = getMeasuredWidth();  //获取期望尺寸
        int height = getMeasuredHeight();
        int size = Math.min(width, height); //更改期望尺寸     只有确定的值才有效   matchparent 和wrapcontent 无效


        setMeasuredDimension(size, size);  //存下来
    }
}
