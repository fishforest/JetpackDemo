package com.fish.jetpackdemo.livedata;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisitViewModel extends ViewModel {
    private MutableLiveData<String> name;
    private MutableLiveData<List<Integer>> ageList;

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }

    public MutableLiveData<List<Integer>> getAgeList() {
        if (ageList == null) {
            ageList = new MutableLiveData<>();
        }
        return ageList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
