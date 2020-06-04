package com.kinstalk.her.myadpter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kinstalk.her.wyviewpaint.R;

/**
 * 百分比布局
 * 缺点：只适用于一层布局
 */
public class PercentLayout extends RelativeLayout {
    private boolean flag;

    public PercentLayout(Context context) {
        super(context);
    }

    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!flag) {
            flag = true;
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                ViewGroup.LayoutParams params = child.getLayoutParams();
                if (checkLayoutParams(params)) {
                    LayoutParams lp = ((LayoutParams) params);
                    float widthPercent = lp.widthPercent;
                    float heightPercent = lp.heightPercent;
                    float leftMarginPercent = lp.leftMarginPercent;
                    float rightMarginPercent = lp.rightMarginPercent;
                    float topMarginPercent = lp.topMarginPercent;
                    float bottomMarginPercent = lp.bottomMarginPercent;
                    if (widthPercent > 0) {
                        lp.width = (int) (width * widthPercent);
                    }
                    if (heightPercent > 0) {
                        lp.height = (int) (height * heightPercent);
                    }
                    if (leftMarginPercent > 0) {
                        lp.leftMargin = (int) (width * leftMarginPercent);
                    }
                    if (rightMarginPercent > 0) {
                        lp.rightMargin = (int) (width * rightMarginPercent);
                    }
                    if (topMarginPercent > 0) {
                        lp.topMargin = (int) (height * topMarginPercent);
                    }
                    if (bottomMarginPercent > 0) {
                        lp.bottomMargin = (int) (height * bottomMarginPercent);
                    }
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof PercentLayout.LayoutParams;
    }

    /**
     * 1、创建自定义属性
     * 2、在容器中去创建一个静态内部类LayoutParams
     * 3、在LayoutParams构造方法中获取自定义属性
     * 4、onMeasure中给子控件设置修改后的属性值
     */

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        public float widthPercent;
        public float heightPercent;
        public float leftMarginPercent;
        public float rightMarginPercent;
        public float topMarginPercent;
        public float bottomMarginPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.PercentLayout_Layout);
            widthPercent = a.getFraction(R.styleable.PercentLayout_Layout_widthPercent, 1, 2, 0);
            heightPercent = a.getFraction(R.styleable.PercentLayout_Layout_heightPercent, 1, 2, 0);
            leftMarginPercent = a.getFraction(R.styleable.PercentLayout_Layout_leftMarginPercent, 1, 2, 0);
            topMarginPercent = a.getFraction(R.styleable.PercentLayout_Layout_topMarginPercent, 1, 2, 0);
            rightMarginPercent = a.getFraction(R.styleable.PercentLayout_Layout_rightMarginPercent, 1, 2, 0);
            bottomMarginPercent = a.getFraction(R.styleable.PercentLayout_Layout_bottomMarginPercent, 1, 2, 0);
            a.recycle();
        }
    }
}
