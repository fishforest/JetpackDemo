package com.fish.jetpackdemo.fragment.dialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.fish.jetpackdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistry;
import androidx.viewpager2.widget.ViewPager2;

public class FishFragmentActivity extends AppCompatActivity {

    private FishViewModel viewModel;

    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        if (savedInstanceState != null)
//            savedInstanceState.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key").remove("android:support:fragments");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment);
//        viewModel = new ViewModelProvider(this).get(FishViewModel.class);
//        viewModel.setName("fuck");

//        FishDialogFragment fishDialogFragment = new FishDialogFragment();
//        fishDialogFragment.show(getSupportFragmentManager(), "dd");

//        MyView myView = new MyView(this);
//        myView.setText("hello world");
//        myView.setTextSize(30);
//        setContentView(myView);

        FishPureFragment fishPureFragment2 = new FishPureFragment("fuck you");


        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.root, fishPureFragment2).commit();
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FishPureFragment fishPureFragment2 = new FishPureFragment();
                getSupportFragmentManager().beginTransaction().remove(fishPureFragment2).commit();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FishPureFragment fishPureFragment2 = new FishPureFragment("reach");
                getSupportFragmentManager().beginTransaction().add(R.id.root, fishPureFragment2).commit();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                MyGroup myGroup = new MyGroup(v.getContext());
                MyView myView = new MyView(v.getContext());
                myView.setText("dialog");
                myView.setTextSize(30);
                myGroup.addView(myView);
                myGroup.setAlpha(0.5f);
                dialog.setContentView(myGroup);
                dialog.show();
            }
        });
//
//        FishPureFragment fishPureFragment = new FishPureFragment("hello world1");
//        getSupportFragmentManager().beginTransaction().add(R.id.root, fishPureFragment, "dd").commit();


        viewPager2 = findViewById(R.id.vp);
//        viewPager2.setVisibility(View.GONE);
        List<FishPureFragment> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            FishPureFragment fishPureFragment1 = new FishPureFragment("fragment" + i);
            list.add(fishPureFragment1);
        }
        VPAdapter vpAdapter = new VPAdapter(this, list);
        viewPager2.setAdapter(vpAdapter);
        viewPager2.setOffscreenPageLimit(1);
//        viewPager2.setCurrentItem(1,false);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
