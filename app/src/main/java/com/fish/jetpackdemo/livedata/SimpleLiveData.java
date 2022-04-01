package com.fish.jetpackdemo.livedata;

import androidx.lifecycle.MutableLiveData;

public class SimpleLiveData {
    //LiveData 接收泛型参数
    private MutableLiveData<String> name;

    private EasyLiveData<String> easyName;

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }

    public EasyLiveData<String> getEasyName() {
        if (easyName == null) {
            easyName = new EasyLiveData<>();
        }
        return easyName;
    }
}
