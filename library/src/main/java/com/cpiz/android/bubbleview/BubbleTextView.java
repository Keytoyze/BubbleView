package com.cpiz.android.bubbleview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import lombok.experimental.Delegate;

/**
 * 气泡样式的TextView控件
 * 支持自定义气泡样式
 *
 * Created by caijw on 2016/6/1.
 * https://github.com/cpiz/BubbleView
 */
public class BubbleTextView extends TextView implements BubbleStyle, BubbleCallback {
    @Delegate(types = BubbleStyle.class, excludes = Undelegateable.BubbleStyle.class)
    private final BubbleImpl mBubbleImpl = new BubbleImpl();

    public BubbleTextView(Context context) {
        this(context, null);
    }

    public BubbleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressWarnings("unused")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BubbleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mBubbleImpl.init(this, context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mBubbleImpl.updateDrawable(right - left, bottom - top, true);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        // noinspection ConstantConditions
        if (mBubbleImpl == null) {
            Log.w("BubbleView", "mBubbleImpl == null on old Android platform");
            setSuperPadding(left, top, right, bottom);
            return;
        }

        mBubbleImpl.setPadding(left, top, right, bottom);
    }

    @Override
    public void setSuperPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
    }

    @Override
    public int getSuperPaddingLeft() {
        return super.getPaddingLeft();
    }

    @Override
    public int getSuperPaddingTop() {
        return super.getPaddingTop();
    }

    @Override
    public int getSuperPaddingRight() {
        return super.getPaddingRight();
    }

    @Override
    public int getSuperPaddingBottom() {
        return super.getPaddingBottom();
    }

    @Override
    public void setSuperPressed(boolean pressed) {
        super.setPressed(pressed);
    }
}
