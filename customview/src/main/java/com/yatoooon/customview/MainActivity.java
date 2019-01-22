package com.yatoooon.customview;

import android.animation.*;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.draw.PointView;
import com.yatoooon.customview.draw.ProvinceView;

public class MainActivity extends AppCompatActivity {

    //    private CameraView view;    //1 2 3
    private PointView view;       //4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view = findViewById(R.id.view);


//        setExample1();
//        setExample2();
//        setExample3();
//        setExample4();
        setExample5();


    }


    class ProvinceEvaluator implements TypeEvaluator<String> {
        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
            // 北京市      上海市       fraction 0.5f
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
        animatorSet.playSequentially(bottomAnimator, flipAnimator, topAnimator);   //依次执行
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
