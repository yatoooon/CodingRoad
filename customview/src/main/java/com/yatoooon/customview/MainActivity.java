package com.yatoooon.customview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.customview.draw.CameraView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CameraView cameraView = findViewById(R.id.view);

        ObjectAnimator topAnimator = ObjectAnimator.ofFloat(cameraView, "topRotateDeg", -45);
        topAnimator.setDuration(1500);
        ObjectAnimator flipAnimator = ObjectAnimator.ofFloat(cameraView, "flipDeg", 270);
        flipAnimator.setDuration(1500);
        ObjectAnimator bottomAnimator = ObjectAnimator.ofFloat(cameraView, "bottomRotateDeg", 45);
        bottomAnimator.setDuration(1500);

        PropertyValuesHolder bottomEndHolder = PropertyValuesHolder.ofFloat("bottomRotateDeg", 0);
        PropertyValuesHolder topEndHolder = PropertyValuesHolder.ofFloat("topRotateDeg", 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(cameraView, bottomEndHolder, topEndHolder);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomAnimator, flipAnimator, topAnimator);
        animatorSet.setStartDelay(2000);
        animatorSet.start();

        objectAnimator.setStartDelay(6500);
        objectAnimator.setDuration(1000);
        objectAnimator.start();


    }
}
