package com.yatoooon.customview.touchfeedback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.yatoooon.baselibrary.utils.BitmapUtil;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.R;

public class MultiTouchView2 extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    float mPosX;
    float mPosY;
    private float mLastTouchX;
    private float mLastTouchY;

    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapUtil.getBitmap(context, R.drawable.head, DensityUtil.dp2px(200));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, mPosX, mPosY, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float sumX = 0;
        float sumY = 0;
        int pointerCount = ev.getPointerCount();
        boolean isPointerUp = ev.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        for (int i = 0; i < pointerCount; i++) {
            if (!(isPointerUp && i == ev.getActionIndex())) {
                sumX += ev.getX(i);
                sumY += ev.getY(i);
            }
        }
        if (isPointerUp) {
            pointerCount -= 1;
        }
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: // 第⼀个⼿指按下（之前没有任何⼿指触摸到 View）
            case MotionEvent.ACTION_POINTER_DOWN: //额外⼿指按下（按下之前已经有别的⼿指触摸到 View）
            case MotionEvent.ACTION_POINTER_UP:// 有⼿指抬起，但不是最后⼀个（抬起之后，仍然还有别的⼿指在触摸着 View）
                mLastTouchX = focusX;
                mLastTouchY = focusY;
                break;
            case MotionEvent.ACTION_MOVE: //有⼿指发⽣移动
                mPosX += focusX - mLastTouchX;
                mPosY += focusY - mLastTouchY;
                mLastTouchX = focusX;
                mLastTouchY = focusY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP: //最后⼀个⼿指抬起（抬起之后没有任何⼿指触摸到 View，这个⼿指未必是 ACTION_DOWN 的那 个⼿指）
                break;
            default:
                break;
        }

        return true;
    }
}
