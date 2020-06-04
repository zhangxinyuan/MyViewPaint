package com.kinstalk.her.myadpter;

import android.content.Context;

public class ScreenAdapter {
    private final static int STAND_WIDTH = 1080;//px
    private final static int STAND_HEIGHT = 1920;//px

    private final static int TARGET_WIDTH = 360;//dp
    private final static int TARGET_HEIGHT = 640;//dp

    /**
     * 按宽进行适配
     *
     * @return
     */
    private static int getTargetDensity() {
        return STAND_WIDTH / TARGET_WIDTH;
    }

    /**
     * 计算适配尺寸
     *
     * @param pxSize
     * @return
     */
    public static int getDpSize(int pxSize) {
        return pxSize / getTargetDensity();
    }

    public static int getPxSize(Context context, int pxSize) {
        return dp2px(context, getDpSize(pxSize));
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }
}