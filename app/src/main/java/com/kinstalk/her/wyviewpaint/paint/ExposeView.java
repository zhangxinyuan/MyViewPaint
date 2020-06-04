package com.kinstalk.her.wyviewpaint.paint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.kinstalk.her.wyviewpaint.R;

public class ExposeView extends View {

    private static final int BALL_SIZE_DEFAULT = 40;
    private static final int BALL_CIRCLE_RADIUS = 80;

    private Paint mBallPaint;
    private Paint mHolePaint;

    private float mCenterX;
    private float mCenterY;
    private float mDistence;

    private float mBallRadius = BALL_SIZE_DEFAULT / 2;//小球的半径
    private float mBallCircleRadius = BALL_CIRCLE_RADIUS;//小球形成的圆的半径
    private float mHoleRadius = 0; //孔的半径

    private float mBallCircleRotateRadian; // 小球形成的圆转动时，相对于静止时的偏移弧度

    private int mBackgroundColor = Color.WHITE;
    private int[] mBallColors;

    private ExposeViewStatus mStatus;
    private ValueAnimator mValueAnimator;

    public ExposeView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mBallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackgroundColor);
        mBallColors = getResources().getIntArray(R.array.colors_ball);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2;
        mCenterY = h / 2;
        mDistence = (float) Math.sqrt(mCenterX * mCenterY + mCenterY * mCenterY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mStatus == null) {
            mStatus = new RotateStatus();
        }
        mStatus.onDraw(canvas);
    }

    private abstract class ExposeViewStatus {
        abstract void onDraw(Canvas canvas);
    }

    private class ExposeStatus extends ExposeViewStatus {

        public ExposeStatus() {
            mValueAnimator = ValueAnimator.ofFloat(mHoleRadius, mDistence);
            mValueAnimator.setDuration(1200);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mHoleRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.start();
        }

        @Override
        void onDraw(Canvas canvas) {
            drawBackground(canvas);
        }
    }

    private class ScaleStatus extends ExposeViewStatus {

        public ScaleStatus() {
            mValueAnimator = ValueAnimator.ofFloat(mBallCircleRadius, 160);
            mValueAnimator.setInterpolator(new OvershootInterpolator(10f));
            mValueAnimator.setDuration(2000);
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mBallCircleRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mStatus = new ExposeStatus();
                }
            });
            mValueAnimator.reverse();
        }

        @Override
        void onDraw(Canvas canvas) {
            drawBackground(canvas);
            drawBalls(canvas);
        }
    }

    private class RotateStatus extends ExposeViewStatus {

        public RotateStatus() {
            mValueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));
            mValueAnimator.setDuration(1200);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mBallCircleRotateRadian = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mStatus = new ScaleStatus();
                }
            });
            mValueAnimator.start();
        }

        @Override
        void onDraw(Canvas canvas) {
            drawBackground(canvas);
            drawBalls(canvas);
        }
    }

    private void drawBalls(Canvas canvas) {
        //小球平分2pi弧度
        float singleRadian = (float) (Math.PI * 2 / mBallColors.length);
        for (int i = 0; i < mBallColors.length; i++) {
            mBallPaint.setColor(mBallColors[i]);
            //每个小球的弧度+ 旋转时
            float ballRadian = i * singleRadian + mBallCircleRotateRadian;
            float cx = (float) (Math.cos(ballRadian) * mBallCircleRadius + mCenterX);
            float cy = (float) (Math.sin(ballRadian) * mBallCircleRadius + mCenterY);
            canvas.drawCircle(cx, cy, mBallRadius, mBallPaint);
        }
    }

    private void drawBackground(Canvas canvas) {
        //通过判断空心圆的半径来判断是不是开始绘制空心圆
        if (mHoleRadius == 0) {
            canvas.drawColor(mBackgroundColor);
        } else {
            //空心圆的本质是：线很宽的圆
            float stokeWidth = mDistence - mHoleRadius;
            mHolePaint.setStrokeWidth(stokeWidth);
            //设置线宽后，圆的实际半径在线宽中心点
            canvas.drawCircle(mCenterX, mCenterY, mHoleRadius + stokeWidth / 2, mHolePaint);
        }
    }

    public void start() {
        if (mValueAnimator.isRunning()) {
            return;
        }
        mHoleRadius = 0; //孔的半径
        mBallCircleRadius = BALL_CIRCLE_RADIUS;
        mBallCircleRotateRadian = 0;
        mStatus = new RotateStatus();
        invalidate();
    }
}
