package com.fish.jetpackdemo.fragment.dialogFragment;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

public class MyView extends AppCompatTextView {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
