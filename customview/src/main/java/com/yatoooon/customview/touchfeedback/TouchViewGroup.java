package com.yatoooon.customview.touchfeedback;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import timber.log.Timber;

public class TouchViewGroup extends RelativeLayout {

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //常见的写法是 滿足条件后 返回true拦截
//        if () {
//            return true;
//        }
//        return false;
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Timber.d("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Timber.d("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Timber.d("ACTION_UP");
                performClick();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Timber.d("ACTION_POINTER_DOWN");
                break;
            default:
                break;
        }
        return true;
    }
}
