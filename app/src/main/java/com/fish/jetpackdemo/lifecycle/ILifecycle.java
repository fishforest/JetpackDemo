package com.fish.jetpackdemo.lifecycle;

interface ILifecycle {
    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
