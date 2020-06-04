package com.kinstalk.her.wyviewpaint.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton2 extends Button {

    public MyButton2(Context context) {
        super(context);
    }

    public MyButton2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyButton2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("MyButton2", "dispatchTouchEvent：" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MyButton2", "" + isClickable() + isLongClickable() + isContextClickable());
        Log.e("MyButton2", "onTouchEvent：" + event.getAction());
        return super.onTouchEvent(event);
    }
}
