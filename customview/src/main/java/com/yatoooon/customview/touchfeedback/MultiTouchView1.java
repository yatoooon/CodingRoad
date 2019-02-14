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

public class MultiTouchView1 extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private float mPosX;
    private float mPosY;

    private float mLastTouchX;
    private float mLastTouchY;
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = INVALID_POINTER_ID;

    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapUtil.getBitmap(context, R.drawable.head, DensityUtil.dp2px(200));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, mPosX, mPosY, paint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {// 第⼀个⼿指按下（之前没有任何⼿指触摸到 View）
                mLastTouchX = ev.getX();
                mLastTouchY = ev.getY();
                mActivePointerId = ev.getPointerId(0);
                break;
            }
            case MotionEvent.ACTION_MOVE: {//有⼿指发⽣移动
                int pointerIndex = ev.findPointerIndex(mActivePointerId);
                float x = ev.getX(pointerIndex);
                float y = ev.getY(pointerIndex);
                mPosX = mPosX + x - mLastTouchX;
                mPosY = mPosY + y - mLastTouchY;
                mLastTouchX = x;
                mLastTouchY = y;
                invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {//最后⼀个⼿指抬起（抬起之后没有任何⼿指触摸到 View，这个⼿指未必是 ACTION_DOWN 的那 个⼿指）
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {//额外⼿指按下（按下之前已经有别的⼿指触摸到 View）
                int actionIndex = ev.getActionIndex();
                mLastTouchX = ev.getX(actionIndex);
                mLastTouchY = ev.getY(actionIndex);
                mActivePointerId = ev.getPointerId(actionIndex);
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {// 有⼿指抬起，但不是最后⼀个（抬起之后，仍然还有别的⼿指在触摸着 View）
                int pointerIndex = ev.getActionIndex();
                int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = ev.getX(newPointerIndex);
                    mLastTouchY = ev.getY(newPointerIndex);
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                }
                break;
            }
            default:
                break;
        }

        return true;
    }
}
