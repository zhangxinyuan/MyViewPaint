package com.kinstalk.her.wyviewpaint.paint;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.SweepGradient;

public class MyPaint {

    public MyPaint() {
        // Paint是一个class，指油漆或绘画颜料，它一些属性：
        // Style，内部枚举类，样式有fill（填充)、stroke(描边）、FILL_AND_STROKE（填充加描边）
        // Cap，内部枚举类，指线头和线尾的样式，有BUTT（方形，但不会超过设置的path）、ROUND(圆形)、SQUARE（方形)
        // Join，内部枚举类，指拐角的样式，有MITER（锐角）、ROUND（圆角）、BEVEL（切角）
        // Align，内部枚举类，指文字的对齐方式，LEFT、CENTER、RIGHT
        // FontMetrics和FontMetricsInt，指文字的属性：基线、行间距等
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setARGB(255, 255, 255, 0);
        paint.setAlpha(200);//0为全透明，值越大月不透明，取值范围0-255

        paint.setAntiAlias(true);//抗锯齿效果
        paint.setFilterBitmap(true);//设置双线性过滤，也可以起到抗锯齿效果
        paint.setStyle(Paint.Style.FILL);//设置填充样式
        paint.setStrokeWidth(2);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        //着色器，指渲染颜色的模式（颜色的水平梯度变化），有三种TileMode：CLAMP（尾部延伸）、REPEAT(重复)、MIRROR（镜像）
        //我们常用的是系统提供的四个子类：LinearGradient（线性梯度渲染）、RadialGradient（环形梯度渲染）、SweepGradient（扫描渲染）、BitmapShader（位图渲染）
        // 还有一个ComposeShader, 组合以上四个shader
        paint.setShader(new SweepGradient(200, 200, Color.RED, Color.BLUE));
        paint.setColorFilter(new LightingColorFilter(0x00ffff, 0xffffff));//设置颜色过滤器
        paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.INNER));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));

        //text相关
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(10);
        paint.setTextScaleX(2);
    }
}
