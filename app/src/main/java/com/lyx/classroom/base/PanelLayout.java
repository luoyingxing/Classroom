package com.lyx.classroom.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * PanelLayout
 * <p>
 * Created by luoyingxing on 2017/11/18 0018.
 */

public class PanelLayout extends ViewGroup {

    public PanelLayout(Context context) {
        super(context);
    }

    public PanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : getWidth(),
                (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();

        if (childCount != 4) {
            throw new RuntimeException("the child view only support four count!");
        }

        View childOne = getChildAt(0);
        View childTwo = getChildAt(1);
        View childThree = getChildAt(2);
        View childFour = getChildAt(3);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int perWidth = width / 4;
        int centerHeight = height / 2;

        int oneWidth = childOne.getMeasuredWidth();
        int oneHeight = childOne.getMeasuredHeight();
        int oneLeft = (perWidth - oneWidth) / 2;
        childOne.layout(oneLeft, centerHeight - oneHeight, oneLeft + oneWidth, centerHeight);

        int twoWidth = childTwo.getMeasuredWidth();
        int twoHeight = childTwo.getMeasuredHeight();
        int twoLeft = (perWidth - twoWidth) / 2 + perWidth;
        childTwo.layout(twoLeft, centerHeight / 2 * 3 - twoHeight, twoLeft + twoWidth, centerHeight / 2 * 3);

        int threeWidth = childFour.getMeasuredWidth();
        int threeHeight = childFour.getMeasuredHeight();
        int threeRight = width - perWidth - (perWidth - threeHeight) / 2;
        childThree.layout(threeRight - threeWidth, centerHeight / 2 * 3 - threeHeight, threeRight, centerHeight / 2 * 3);

        int fourWidth = childFour.getMeasuredWidth();
        int fourHeight = childFour.getMeasuredHeight();
        int fourRight = width - (perWidth - fourWidth) / 2;
        childFour.layout(fourRight - fourWidth, centerHeight - fourHeight, fourRight, centerHeight);

    }


}
