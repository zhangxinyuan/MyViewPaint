package com.kinstalk.her.wyviewpaint.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class MyImageView3 extends ImageView {
    public MyImageView3(Context context) {
        super(context);
    }

    public MyImageView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyImageView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("zxy", "MyImageView3: dispatchTouchEvent: " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zxy", "MyImageView3: onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }
}
