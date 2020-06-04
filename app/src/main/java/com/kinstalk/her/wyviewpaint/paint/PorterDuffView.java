package com.kinstalk.her.wyviewpaint.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;

import com.kinstalk.her.wyviewpaint.R;

public class PorterDuffView extends View {
    Paint mPaint;
    Bitmap bitmap1;
    Bitmap bitmap2;
    private final PorterDuffXfermode porterDuffXfermode;

    public PorterDuffView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        bitmap1 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.bg_launcher_tab), 0, 0, 1920, 180);
        bitmap2 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_bg), 0, 0, 190, 180);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.ADD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //dst 先绘制的是表示dst, 指的是下层图像
        canvas.drawBitmap(bitmap1, 0, 0, mPaint);
        mPaint.setXfermode(porterDuffXfermode);
        //src 后绘制的表示为src, 指的是上层图像
        canvas.drawBitmap(bitmap2, 0, 0, mPaint);
    }
}
