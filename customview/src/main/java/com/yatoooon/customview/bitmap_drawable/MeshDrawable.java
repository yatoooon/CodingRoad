package com.yatoooon.customview.bitmap_drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.yatoooon.baselibrary.utils.DensityUtil;

public class MeshDrawable extends Drawable {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static final int SPACING = DensityUtil.dp2px(10);

    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = 0; i < getBounds().right; i += SPACING) {
            for (int j = 0; j < getBounds().bottom; j += SPACING) {
                canvas.drawLine(getBounds().left, j, getBounds().right, j, paint);
                canvas.drawLine(i, getBounds().top, i, getBounds().bottom, paint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
                paint.getAlpha() == 0xff ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }
}
