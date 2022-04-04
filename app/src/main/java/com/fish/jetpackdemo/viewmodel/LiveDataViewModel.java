package com.fish.jetpackdemo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveDataViewModel extends ViewModel {
    private MutableLiveData<String> mutableLiveData;

    public MutableLiveData<String> getLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }

        return mutableLiveData;
    }
}
