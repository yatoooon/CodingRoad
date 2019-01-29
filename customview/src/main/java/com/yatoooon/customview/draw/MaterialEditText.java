package com.yatoooon.customview.draw;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.yatoooon.baselibrary.utils.DensityUtil;
import com.yatoooon.customview.R;

public class MaterialEditText extends AppCompatEditText {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final int TEXTSIZE = DensityUtil.dp2px(20);
    private static final int PADDING_TOP = DensityUtil.dp2px(10);
    private static final int PADDING_LEFT = DensityUtil.dp2px(5);

    private boolean useFloatingLable = true;
    private Rect rect;

    private float getCustomValue() {
        return customValue;
    }

    private void setCustomValue(float customValue) {
        this.customValue = customValue;
        invalidate();
    }

    private float customValue;


    private boolean isShow;

    public void setUseFloatingLable(boolean useFloatingLable) {
        this.useFloatingLable = useFloatingLable;
//        requestLayout();
        setPadding(useFloatingLable);
    }

    private void setPadding(boolean useFloatingLable) {
        if (useFloatingLable) {
            setPadding(getPaddingLeft(), rect.top + TEXTSIZE + PADDING_TOP, getPaddingRight(), getPaddingBottom());

        } else {
            setPadding(getPaddingLeft(), rect.top, getPaddingRight(), getPaddingBottom());
        }
    }

    public MaterialEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rect = new Rect(getBackground().getBounds());

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (useFloatingLable) {
                    if (isShow && TextUtils.isEmpty(s)) {
                        isShow = false;
                        animatior().reverse();
                    } else if (!isShow && !TextUtils.isEmpty(s)) {
                        isShow = true;
                        animatior().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setPadding(useFloatingLable);
        paint.setTextSize(TEXTSIZE);
        paint.setColor(getResources().getColor(R.color.colorAccent));

    }

    private ObjectAnimator animatior() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(MaterialEditText.this, "customValue", 0, 1);
        animator.setDuration(200);
        return animator;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint.setAlpha((int) (255 * customValue));
        canvas.drawText(getHint().toString(), 0, getHint().toString().length(), PADDING_LEFT, getPaddingTop() - (customValue * PADDING_TOP), paint);
    }
}
