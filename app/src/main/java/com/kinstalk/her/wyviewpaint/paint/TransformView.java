package com.kinstalk.her.wyviewpaint.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

/**
 * 需要明确一点
 * canvas 的操作都是最canvas自身的操作
 * 比如平移，是指将canvas整体平移，之后的操作都是在平移后的canvas坐标系中进行的。
 * 同理，裁剪也是对canvas的操作，裁剪画布整体
 */
public class TransformView extends View {

    private Paint mPaint;

    public TransformView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
//        canvas.drawRect(10, 10, 500, 500, mPaint);
//        //保存画布的状态，入栈
//        int saveStatus = canvas.save();
//
//        canvas.translate(100, 100);
//
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(10, 10, 500, 500, mPaint);
//
//        //恢复画布的状态，出栈。必须在save后去调用，否则会出栈异常
//        canvas.restore();
//        //恢复某个指定的保存的画布的状态
//        canvas.restoreToCount(saveStatus);
//
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(10, 10, 500, 500, mPaint);
        /**
         * 倾斜
         * sx 表示倾斜方向时X轴的方向，sy表示倾斜的方向是y轴的方向
         * sx，sy是指tan的值
         */
//        canvas.skew(1, 0);
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(10, 10, 500, 500, mPaint);
//
//        canvas.scale(0.5f, 0.5f);
//        canvas.drawRect(20, 20, 500, 500, mPaint);
//
//        canvas.scale(2, 2);
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(10, 10, 500, 500, mPaint);

        /**
         * 旋转
         * degrees 旋转角度
         * px,py表示旋转中心坐标
         */
//        mPaint.setColor(Color.MAGENTA);
//        canvas.rotate(45, 250, 250);
//        canvas.drawRect(10, 10, 500, 500, mPaint);

        /**
         * 切割画布
         * clipRect：指定范围内的可以显示，范围外的无法显示
         * clipOutRect：指定范围外的可以显示，范围内的无法显示
         * 执行此操作后对后面的绘制有影响，不会对前面已绘制的产生影响
         */
//        canvas.drawRect(100, 100, 500, 500, mPaint);
//
//        canvas.clipRect(100, 100, 500, 500);
//        canvas.clipOutRect(99, 99, 501, 501);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(200, 200, 600, 600, mPaint);
//
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(50, 50, 800, 800, mPaint);

        setMatrix(canvas);
    }

    /**
     * 可以给canvas设置矩阵来对自己进行平移、旋转操作
     * @param canvas
     */
    private void setMatrix(Canvas canvas) {
        canvas.drawRect(100, 100, 500, 500, mPaint);
        Matrix matx = new Matrix();
//        matx.setTranslate(50,50);
//        matx.setRotate(45, 300, 300);
        matx.setScale(0.5f, 0.5f);
        canvas.setMatrix(matx);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(100, 100, 500, 500, mPaint);
    }
}
