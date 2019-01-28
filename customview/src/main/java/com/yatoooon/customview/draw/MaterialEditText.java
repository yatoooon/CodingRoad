package com.yatoooon.customview.draw;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.R;

public class MaterialEditText extends AppCompatEditText {
    public static final int TEXTSIZE = DensityUtil.dp2px(20);
    public static final int PADDING_TOP = DensityUtil.dp2px(10);
    public static final int PADDING_LEFT = DensityUtil.dp2px(5);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MaterialEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isShow && TextUtils.isEmpty(s)) {
                    isShow = false;
                    animatior().reverse();
                } else if (!isShow && !TextUtils.isEmpty(s)) {
                    isShow = true;
                    animatior().start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public float getCustomValue() {
        return customValue;
    }

    public void setCustomValue(float customValue) {
        this.customValue = customValue;
        invalidate();
    }

    private float customValue;

    private boolean isShow;


    private ObjectAnimator animatior() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(MaterialEditText.this, "customValue", 0, 1);
        animator.setDuration(200);
        return animator;
    }

    {
        setPadding(getPaddingLeft(), getPaddingTop() + TEXTSIZE, getPaddingRight(), getPaddingBottom());
        paint.setTextSize(TEXTSIZE);
        paint.setColor(getResources().getColor(R.color.colorAccent));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint.setAlpha((int) (255 * customValue));
        canvas.drawText(getHint().toString(), 0, getHint().toString().length(), PADDING_LEFT, getPaddingTop() - (customValue * PADDING_TOP), paint);
    }
}
