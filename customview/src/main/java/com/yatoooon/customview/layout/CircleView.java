package com.yatoooon.customview.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.DensityUtil;

public class CircleView extends View {

    private static final int RADIUS = DensityUtil.dp2px(80);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //对自定义view完全进行自定义尺寸计算:重写onMeasure();
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = RADIUS * 2;
        int height = RADIUS * 2;
        width = resolveSizeAndState(width, widthMeasureSpec, 0);    //对计算结果进行修正  根据父view的要求和自己计算的尺寸来确定   看一下源码
        height = resolveSizeAndState(height, heightMeasureSpec, 0);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        canvas.drawCircle(RADIUS, RADIUS, RADIUS, paint);
    }
}
