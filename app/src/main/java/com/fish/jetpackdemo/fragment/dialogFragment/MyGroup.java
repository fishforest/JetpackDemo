package com.fish.jetpackdemo.fragment.dialogFragment;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class MyGroup extends FrameLayout {
    public MyGroup(@NonNull Context context) {
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
