package com.kinstalk.her.wyviewpaint.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CircleView extends View {

    private Paint mPaint;

    public CircleView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //想要画一个直径为屏幕宽度的圆，因为线宽会沿着圆边缘向两边均匀扩散，所以看到的圆不是width/2, 差了一半的线宽
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2 + 50, mPaint);
    }
}
