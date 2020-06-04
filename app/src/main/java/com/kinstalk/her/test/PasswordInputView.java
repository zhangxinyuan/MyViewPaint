package com.kinstalk.her.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class PasswordInputView extends EditText {
    private Context mContext;

    private int mPswSize = 6;
    private int mBorderWidth = 3;
    private int mBorderRadius = 30;
    private int mBorderColor = 0xff333333;
    private int mPswColor = 0xff000000;
    private int mPswWidth = 12;
    private int mPadding = 5;

    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private Paint mPswPaint;

    private int mInputTextCount;
    private String mPsw;
    private OnPasswordChangeListener mOnPasswordChangeListener;


    public PasswordInputView(Context context) {
        this(context, null);
    }

    public PasswordInputView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        setCursorVisible(false);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mPswSize)});
        setPadding(10, 0, 0, 0);

        mPaint = new Paint();
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mBorderColor);

        mPswPaint = new Paint();
        mPswPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPswPaint.setColor(mPswColor);
        mPswPaint.setStrokeWidth(mPswWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画框
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mBorderColor);
        RectF borderRecF = new RectF(mPadding, mPadding, mWidth - mPadding, mHeight - mPadding);
        canvas.drawRoundRect(borderRecF, mBorderRadius, mBorderRadius, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        RectF contentRecF = new RectF(mBorderWidth + mBorderWidth / 2 + mPadding,
                mBorderWidth + mBorderWidth / 2 + mPadding,
                mWidth - mBorderWidth - mPadding, mHeight - mBorderWidth - mPadding);
        canvas.drawRoundRect(contentRecF, mBorderRadius, mBorderRadius, mPaint);

        //画分割线
        mPaint.setColor(mBorderColor);
        int segmentWidth = mWidth / mPswSize;
        int lineHeight = mHeight - mBorderWidth * 2 - mPadding * 2;
        for (int i = 1; i < mPswSize; i++) {
            canvas.drawLine(segmentWidth * i, mBorderWidth + mPadding, segmentWidth * i,
                    mPadding + mBorderWidth + lineHeight, mPaint);
        }

        //画圆点
        for (int i = 0; i < mInputTextCount; i++) {
            canvas.drawCircle(i * segmentWidth + segmentWidth / 2, mHeight / 2,
                    mBorderRadius, mPswPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.length() <= mPswSize) {
            mInputTextCount = text.length();
            mPsw = text.toString();
            if (mOnPasswordChangeListener != null) {
                mOnPasswordChangeListener.onPasswordChange(mPsw);
            }
        } else {
            text = mPsw;
        }
        invalidate();
    }

    public interface OnPasswordChangeListener {
        void onPasswordChange(String password);
    }

    public void setOnPasswordChangeListener(OnPasswordChangeListener listener) {
        mOnPasswordChangeListener = listener;
    }
}
