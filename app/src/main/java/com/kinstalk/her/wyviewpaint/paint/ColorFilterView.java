package com.kinstalk.her.wyviewpaint.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;

import com.kinstalk.her.wyviewpaint.R;

public class ColorFilterView extends View {

    private Paint paint;
    private Bitmap bitmap;

    public ColorFilterView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_demo);

        /**
         *  LightingColorFilter
         *  光照色彩过滤器，参数mul：用来和像素颜色值相乘，参数add：用来和像素值相加
         *
         *  R' = R * colorMultiply.R + colorAdd.R
         *  G' = G * colorMultiply.G + colorAdd.G
         *  B' = B * colorMultiply.B + colorAdd.B
         *
         *  结果取值在[0..255]
         *  R' = R * colorMultiply.R / 0xff + colorAdd.R
         *  G' = G * colorMultiply.G /0xff + colorAdd.G
         *  B' = B * colorMultiply.B /0xff + colorAdd.B
         */
        //原图效果
//        ColorFilter colorFilter = new LightingColorFilter(0xffffff, 0x000000);
//        paint.setColorFilter(colorFilter);

        // 红色去除效果，只剩下绿色和蓝色
//        ColorFilter colorFilter = new LightingColorFilter(0x00ffff, 0x000000);
//        paint.setColorFilter(colorFilter);

        //红色增强
//        ColorFilter colorFilter = new LightingColorFilter(0xffffff, 0x800000);
//        paint.setColorFilter(colorFilter);

        ColorFilter colorFilter = new LightingColorFilter(0xffffff, 0x800000);

        /**
         * PorterDuffColorFilter
         * 颜色混合过滤器
         * color：和哪个颜色进行混合，mode：混合模式
         * 原图就是dst，颜色值就是src
         */
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DST);


        /**
         * 颜色矩阵是一个4*5的矩阵：
         * 一行表示一个原色
         * [ a, b, c, d, e,   //R
         *   f, g, h, i, j,   //G
         *   k, l, m, n, o,   //B
         *   p, q, r, s, t ]  //A
         *
         * 一个像素点的颜色值也是个矩阵：
         * [R, G, B, A]
         *
         * 当使用颜色矩阵对像素点的颜色进行修改时，就是对两个矩阵进行相乘运算：
         *
         * [ a*R , b*G , c*B , d*A + e,    //新的R
         *   f*R , g*G , h*B , i*A + j,    //新的G
         *   k*R , l*G , m*B , n*A + o,    // 新的B
         *   p*R , q*G , r*B , s*A + t ];  //新的A
         *
         * 从上面的运算可以看出，每一行决定了一个原色，最后的e、j、o、t则表示对这个行代表的颜色的偏移量
         *
         *
         * 原图：
         * float[] matrix = {
         *                 1, 0, 0, 0, 0,
         *                 0, 1, 0, 0, 0,
         *                 0, 0, 1, 0, 0,
         *                 0, 0, 0, 1, 0
         *         };
         * 黑白：
         * float[] matrix = {
         *                 0.3f, 0.3f, 0.3f, 0.3f, 0,
         *                 0.3f, 0.3f, 0.3f, 0.3f, 0,
         *                 0.3f, 0.3f, 0.3f, 0.3f, 0,
         *                 0, 0, 0, 1, 0
         *         };
         * 底片效果：（底色 = 255 - 颜色）
         * float[] matrix = {
         *                 -1, 0, 0, 0, 255,
         *                 0, -1, 0, 0, 255,
         *                 0, 0, -1, 0, 255,
         *                 0, 0, 0, 1, 0
         *         };
         */

        ColorMatrix colorMatrix = new ColorMatrix();
        //亮度调节
//        colorMatrix.setScale(1, 1, 1, 1);
        // 色彩饱和度，0无色彩，1原图、>1饱和度增强
        colorMatrix.setSaturation(0);

        //色调调节，axis表示已某颜色轴为轴心进行旋转，0表示红色轴，1表示绿色轴，2表示蓝色轴。degrees表示旋转角度
//        colorMatrix.setRotate(2, 45);
        /**
         * ColorMatrixColorFilter
         * 通过4x5颜色矩阵变换颜色的颜色过滤器
         * 可用于改变像素的饱和度，从YUV转换为RGB等。
         */
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixColorFilter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
