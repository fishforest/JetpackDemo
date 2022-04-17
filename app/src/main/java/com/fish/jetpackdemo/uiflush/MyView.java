package com.fish.jetpackdemo.uiflush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        requestLayout();
        invalidate();
        Log.d("fish1", "onMeasure called");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        requestLayout();
        invalidate();
        Log.d("fish1", "onLayout called");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        requestLayout();
//        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 500) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = 1000;
            setLayoutParams(layoutParams);
            Log.d("fish1", "onDraw called");
        }
    }
}
