package com.kinstalk.her.wyviewpaint.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Path类有OP、FillType、Direction三个枚举类
 * 分别表示，对两条path进行的操作（联合、相减等）、填充方式、闭合图形的绘制方向：顺时针和逆时针
 */
public class PathView extends View {

    Paint mPaint;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        drawOp(canvas);
//        drawFillType(canvas);
//        drawFuc(canvas);
        drawAdd(canvas);
    }

    private void drawAdd(Canvas canvas) {
        Path path = new Path();
        /**
         * 在指定范围内绘制圆弧
         */
        path.addArc(100, 100, 200, 200, 0, -90);

        /**
         * forcemoveto如果为true,则会从原点绘制一条圆弧，同addArc
         * 如果为false，则会从现在的点连接到原点，然后再绘制圆弧
         */
        path.arcTo(100, 100, 200, 200, 0,90, false);


        /**
         * 在指定矩形区域绘制椭圆
         */
        path.addOval(100, 300, 500, 600, Path.Direction.CW);

        /**
         * 绘制矩形
         */
        path.addRect(100, 600, 500, 800, Path.Direction.CW);

        /**
         * 绘制圆角矩形
         * 通常用来和我们想要裁剪的图进行图层混合(PorterDuff)，生成我们想要的图形
         */
        path.addRoundRect(200, 900, 800, 1200, 10, 20, Path.Direction.CW);

        canvas.drawPath(path, mPaint);
    }

    private void drawFuc(Canvas canvas) {
        Path path = new Path();
        /**
         * 移动到某一点
         */
        path.moveTo(100, 200);

        /**
         *相对现在点上再移动
         */
        path.rMoveTo(100, 100);

        /**
         * 直接连接到某一点
         */
//        path.lineTo(300, 300);

        /**
         * 终点为：相对于起点，再移动一段距离后的点
         */
        path.rLineTo(100, 0);

        path.lineTo(50, 100);

        /**
         * 将起点和终点连接起来
         */
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private void drawFillType(Canvas canvas) {
        Path path = new Path();
        path.addCircle(200, 200, 100, Path.Direction.CW);

        path.addCircle(200, 350, 100, Path.Direction.CW);

        /**
         * 填充两条path的合并
         */
//        path.setFillType(Path.FillType.WINDING);

        /**
         * 填充两条path没有交集的部分
         */
//        path.setFillType(Path.FillType.EVEN_ODD);

        /**
         * 填充两条path合并之外的部分
         */
//        path.setFillType(Path.FillType.INVERSE_WINDING);

        /**
         * 填充两条path合并之外的部分和交集部分
         */
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);

        canvas.drawPath(path, mPaint);
    }

    private void drawOp(Canvas canvas) {

        Path path1 = new Path();
        path1.addCircle(200, 200, 100, Path.Direction.CW);

        Path path2 = new Path();
        path2.addCircle(200, 350, 100, Path.Direction.CW);

        /**
         * path1-path2
         */
//        path1.op(path2, Path.Op.DIFFERENCE);

        /**
         * 留下相交部分
         */
//        path1.op(path2, Path.Op.INTERSECT);

        /**
         * 合并
         */
//        path1.op(path2, Path.Op.UNION);

        /**
         * 不相交的地方
         * INTERSECT模式取反
         */
//        path1.op(path2, Path.Op.XOR);

        /**
         * path2-path1
         */
        path1.op(path2, Path.Op.REVERSE_DIFFERENCE);


        canvas.drawPath(path1, mPaint);
//        canvas.drawPath(path2, mPaint);
    }
}
