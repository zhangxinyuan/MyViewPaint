package com.kinstalk.her.wyviewpaint.paint;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.view.View;

public class PathEffectView extends View {
    private Paint mPaint;
    private Path mPath;

    public PathEffectView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 0; i < 40; i++) {
            mPath.lineTo(i * 40, (float) (Math.random() * 100));
        }
        mPath.close();

        /**
         * 圆角路径效果
         * 将路径的拐角画成圆的
         */
        PathEffect effect = new CornerPathEffect(10);

        /**
         * 偏移路径效果
         * 在原路径上进行上下偏移
         * segmentLength：线段长度
         * deviation：偏离长度
         */
        PathEffect effect1 = new DiscretePathEffect(20f, 20f);

        /**
         * 虚线路径偏移
         * intervals 间隔数组，必须是大于2的偶个条目数组。奇数下标的值表示绘制线的长，偶数下标的值表示间隔的长度
         * phase 偏移，动态改变该值，就可以形成动画
         */
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{20f, 5f, 40f,10f}, 0f);

        /**
         * 形状虚线路径偏移
         * shape 指定的形状
         * advance 每个形状之间的距离
         * phase 偏移
         * style 在填充时如何变换每个位置的形状
         */
        Path p = new Path();
        p.addCircle(5, 5, 5, Path.Direction.CCW);
        PathDashPathEffect pathDashPathEffect = new PathDashPathEffect(p, 20, 0,
                PathDashPathEffect.Style.MORPH);
        
        mPaint.setPathEffect(pathDashPathEffect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }
}
