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

public class MultiTouchView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private float offSetX;
    private float offSetY;
    private float downX;
    private float downY;
    private float originX;
    private float originY;
    private int trackingPointerId;

    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapUtil.getBitmap(context, R.drawable.head, DensityUtil.dp2px(200));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, offSetX, offSetY, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: // 第⼀个⼿指按下（之前没有任何⼿指触摸到 View）
                trackingPointerId = event.getPointerId(event.getActionIndex());
                downX = event.getX();
                downY = event.getY();
                originX = offSetX;
                originY = offSetY;
                break;
            case MotionEvent.ACTION_MOVE://有⼿指发⽣移动
                int index = event.findPointerIndex(trackingPointerId);
                offSetX = originX + event.getX(index) - downX;
                offSetY = originY + event.getY(index) - downY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP://最后⼀个⼿指抬起（抬起之后没有任何⼿指触摸到 View，这个⼿指未必是 ACTION_DOWN 的那 个⼿指）
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //额外⼿指按下（按下之前已经有别的⼿指触摸到 View）
                int actionIndex = event.getActionIndex();
                trackingPointerId = event.getPointerId(actionIndex);
                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);
                originX = offSetX;
                originY = offSetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:// 有⼿指抬起，但不是最后⼀个（抬起之后，仍然还有别的⼿指在触摸着 View）
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == trackingPointerId) {
                    int newIndex;
                    if (actionIndex == event.getPointerCount() - 1) {
                        newIndex = event.getPointerCount() - 2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }
                    trackingPointerId = event.getPointerId(newIndex);
                    downX = event.getX(actionIndex);
                    downY = event.getY(actionIndex);
                    originX = offSetX;
                    originY = offSetY;
                }
                break;
            default:
                break;
        }
        return true;
    }
}
