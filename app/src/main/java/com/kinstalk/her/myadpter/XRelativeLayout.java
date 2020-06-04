package com.kinstalk.her.myadpter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 遍历所有子View，对子View尺寸进行缩放处理
 * 缺点: 需要针对RelativeLayout、LinearLayout等布局都进行适配
 */
public class XRelativeLayout extends RelativeLayout {
    private boolean flag;


    public XRelativeLayout(Context context) {
        super(context);
    }

    public XRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * RelativeLayout会调用两次onMeasure
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("zxy", "onMeasure");
        if (!flag) {
            flag = true;
            float scaleX = ScaleUtils.getInstance(getContext()).getWidthScaleSize();
            float scaleY = ScaleUtils.getInstance(getContext()).getHeightScaleSize();
            Log.e("zxy", "scaleX: " + scaleX + " scaleY: " + scaleY);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                //todo 需要处理WRAP_CONTENT和MATCH_PARENT的情况
                //todo 需要处理单位的问题px？dp？,默认是px，如果是dp，怎么处理
                lp.width = (int) (lp.width * scaleX);
                lp.height = (int) (lp.height * scaleY);
                lp.leftMargin = (int) (lp.leftMargin * scaleX);
                lp.topMargin = (int) (lp.topMargin * scaleY);
                lp.rightMargin = (int) (lp.rightMargin * scaleX);
                lp.bottomMargin = (int) (lp.bottomMargin * scaleY);
                child.setPadding(((int) (child.getPaddingLeft() * scaleX)),
                        ((int) (child.getPaddingTop() * scaleY)),
                        ((int) (child.getPaddingRight() * scaleX)),
                        ((int) (child.getPaddingBottom() * scaleY)));
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
