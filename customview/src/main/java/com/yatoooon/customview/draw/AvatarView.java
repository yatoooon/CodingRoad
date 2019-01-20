package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.BitmapUtil;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.R;

public class AvatarView extends View {
    public static final int WIDTH = DensityUtil.dp2px(200);
    public static final int RADIUS = DensityUtil.dp2px(100);
    public static final int EDGE = DensityUtil.dp2px(10);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rect = new RectF();

    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
        int saveLayer = canvas.saveLayer(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, paint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS - EDGE, paint);
        paint.setXfermode(xfermode);
        rect.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        canvas.drawBitmap(BitmapUtil.getAvatar(getContext(),R.drawable.head,WIDTH), null, rect, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }


}
