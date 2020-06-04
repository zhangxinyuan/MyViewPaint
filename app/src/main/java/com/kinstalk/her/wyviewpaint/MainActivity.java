package com.kinstalk.her.wyviewpaint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.kinstalk.her.chenjinshi.StatusBarUtil;
import com.kinstalk.her.wyviewpaint.paint.ExposeView;

public class MainActivity extends AppCompatActivity {

    private ExposeView exposeView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setStatusBarBackground(this, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_bg));
//         ViewGroup decor = (ViewGroup) getWindow().getDecorView();
//        exposeView = new ExposeView(this);
//        decor.addView(exposeView);
//        final TextView textView = findViewById(R.id.tv);
//        textView.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("zxy", "textView width: " + textView.getWidth() + " textView height: " + textView.getHeight());
//                Log.e("zxy", "parent width: " + ((XRelativeLayout) textView.getParent()).getWidth()
//                        + "height" + ((XRelativeLayout) textView.getParent()).getHeight());
//            }
//        });
        Bitmap originBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_demo);
        findViewById(R.id.origin_image).setBackground(new BitmapDrawable(originBitmap));
        Bitmap grayBitmap = grayImageView(originBitmap);
        findViewById(R.id.gray_image).setBackground(new BitmapDrawable(grayBitmap));
    }

    private Bitmap grayImageView(Bitmap originalBitmap) {
        Bitmap toBitmap = Bitmap
                .createBitmap(originalBitmap.getWidth(),
                        originalBitmap.getHeight(),
                        Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(toBitmap);
        Paint paint = new Paint();
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);//设置色彩饱和度为0
        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(originalBitmap, 0, 0, paint);
        return toBitmap;
    }

    public void click(View view) {
        Log.e("zxy", "click");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("zxy", "dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zxy", "onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }

    public void click2(View view) {
        Log.e("zxy", "click2");
    }

    public void click3(View view) {
        Log.e("zxy", "clickBtn3");
    }

    public void clickBtn1(View view) {
        Log.e("zxy", "clickBtn1");
    }

    public void clickBtn2(View view) {
        Log.e("zxy", "clickBtn2");
    }

    public void start2(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

}
