package com.kinstalk.her.chenjinshi;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class StatusBarUtil {

    public static void setStatusBarBackground(Activity activity, Bitmap bitmap) {
        setFullScreen(activity);
        addStatusView(activity, bitmap);
    }

    private static void setFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                Window window = activity.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.flags |= flagTranslucentStatus;
                window.setAttributes(lp);
            }
        }
    }

    private static void addStatusView(Activity activity, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                statusBarView.setBackground(new BitmapDrawable(activity.getResources(), bitmap));
            } else {
                statusBarView.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), bitmap));
            }
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusBarView, lp);
            View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            rootView.setPadding(0, getStatusBarHeight(activity), 0, 0);
        }
    }

    private static int getStatusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            return activity.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
