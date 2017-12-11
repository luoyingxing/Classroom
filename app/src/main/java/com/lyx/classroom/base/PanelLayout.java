package com.lyx.classroom.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * PanelLayout
 * <p>
 * Created by luoyingxing on 2017/11/18 0018.
 */

public class PanelLayout extends ViewGroup {
    private Paint mPaint;
    private String mStrokeColor = "#70FFFFFF";

    public PanelLayout(Context context) {
        super(context);
        init();
    }

    public PanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor(mStrokeColor));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
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

        if (childCount != 5) {
            throw new RuntimeException("the child view only support five count!");
        }

        View childOne = getChildAt(0);
        View childTwo = getChildAt(1);
        View childThree = getChildAt(2);
        View childFour = getChildAt(3);
        View childFive = getChildAt(4);

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
        int twoLeft = perWidth - twoWidth / 2;
        childTwo.layout(twoLeft, centerHeight / 2 * 3 - twoHeight, twoLeft + twoWidth, centerHeight / 2 * 3);

        int threeWidth = childFour.getMeasuredWidth();
        int threeHeight = childFour.getMeasuredHeight();
        int threeLeft = width / 2 - threeWidth / 2;
        childThree.layout(threeLeft, centerHeight / 2 * 3 - threeHeight / 2, threeLeft + threeWidth, centerHeight / 2 * 3 + threeHeight / 2);

        int fourWidth = childFour.getMeasuredWidth();
        int fourHeight = childFour.getMeasuredHeight();
        int fourRight = width / 4 * 3 + fourWidth / 2;
        childFour.layout(fourRight - fourWidth, centerHeight / 2 * 3 - fourHeight, fourRight, centerHeight / 2 * 3);

        int fiveWidth = childFive.getMeasuredWidth();
        int fiveHeight = childFive.getMeasuredHeight();
        int fiveRight = width - (perWidth - fiveWidth) / 2;
        childFive.layout(fiveRight - fiveWidth, centerHeight - fiveHeight, fiveRight, centerHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(new RectF(0, -26, getMeasuredWidth(), getMeasuredHeight() / 16 * 15), 0, 180, false, mPaint);
    }
}