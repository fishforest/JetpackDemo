package com.fish.jetpackdemo.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fish.jetpackdemo.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeActivity extends AppCompatActivity {

    private final String TAG = LifeActivity.class.getSimpleName();

    public static void start(Context context) {
        Intent intent = new Intent(context, LifeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        getLifecycle().addObserver(new MyObserver());
    }

    class MyObserver implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        void start() {
            Log.d(TAG, "start");
        }
    }

}
