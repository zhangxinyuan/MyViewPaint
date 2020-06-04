package com.kinstalk.her.myadpter

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.content.res.Resources

/**
 *
 * 根据标准的dp width
 * 计算出目标的设备的 density
 *
 * 注意：density一旦设置，应用所有页面都会使用此density
 *
 * 可以在BaseActivity中调用，也可以在Application中registerActivityLifecycleCallbacks的回调中调用
 *
 * px = density * dp;
 * density = dpi / 160;
 * px = dp * (dpi / 160);
 *
 *
 * 如果想适配 Bitmap，需要在创建 Bitmap 时指定修改后的 DisplayMetrics
 * Bitmap bitmap = Bitmap.createBitmap(activity.getResources().getDisplayMetrics(),width,height,config);
 */
class DensityAdapter {
    companion object {
        private const val STANDARD_WIDTH_DP = 360

        private var mDensity: Float = 0F
        private var mScaledDensity: Float = 0F //字体缩放比例，默认为appDensity

        fun setDensity(application: Application, activity: Activity) {
            val displayMetrics = application.resources.displayMetrics
            if (mDensity == 0F) {
                mDensity = displayMetrics.density
                mScaledDensity = displayMetrics.scaledDensity

                //字体发生更改，重新计算scaleDensity
                application.registerComponentCallbacks(object : ComponentCallbacks {
                    override fun onConfigurationChanged(newConfig: Configuration) {
                        if (newConfig.fontScale > 0) {
                            mScaledDensity = application.resources.displayMetrics.scaledDensity
                        }
                    }

                    override fun onLowMemory() {

                    }

                })
            }
            val targetDensity = displayMetrics.widthPixels / STANDARD_WIDTH_DP.toFloat()
            val targetScaledDensity = targetDensity * (mScaledDensity / mDensity)
            val targetDensityDpi = targetDensity * 160

            val appDm = activity.resources.displayMetrics
            appDm.density = targetDensity
            appDm.scaledDensity = targetScaledDensity
            appDm.densityDpi = targetDensityDpi.toInt()
        }

        /**
         * 恢复原来的density
         */
        fun cancelAdaptScreen(app: Application, activity: Activity) {
            val systemDm = Resources.getSystem().displayMetrics
            val appDm = app.resources.displayMetrics
            val activityDm = activity.resources.displayMetrics
            activityDm.density = systemDm.density
            activityDm.scaledDensity = systemDm.scaledDensity
            activityDm.densityDpi = systemDm.densityDpi
            appDm.density = systemDm.density
            appDm.scaledDensity = systemDm.scaledDensity
            appDm.densityDpi = systemDm.densityDpi
        }
    }
}