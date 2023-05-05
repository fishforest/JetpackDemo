package com.fish.jetpackdemo.fragment.dialogFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FishFragmentActivity extends AppCompatActivity {

    private FishViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        viewModel = new ViewModelProvider(this).get(FishViewModel.class);
//        viewModel.setName("fuck");

        FishDialogFragment fishDialogFragment = new FishDialogFragment();
        fishDialogFragment.show(getSupportFragmentManager(), "dd");
    }
}
