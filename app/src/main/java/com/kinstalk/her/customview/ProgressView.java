package com.kinstalk.her.customview;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 水波效果的进度
 */
public class ProgressView extends View {
    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private float mPathOffset;//水波偏移

    private float mMaxProgress;
    private float mCurProgress;
    private float mPerCycleWidth;//一个周期宽
    private float mPerCycleLength;//一个周期路径长度
    private float mCycleCount = 6;//周期个数

    private float mWidth;
    private float mHeight;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCurProgress = 0;
        mMaxProgress = 100;
        mPaint = new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setColor(0x771234FF);
        mPath = new Path();
        mPathMeasure = new PathMeasure();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //对路径进行绘制
        mPath.reset();
        mPerCycleWidth = mWidth / mCycleCount;

        //path绘制一段正弦曲线
        mPath.moveTo(0, h / 2);
        for (int i = 0; i < mCycleCount + 2; i++) {
            //二次贝塞尔曲线
            mPath.rQuadTo(mPerCycleWidth / 4, -mPerCycleWidth / 4,
                    mPerCycleWidth / 2, 0);
            mPath.rQuadTo(mPerCycleWidth / 4, mPerCycleWidth / 4,
                    mPerCycleWidth / 2, 0);
        }
        mPathMeasure.setPath(mPath, false);
        float length = mPathMeasure.getLength();
        mPerCycleLength = length / (mCycleCount + 2);

        mPath.lineTo(mWidth + 2 * mPerCycleWidth, mHeight);
        mPath.lineTo(0, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(-mPathOffset, 0);//偏移了一段距离
        canvas.drawPath(mPath, mPaint);
        canvas.save();
        canvas.translate(-mPathOffset, 0);//偏移了上面的两倍
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        canvas.restore();
        mPathOffset += 1;
        if (mPathOffset > mPerCycleLength) {
            mPathOffset = 0;
        }
        invalidate();
    }

    public float getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(float maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public float getCurProgress() {
        return mCurProgress;
    }

    /**
     * 设置当前进度
     *
     * @param curProgress
     */
    public void setCurProgress(float curProgress) {
        if (curProgress > mMaxProgress) {
            curProgress = mMaxProgress;
        } else if (curProgress < 0) {
            curProgress = 0;
        }
        this.mCurProgress = curProgress;
    }
}
