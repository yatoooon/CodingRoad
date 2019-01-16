package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.DensityUtil;

public class PieChartView extends View {
    public static final int RADIUS = DensityUtil.dp2px(100);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rect = new RectF();
    int[] colors = new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
    int[] angles = new int[]{30, 150, 75, 105};

    private int startAngle = 0;
    private int dragPosition = 3;
    private float LENGHT = DensityUtil.dp2px(10);

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[i]);
            if (i == dragPosition) {
                canvas.save();
                canvas.translate((float) Math.cos(Math.toRadians(startAngle + (float) angles[i] / 2)) * LENGHT, (float) Math.sin(Math.toRadians(startAngle + (float) angles[i] / 2)) * LENGHT);
                canvas.drawArc(rect, startAngle, angles[i], true, paint);
                canvas.restore();
            } else {
                canvas.drawArc(rect, startAngle, angles[i], true, paint);
            }
            startAngle = startAngle + angles[i];
        }
    }
}
