package com.yatoooon.customview.bitmap_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DrawableView extends View {
    //自定义drawable
    MeshDrawable meshDrawable;

    //常用的google都封装好了   比如BitmapDrawable    ColorDrawable

    public DrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        meshDrawable = new MeshDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        meshDrawable.setBounds(0, 0, getWidth(), getHeight());
        meshDrawable.draw(canvas);
    }
}
