package com.fish.jetpackdemo.fragment.dialogFragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FishPureFragment extends Fragment {
    private String str = "rizi";

    FFVM ffvm;

    public FishPureFragment() {
    }

    public FishPureFragment(String str) {
        this.str = str;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ffvm = new ViewModelProvider(this).get(FFVM.class);
        MyGroup myGroup = new MyGroup(getContext());
        MyView myView = new MyView(getContext());
        myView.setText(ffvm.getSchool());
        myView.setTextSize(30);
        myGroup.addView(myView);
        myGroup.setAlpha(0.5f);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        myGroup.post(new Runnable() {
            @Override
            public void run() {
                ffvm.setSchool("new school");
                if (str.contains("fuck")) {
                    myGroup.getLayoutParams().width = 100;
                    myGroup.requestLayout();
                }
            }
        });
        return myGroup;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
