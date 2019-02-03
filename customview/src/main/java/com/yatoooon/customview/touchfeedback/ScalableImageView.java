package com.yatoooon.customview.touchfeedback;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import com.yatoooon.baselibrary.utils.BitmapUtil;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.R;

public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect = new Rect();
    public static final int WIDTH = DensityUtil.dp2px(100);
    public static final float ScaleFactor = 1.5f;
    Bitmap bitmap;

    float smallscale;
    float bigsmallscale;
    private final GestureDetectorCompat detector;
    float scaleValue;

    float currentscale = 0;
    ObjectAnimator scaleAnimator;

    boolean isBig;

    float offSetX = 0;
    float offSetY = 0;
    private final OverScroller overScroller;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapUtil.getBitmap(getContext(), R.drawable.head, WIDTH * 2);
        detector = new GestureDetectorCompat(context, this);
        detector.setIsLongpressEnabled(false);
        detector.setOnDoubleTapListener(this);
        overScroller = new OverScroller(context);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float bitmapScale = (float) this.bitmap.getWidth() / this.bitmap.getHeight();
        float screenScale = (float) getWidth() / getHeight();

        if (bitmapScale > screenScale) {
            smallscale = (float) getWidth() / this.bitmap.getWidth();
            bigsmallscale = (float) getHeight() / this.bitmap.getHeight() * ScaleFactor;
        } else {
            bigsmallscale = (float) getWidth() / this.bitmap.getWidth() * ScaleFactor;
            smallscale = (float) getHeight() / this.bitmap.getHeight();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offSetX, offSetY);
        currentscale = smallscale + (bigsmallscale - smallscale) * scaleValue;
        rect.set(getWidth() / 2 - WIDTH, getHeight() / 2 - WIDTH, getWidth() / 2 + WIDTH, getHeight() / 2 + WIDTH);
        canvas.scale(currentscale, currentscale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(bitmap, null, rect, paint);

    }

    public float getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(float scaleValue) {
        this.scaleValue = scaleValue;
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (isBig) {
            offSetX = offSetX - distanceX;
            offSetY = offSetY - distanceY;
            float maxX = (bitmap.getWidth() * bigsmallscale - getWidth()) / 2;
            float maxY = (bitmap.getHeight() * bigsmallscale - getHeight()) / 2;
            if (offSetX > maxX) {
                offSetX = maxX;
            } else if (offSetX < -maxX) {
                offSetX = -maxX;
            }
            if (offSetY > maxY) {
                offSetY = maxY;
            } else if (offSetY < -maxY) {
                offSetY = -maxY;
            }
            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (isBig) {
            int x = (int) ((bitmap.getWidth() * bigsmallscale - getWidth()) / 2);
            int y = (int) ((bitmap.getHeight() * bigsmallscale - getHeight()) / 2);
            overScroller.fling((int) offSetX, (int) offSetY, (int) velocityX, (int) velocityY,
                    -x, x, -y, y);
            ViewCompat.postOnAnimation(this, refresh());
        }
        return false;
    }

    private Runnable refresh() {
        return new Runnable() {
            @Override
            public void run() {
                if (overScroller.computeScrollOffset()) {
                    offSetX = overScroller.getCurrX();
                    offSetY = overScroller.getCurrY();
                    invalidate();
                    ViewCompat.postOnAnimation(ScalableImageView.this, refresh());
                }
            }
        };
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (isBig) {
            getScaleAnimator().reverse();
        } else {
            getScaleAnimator().start();
        }
        isBig = !isBig;
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


    public ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleValue", 0, 1);
        }
        return scaleAnimator;
    }
}
