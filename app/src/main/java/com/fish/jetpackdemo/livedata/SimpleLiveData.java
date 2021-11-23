package com.fish.jetpackdemo.livedata;

import androidx.lifecycle.MutableLiveData;

public class SimpleLiveData {
    private MutableLiveData<String> name;
    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }
}
