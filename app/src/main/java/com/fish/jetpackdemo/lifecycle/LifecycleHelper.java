package com.fish.jetpackdemo.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class LifecycleHelper {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static void bindLifecycle(Activity activity, ILifecycle iLifecycle) {
        if (activity == null)
            return;
        activity.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                if (iLifecycle != null)
                    iLifecycle.onResume();
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                if (iLifecycle != null)
                    iLifecycle.onPause();
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }
}
