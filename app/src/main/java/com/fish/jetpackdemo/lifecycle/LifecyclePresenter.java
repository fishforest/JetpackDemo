package com.fish.jetpackdemo.lifecycle;

public class LifecyclePresenter implements ILifecycle{
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
}
