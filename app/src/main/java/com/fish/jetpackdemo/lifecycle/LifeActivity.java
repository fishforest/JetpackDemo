package com.fish.jetpackdemo.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fish.jetpackdemo.R;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeActivity extends ComponentActivity {

    private final String TAG = LifeActivity.class.getSimpleName();

    private LifecyclePresenter lifecyclePresenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, LifeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        LifecycleObserver observerV7 = new MyObserver7();
        getLifecycle().addObserver(observerV7);

//        LifecycleObserver observerV8 = new MyObserver8();
//        getLifecycle().addObserver(observerV8);

        lifecyclePresenter = new LifecyclePresenter();
    }

    //注解的实现方式
    class MyObserver7 implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void start() {
            Log.d(TAG, "start");
        }
    }

    //Java8 方式
//    class MyObserver8 implements DefaultLifecycleObserver {
//        @Override
//        public void onStart(LifecycleOwner owner) {
//            Log.d(TAG, "start");
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecyclePresenter.onResume();
        NetRequest.startRequest();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecyclePresenter.onPause();
        NetRequest.stopRequest();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
