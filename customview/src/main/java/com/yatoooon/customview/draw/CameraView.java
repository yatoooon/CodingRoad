package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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


        canvas.translate((getWidth() / 2), (getHeight() / 2));
        camera.applyToCanvas(canvas);
        canvas.translate(-(getWidth() / 2), -(getHeight() / 2));
        canvas.drawBitmap(BitmapUtil.getAvatar(getContext(), R.drawable.head, WIDTH), null, rect, paint);

    }


}
