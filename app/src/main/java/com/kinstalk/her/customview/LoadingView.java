package com.kinstalk.her.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LoadingView extends View {

    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private int mDuration = 1500;

    private boolean isSuccess;

    private float mCurrentFaction;
    private Path dst;
    private float length;

    private int step;
    private ValueAnimator animator;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mCenterX = 500;
        mCenterY = 500;
        mRadius = 100;
        mPath = new Path();
        dst = new Path();
        mPath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);
        mPathMeasure = new PathMeasure(mPath, true);
        length = mPathMeasure.getLength();
        animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentFaction = animation.getAnimatedFraction();
            }
        });
        animator.setDuration(mDuration);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float stopD;
        float startD;
        if (isSuccess) {
            stopD = length;
            startD = 0;
        } else {
            dst.reset();
            stopD = mCurrentFaction * length;
            startD = stopD - (0.5f - Math.abs(0.5f - mCurrentFaction)) * length;
        }
        mPathMeasure.getSegment(startD, stopD, dst, true);
        canvas.drawPath(dst, mPaint);

        canvas.drawLine(mCenterX/2, mCenterY/2 , mCenterX/2, mCenterY + mCenterY/2, mPaint);
    }

    public void success() {
        isSuccess = true;
    }
}
