package com.kinstalk.her.wyviewpaint.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.View;

import com.kinstalk.her.wyviewpaint.R;

public class ShaderView extends View {

    private Paint paint;

    public ShaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        /**
         * LinearGradient
         * 线性渐变
         * x0,y0 起始坐标
         * x1, y1 结束坐标
         * color0, 渐变开始颜色，十六进制颜色值，必须带有alpha
         * color 渐变结束颜色
         * colors 渐变数组
         * positions 位置数组，position取值范围[0,1],作用是指定某个位置的颜色值。如果传null,就线性变化。
         * tile, 用于指定控件区域大于渐变区域时，空白区域的颜色填充模式
         * 想要改变渐变方向，可以通过控制起始坐标
         */
        LinearGradient linearGradient = new LinearGradient(0, 0, 200, 300,
                new int[]{Color.RED, Color.GREEN, Color.BLUE},
                null, Shader.TileMode.CLAMP);
        /**
         * 放射状渐变
         */
        RadialGradient radialGradient = new RadialGradient(400, 400, 200,
                new int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.MIRROR);
        /**
         * (雷达)扫描状渐变
         */
        SweepGradient sweepGradient = new SweepGradient(400, 400,
                new int[]{Color.RED, Color.BLUE, Color.GREEN}, new float[]{0.25f, 0.5F, 0});
        /**
         * 位图渲染
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_demo);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        /**
         * 组合渲染
         * 可以组合A、B两种渲染器，并指定混合模式
         * 将A渲染器结果作为dst,B渲染器结果作为src
         */
        ComposeShader composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.MULTIPLY);
        paint.setShader(composeShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle(400, 400, 400, paint);
        canvas.drawRect(0, 0, 800, 800, paint);
    }
}
