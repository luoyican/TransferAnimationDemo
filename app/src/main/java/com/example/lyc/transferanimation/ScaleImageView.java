package com.example.lyc.transferanimation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by luoyican on 2017/6/1.
 */

public class ScaleImageView extends AppCompatImageView {
    private int scale;

    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scale = attrs.getAttributeIntValue(null, "scale", 100);
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        /**
         * if both width and height are set scale width first. modify in future if necessary
         */
        int isScale = 0;//0 宽高自适应 1宽定高自适应 2高定宽自适应
        if (widthMode == MeasureSpec.EXACTLY) {
            isScale = 1;
        } else if (heightMode == MeasureSpec.EXACTLY) {
            isScale = 2;
        } else {
            isScale = 0;
        }


        if (getDrawable() == null || getDrawable().getIntrinsicWidth() == 0) {
            // nothing to measure
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        } else {
            int iw = this.getDrawable().getIntrinsicWidth();
            int ih = this.getDrawable().getIntrinsicHeight();
            int realWidth = width;
            int realHeight = height;
            if (isScale == 0) {//0 宽高自适应
                realWidth = dip2px(iw / 2);
                realHeight = dip2px(ih / 2);
                setMeasuredDimension(realWidth, realHeight);

            } else if (isScale == 1) {// 1宽定高自适应
                realWidth = width;
                realHeight = (int) (1f * ih * width / iw + 0.5F);

            } else if (isScale == 2) {
                realWidth = (int) (1f * iw * height / ih + 0.5F);
                realHeight = height;
            }
            setMeasuredDimension(realWidth * scale / 100, realHeight * scale / 100);
        }
    }

    private int dip2px(int dipValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, getContext().getResources().getDisplayMetrics()) + 0.5f);
    }
}
