package com.fish.jetpackdemo.livedata;


import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LiveDataPostUtil {
    private static Handler handler;
    public static <T> void postValue(MutableLiveData<T> liveData, T data) {
        if (liveData == null || data == null)
            return;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new CustomRunnable<>(liveData, data));
    }

    static class CustomRunnable<T> implements Runnable{
        private MutableLiveData<T> liveData;
        private T data;

        public CustomRunnable(MutableLiveData<T> liveData, T data) {
            this.liveData = liveData;
            this.data = data;
        }

        @Override
        public void run() {
            liveData.setValue(data);
        }
    }
}
