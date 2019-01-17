package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.DensityUtil;

public class SportView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static final int RADIOUS = DensityUtil.dp2px(100);

    RectF rectF = new RectF();

    Rect rect = new Rect();


    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(DensityUtil.dp2px(20));
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.LTGRAY);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIOUS, paint);


        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        rectF.set(getWidth() / 2 - RADIOUS, getHeight() / 2 - RADIOUS, getWidth() / 2 + RADIOUS, getHeight() / 2 + RADIOUS);
        canvas.drawArc(rectF, 0, 250, false, paint);


        paint.setColor(Color.BLACK);
        paint.setTextSize(DensityUtil.dp2px(30));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);

//        paint.getTextBounds("SportView", 0, "SportView".length(), rect);// 第一种.适用于固定文字的居中    对固定文字进行测量    aaaa   和  bbbb  bounds测量出来的就不一样
//        canvas.drawText("SportView", getWidth() / 2, getHeight() / 2 + (rect.bottom - rect.top) / 2, paint);

        float offset = (paint.getFontMetrics().ascent + paint.getFontMetrics().descent) / 2;// 第二种.适用于变换文字的居中    不管是什么文字  取固定的值
        canvas.drawText("SportView", getWidth() / 2, getHeight() / 2 - offset, paint);

    }
}
