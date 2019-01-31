package com.yatoooon.customview.layout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    List<Rect> childLayoutList = new ArrayList<>();


    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int heightUsed = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            measureChildWithMargins(childAt, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
            Rect rect = new Rect();
            rect.set(widthUsed, 0, widthUsed + childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            childLayoutList.add(rect);
            widthUsed = widthUsed + childAt.getMeasuredWidth();
            heightUsed = childAt.getMeasuredHeight();
        }

        setMeasuredDimension(widthUsed, heightUsed);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            Rect rect = childLayoutList.get(i);
            childAt.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
