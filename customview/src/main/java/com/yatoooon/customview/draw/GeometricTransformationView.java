package com.yatoooon.customview.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yatoooon.baselibrary.utils.BitmapUtil;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.R;

public class GeometricTransformationView extends View {

    Bitmap bitmap;


    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GeometricTransformationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = BitmapUtil.getBitmap(getContext(),R.drawable.head,DensityUtil.dp2px(200));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //几何变换对应的是view坐标系
        //绘制对应的是canvas坐标系


        //1.translate 2 rotate 3 scale 4 skew

        //自己写自定义view的话   倒着写    从下往上   依次是    1 绘制  2 移动 3 旋转  4 放缩  5  错切
        //  两次绘制之间  比如两次drawbitmap之间 正着写

//        canvas.skew(0.5f, 0.5f);
//        canvas.scale(0.5f, 0.5f);
        canvas.rotate(45, 200, 200);
        canvas.translate(200, 200);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }



}
