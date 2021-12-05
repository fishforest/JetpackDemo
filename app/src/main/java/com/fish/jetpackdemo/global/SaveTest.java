package com.fish.jetpackdemo.global;

import com.fish.jetpackdemo.livedata.VisitViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SaveTest {

    VisitViewModel visitViewModel;


    static class SaveInner {
        public static SaveTest saveTest = new SaveTest();
    }

    public static SaveTest getIns() {
        return SaveInner.saveTest;
    }

    public VisitViewModel getVisitViewModel(AppCompatActivity activity) {
        if (visitViewModel == null) {
            visitViewModel = new ViewModelProvider(activity).get(VisitViewModel .class);
        }
        return visitViewModel;
    }
}
