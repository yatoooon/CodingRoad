package com.yatoooon.customview.touchfeedback;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import timber.log.Timber;

public class TouchView extends View {

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
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
