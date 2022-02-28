package com.fish.jetpackdemo.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class LifecyclePresenterV2 {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public LifecyclePresenterV2(Activity activity) {
        LifecycleHelper.bindLifecycle(activity, new ILifecycle() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onResume() {
                NetRequest.startRequest();
            }

            @Override
            public void onPause() {
                NetRequest.stopRequest();
            }

            @Override
            public void onStop() {

            }

            @Override
            public void onDestroy() {

            }
        });
    }
}
