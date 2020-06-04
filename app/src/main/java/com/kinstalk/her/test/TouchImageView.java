package com.kinstalk.her.test;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;


@SuppressLint("AppCompatCustomView")
public class TouchImageView extends ImageView {
    private RectF imageRect = new RectF();
    private boolean canDrag;
    private boolean canScale;
    private boolean canRotate;
    private PointF downPoint1 = new PointF();
    private PointF downPoint2 = new PointF();
    private PointF lastMidPoint = new PointF();
    private PointF mLastVector = new PointF();
    private Matrix matrix = new Matrix();

    public TouchImageView(Context context) {
        this(context, null);
    }

    public TouchImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        matrix.reset();
        if (getDrawable() != null) {
            imageRect.set(getDrawable().getBounds());
            matrix.mapRect(imageRect, imageRect);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF midPoint = getMidPoint(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN://第一个手指初次接触到屏幕时触发
            case MotionEvent.ACTION_POINTER_DOWN://除了第一个手指的按下触发
                canDrag = false;
                canScale = false;
                canRotate = false;
                lastMidPoint.set(midPoint);
                if (event.getPointerCount() == 1) {
                    canDrag = true;
                } else if (event.getPointerCount() == 2) {
                    canScale = true;
                    downPoint1.set(event.getX(0), event.getY(0));
                    downPoint2.set(event.getX(1), event.getY(1));
                    canRotate = true;
                    // 手指刚按下时的向量
                    mLastVector.set(event.getX(1) - event.getX(0),
                            event.getY(1) - event.getY(0));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (canDrag) {
                    translate(midPoint);
                }
                if (canScale) {
                    scale(event);
                }
                if (canRotate) {
                    rotate(event);
                }
                if (canDrag || canScale || canRotate) {
                    applyMatrix();
                }
                break;
            case MotionEvent.ACTION_POINTER_UP://除最后一个手指抬起触发
                break;
            case MotionEvent.ACTION_UP://最后一个手指离开屏幕时触发
                canDrag = false;
                canScale = false;
                canRotate =false;
                break;
        }
        return super.onTouchEvent(event);
    }

    PointF curVector = new PointF();
    private void rotate(MotionEvent event) {
        curVector.set(event.getX(1) - event.getX(0),
                event.getY(1) - event.getY(0));
        float degree = getRotateDegree(curVector, mLastVector);
        matrix.postRotate(degree, imageRect.centerX(), imageRect.centerY());
        mLastVector.set(curVector);
    }

    /**
     * 计算旋转度
     *
     * @param curVector
     * @param lastVector
     * @return
     */
    private float getRotateDegree(PointF curVector, PointF lastVector) {
        double curRad = Math.atan2(curVector.y, curVector.x);
        double lastRad = Math.atan2(lastVector.y, lastVector.x);
        double dAngrad = lastRad - curRad;
        //“弧度”转“度” (radians to degrees)
        return (float) Math.toDegrees(dAngrad);
    }

    PointF curPoint1 = new PointF();
    PointF curPoint2 = new PointF();
    PointF lastPoint1 = new PointF();
    PointF lastPoint2 = new PointF();
    float curScaleFactor;
    private void scale(MotionEvent event) {
        PointF scaleCenter = getScaleCenter();
        curPoint1.set(event.getX(0), event.getY(0));
        curPoint2.set(event.getX(1), event.getY(1));
        float scaleFactor = distance(curPoint1, curPoint2) / distance(lastPoint1, lastPoint2);
        curScaleFactor *= scaleFactor;
        matrix.postScale(scaleFactor, scaleFactor, scaleCenter.x, scaleCenter.y);
        lastPoint1.set(curPoint1);
        lastPoint2.set(curPoint2);
    }

    private PointF getScaleCenter() {
        return new PointF(lastMidPoint.x, lastMidPoint.y);
    }

    /**
     * 计算两点间的距离
     *
     * @param point1
     * @param point2
     * @return
     */
    private float distance(PointF point1, PointF point2) {
        float dx = point2.x - point1.x;
        float dy = point2.y - point1.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 平移
     *
     * @param midPoint
     */
    private void translate(PointF midPoint) {
        float dx = midPoint.x - lastMidPoint.x;
        float dy = midPoint.y - lastMidPoint.y;
        matrix.postTranslate(dx, dy);
        lastMidPoint = midPoint;
    }

    PointF midPoint = new PointF();

    private PointF getMidPoint(MotionEvent event) {
        midPoint.set(0, 0);
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            midPoint.x += event.getX(i);
            midPoint.y += event.getY(i);
        }
        midPoint.x /= pointerCount;
        midPoint.y /= pointerCount;
        return midPoint;
    }

    /**
     * 更新图片所在区域，并将矩阵应用到图片
     */
    protected void applyMatrix() {
        refreshImageRect(); /*将矩阵映射到ImageRect*/
        setImageMatrix(matrix);
    }

    /**
     * 图片使用矩阵变换后，刷新图片所对应的mImageRect所指示的区域
     */
    private void refreshImageRect() {
        if (getDrawable() != null) {
            imageRect.set(getDrawable().getBounds());
            matrix.mapRect(imageRect, imageRect);
        }
    }
}
