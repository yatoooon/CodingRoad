package com.yatoooon.customview;

import android.animation.*;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.baselibrary.utils.BitmapUtil;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.bitmap_drawable.DrawableView;
import com.yatoooon.customview.draw.ProvinceView;

public class MainActivity extends AppCompatActivity {

    //    private CameraView view;    //1 2 3
//    private PointView view;       //4
//    private ProvinceView view;       //5
//    private DottedLineView view;       //6
    private DrawableView view;
//    private SquareImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view = findViewById(R.id.view);


//        setExample1();
//        setExample2();
//        setExample3();
//        setExample4();
//        setExample5();
//        setExample6();


    }

    private void setExample6() {
        Bitmap avatar = BitmapUtil.getBitmap(this, R.drawable.head, DensityUtil.dp2px(200));

        //bitmap2drawable
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), avatar);

        //drawable2bitmap
        Bitmap bitmap = drawableToBitmap(bitmapDrawable);

        //Resize Bitmap
        final int IMAGE_WIDTH = 320;
        final int IMAGE_HEIGHT = 180;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT, true);

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    class ProvinceEvaluator implements TypeEvaluator<String> {
        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
//             fraction 0 - 0.5 - 1f
//            startValue + (endValue - startValue) * fraction
            int startIndex = ProvinceView.provinces.indexOf(startValue);
            int endIndex = ProvinceView.provinces.indexOf(endValue);
            int index = (int) (startIndex + (endIndex - startIndex) * fraction);
            return ProvinceView.provinces.get(index);
        }


    }

    private void setExample5() {
        String endStr = "甘肃省";
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view, "province", new ProvinceEvaluator(), endStr);
        objectAnimator.setDuration(1500);
        objectAnimator.setStartDelay(1000);
        objectAnimator.start();
    }

    private void setExample4() {
        PointF point = new PointF(DensityUtil.dp2px(200), DensityUtil.dp2px(200));
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view, "PointF", new PointFEvaluator(), point);
        objectAnimator.setDuration(1500);
        objectAnimator.setStartDelay(1000);
        objectAnimator.start();
    }

    private void setExample3() {
        float length = DensityUtil.dp2px(200);

        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.1f, 0.5f * length);
        Keyframe keyframe3 = Keyframe.ofFloat(0.8f, 0.7f * length);
        Keyframe keyframe4 = Keyframe.ofFloat(0.9f, 0.8f * length);
        Keyframe keyframe5 = Keyframe.ofFloat(1, 1 * length);

        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4, keyframe5);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolder);
        objectAnimator.setDuration(1500);
        objectAnimator.setStartDelay(1000);
        objectAnimator.start();
    }

    private void setExample2() {
        ObjectAnimator topAnimator = ObjectAnimator.ofFloat(view, "topFlipDeg", -45);
        topAnimator.setDuration(1500);
        ObjectAnimator flipAnimator = ObjectAnimator.ofFloat(view, "rotateDeg", 360);
        flipAnimator.setDuration(2000);
        ObjectAnimator bottomAnimator = ObjectAnimator.ofFloat(view, "bottomFlipDeg", 45);
        bottomAnimator.setDuration(1500);

        PropertyValuesHolder bottomEndHolder = PropertyValuesHolder.ofFloat("bottomFlipDeg", 0);
        PropertyValuesHolder topEndHolder = PropertyValuesHolder.ofFloat("topFlipDeg", 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, bottomEndHolder, topEndHolder); //同时执行


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomAnimator, flipAnimator, topAnimator);   //依次执行   playTogether是同时执行
        animatorSet.setStartDelay(1500);
        animatorSet.start();

        objectAnimator.setStartDelay(6500);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private void setExample1() {
        view.animate()
                .translationX(DensityUtil.dp2px(100)) //多次调用  只有最后一次生效
                .translationY(100)
                .translationX(-DensityUtil.dp2px(100))
                .rotation(90)
                .alpha(0.5f)
                .setStartDelay(2000)
                .setDuration(2000)
                .start();
    }
}
