package com.kinstalk.her.test;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class TouchMoveView extends View {
    /**
     * View的宽高
     */
    private float width;
    private float height;

    /**
     * 触摸点相对于View的坐标
     */
    private float touchX;
    private float touchY;
    /**
     * 状态栏的高度
     */
    int barHeight;
    /**
     * 屏幕的宽高
     */
    private int screenWidth;
    private int screenHeight;

    public TouchMoveView(Context context) {
        super(context);
    }

    public TouchMoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取状态栏高度
        barHeight = getStatusBarHeight(getContext());
        screenWidth = getScreenWidth();
        screenHeight = getScreenHeight();
        setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.RED);
                touchX = event.getX();
                touchY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float nowY = event.getRawY() - touchY - barHeight;
                float nowX = event.getRawX() - touchX;
                nowX = nowX < 0 ? 0 : (nowX + width > screenWidth) ? (screenWidth - width) : nowX;
                nowY = nowY < 0 ? 0 : nowY + height > screenHeight ? screenHeight - height : nowY;
                this.setY(nowY);
                this.setX(nowX);
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.BLUE);
                touchX = 0;
                touchY = 0;
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics out = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(out);
        return out.widthPixels;
    }

    private int getScreenHeight() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics out = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(out);
        return out.heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }
}
