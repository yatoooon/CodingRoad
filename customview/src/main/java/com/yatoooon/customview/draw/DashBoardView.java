package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.DensityUtil;

public class DashBoardView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int SCALENUM = 10;
    private static final int RADIUS = DensityUtil.dp2px(100);
    private static final int LENGTH = DensityUtil.dp2px(50);
    private static final float ANGLE = 120;
    PathDashPathEffect dashPathEffect;

    Path path = new Path();

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        path.addRect(0, 0, DensityUtil.dp2px(2), DensityUtil.dp2px(10), Path.Direction.CW);

        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, ANGLE / 2 + 90, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);

        dashPathEffect = new PathDashPathEffect(path, (pathMeasure.getLength() - DensityUtil.dp2px(2)) / SCALENUM, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, ANGLE / 2 + 90, 360 - ANGLE, false, paint);

        paint.setPathEffect(dashPathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, ANGLE / 2 + 90, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        canvas.drawLine(
                getWidth() / 2,
                getHeight() / 2,
                (float) Math.cos(Math.toRadians(calculatePoint(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(calculatePoint(5))) * LENGTH + getHeight() / 2,
                paint);

    }

    private int calculatePoint(int point) {
        return (int) ((360 - ANGLE) / SCALENUM * point + 150);
    }


}
