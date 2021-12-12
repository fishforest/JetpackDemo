package com.fish.jetpackdemo.activitylife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.util.AttributeSet;
import android.util.Log;

import java.util.List;

import androidx.appcompat.widget.AppCompatEditText;

public class SaveEditText extends AppCompatEditText {
    public SaveEditText(Context context) {
        super(context);
    }

    public SaveEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }
}
