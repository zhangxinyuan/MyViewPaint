package com.kinstalk.her.myadpter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class ScaleUtils {
    //标准设计尺寸，根据此进行比例缩放
    private static final float STANDARD_WIDTH = 1080;
    private static final float STANDARD_HEIGHT = 1920;

    private static ScaleUtils utils;

    //屏幕真实宽高
    private float mDisplayWidth;
    private float mDisplayHeight;

    private ScaleUtils(Context context) {
        if (mDisplayHeight == 0 || mDisplayWidth == 0) {
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                DisplayMetrics outMetrics = new DisplayMetrics();
                //不包含底部导航栏
                manager.getDefaultDisplay().getMetrics(outMetrics);
                //真正手机屏幕的尺寸
//                manager.getDefaultDisplay().getRealMetrics(outMetrics);
                //判断横竖屏
                if (outMetrics.widthPixels > outMetrics.heightPixels) {
                    mDisplayWidth = outMetrics.heightPixels;
                    //如果设计稿是不包含状态栏的，那么需要减去状态栏高度
                    mDisplayHeight = outMetrics.widthPixels - getSttusBarHeight(context);
                } else {
                    mDisplayWidth = outMetrics.widthPixels;
                    //如果设计稿是不包含状态栏的，那么需要减去状态栏高度
                    mDisplayHeight = outMetrics.heightPixels - getSttusBarHeight(context);
                }
            }
            Log.e("zxy", "mDisplayWidth: " + mDisplayWidth);
            Log.e("zxy", "mDisplayHeight: " + mDisplayHeight);
        }
    }

    public static ScaleUtils getInstance(Context context) {
        if (utils == null) {
            utils = new ScaleUtils(context);
        }
        return utils;
    }

    /**
     * 获取水平方向的缩放比例
     *
     * @return
     */
    public float getWidthScaleSize() {
        return mDisplayWidth / STANDARD_WIDTH;
    }

    /**
     * 获取垂直方向的缩放比例
     *
     * @return
     */
    public float getHeightScaleSize() {
        return mDisplayHeight / STANDARD_HEIGHT;
    }

    public int getSttusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    public static void cancelAdaptScreen(Application app, Activity activity) {
        final DisplayMetrics systemDm = Resources.getSystem().getDisplayMetrics();
        final DisplayMetrics appDm = app.getResources().getDisplayMetrics();
        final DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();
        activityDm.density = systemDm.density;
        activityDm.scaledDensity = systemDm.scaledDensity;
        activityDm.densityDpi = systemDm.densityDpi;
        appDm.density = systemDm.density;
        appDm.scaledDensity = systemDm.scaledDensity;
        appDm.densityDpi = systemDm.densityDpi;
    }
}
