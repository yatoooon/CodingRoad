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
        int currentLineWidthUsed = 0;
        int currentLineMaxHeight = 0;
        int heightUsed = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (mode != MeasureSpec.UNSPECIFIED && currentLineWidthUsed + childAt.getMeasuredWidth() > size) {
                heightUsed = heightUsed + currentLineMaxHeight;
                currentLineWidthUsed = 0;
                currentLineMaxHeight = 0;
                measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            Rect rect = new Rect();
            rect.set(currentLineWidthUsed, heightUsed, currentLineWidthUsed + childAt.getMeasuredWidth(), heightUsed + childAt.getMeasuredHeight());
            childLayoutList.add(rect);
            currentLineWidthUsed = currentLineWidthUsed + childAt.getMeasuredWidth();
            currentLineMaxHeight = Math.max(currentLineMaxHeight, childAt.getMeasuredHeight());
        }

        int height = heightUsed + currentLineMaxHeight;
        setMeasuredDimension(widthMeasureSpec, height);
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
