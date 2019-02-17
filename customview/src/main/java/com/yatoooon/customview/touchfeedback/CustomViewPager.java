package com.yatoooon.customview.touchfeedback;


import android.content.Context;
import android.util.AttributeSet;
import android.view.*;
import android.widget.OverScroller;

public class CustomViewPager extends ViewGroup {


    private final int maxVelocity;
    private final int minVelocity;
    private final OverScroller overScroller;
    private float downX;
    private float downY;
    private int startScrollX;

    VelocityTracker velocityTracker = VelocityTracker.obtain();
    private final ViewConfiguration viewConfiguration;
    private boolean scrolling;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        overScroller = new OverScroller(context);
        viewConfiguration = ViewConfiguration.get(context);
        maxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        minVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int right = getWidth();
        int bottom = getHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            childAt.layout(left, top, right, bottom);
            left += getWidth();
            right += getWidth();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear();
        }
        velocityTracker.addMovement(ev);

        boolean result = false;
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                scrolling = false;
                downX = ev.getX();
                downY = ev.getY();
                startScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = downX - ev.getX();
                if (!scrolling) {
                    if (Math.abs(dx) > viewConfiguration.getScaledPagingTouchSlop()) {
                        scrolling = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        result = true;
                    }
                }
                break;
            default:
                break;
        }
        return result;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear();
        }
        velocityTracker.addMovement(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                startScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = downX - event.getX() + startScrollX;
                if (dx > getWidth()) {
                    dx = getWidth();
                } else if (dx < 0) {
                    dx = 0;
                }
                scrollTo((int) (dx), 0);
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(1000, maxVelocity);
                float xVelocity = velocityTracker.getXVelocity();
                int scrollX = getScrollX();
                int targetPage;
                if (Math.abs(xVelocity) < minVelocity) {
                    targetPage = scrollX > getWidth() / 2 ? 1 : 0;
                } else {
                    targetPage = xVelocity < 0 ? 1 : 0;
                }
                int scrollDistance = targetPage == 1 ? (getWidth() - scrollX) : -scrollX;
                overScroller.startScroll(getScrollX(), 0, scrollDistance, 0);
                postInvalidateOnAnimation();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.getCurrX(), overScroller.getCurrY());
            postInvalidateOnAnimation();
        }
    }
}
