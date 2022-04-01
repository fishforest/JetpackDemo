package com.fish.jetpackdemo.livedata;

public class GlobalLiveData {
    private static class Inner {
        static GlobalLiveData ins = new GlobalLiveData();
    }

    public static GlobalLiveData getInstance() {
        return Inner.ins;
    }

    private SimpleLiveData simpleLiveData;

    private GlobalLiveData() {
        simpleLiveData = new SimpleLiveData();
    }

    public SimpleLiveData getSimpleLiveData() {
        return simpleLiveData;
    }
}
