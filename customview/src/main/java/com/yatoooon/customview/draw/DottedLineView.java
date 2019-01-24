package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.DensityUtil;

public class DottedLineView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DottedLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{DensityUtil.dp2px(10), DensityUtil.dp2px(20)}, DensityUtil.dp2px(0)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        setLayerType(LAYER_TYPE_SOFTWARE, null);  //把离屏缓冲设置为一个bitmap  用软件来绘制  一般用这个来关闭硬件加速
//        setLayerType(LAYER_TYPE_HARDWARE,null); //开启硬件加速不是用这个   这个是用于自带view的官方属性动画避免消耗性能
        setLayerType(LAYER_TYPE_NONE, null); //开启硬件加速

        canvas.drawLine(0, 0, DensityUtil.dp2px(200), DensityUtil.dp2px(200), paint);

    }
}
