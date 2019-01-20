package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.BitmapUtil;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.R;

public class CameraView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public static final int WIDTH = DensityUtil.dp2px(100);

    Camera camera = new Camera();
    Rect rect = new Rect();

    Path clipPath_top = new Path();
    Path clipPath_bottom = new Path();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        camera.rotateX(45);
        camera.setLocation(0, 0, DensityUtil.getZForCamera());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.set(getWidth() / 2 - WIDTH, getHeight() / 2 - WIDTH, getWidth() / 2 + WIDTH, getHeight() / 2 + WIDTH);
        clipPath_top.addRect(getWidth() / 2 - WIDTH, getHeight() / 2 - WIDTH, getWidth() / 2 + WIDTH, getHeight() / 2, Path.Direction.CW);
        clipPath_bottom.addRect(getWidth() / 2 - WIDTH, getHeight() / 2, getWidth() / 2 + WIDTH, getHeight() / 2 + WIDTH, Path.Direction.CW);


        canvas.save();
        canvas.clipPath(clipPath_top);
        canvas.drawBitmap(BitmapUtil.getAvatar(getContext(), R.drawable.logo, WIDTH), null, rect, paint);
        canvas.restore();


        canvas.save();
        camera.applyToCanvas(canvas);
        canvas.clipPath(clipPath_bottom);
        canvas.drawBitmap(BitmapUtil.getAvatar(getContext(), R.drawable.logo, WIDTH), null, rect, paint);
        canvas.restore();

    }


}
