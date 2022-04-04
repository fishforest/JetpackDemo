package com.fish.jetpackdemo.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.fish.jetpackdemo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelActivity extends AppCompatActivity {

    private MoneyViewModel moneyViewModel;

    private MyViewModel myViewModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, ViewModelActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        moneyViewModel = new ViewModelProvider(this).get(MoneyViewModel.class);
        myViewModel = new MyViewModel();

        TextView tvVM = findViewById(R.id.tv_vm);
        tvVM.setText(moneyViewModel.getName());

        TextView tvMyVM = findViewById(R.id.tv_my_vm);
        tvMyVM.setText(myViewModel.getName());

        Button btnChange = findViewById(R.id.btn_change);
        btnChange.setOnClickListener((v) -> {
            moneyViewModel.setName("官方 ViewModel 改变");
            tvMyVM.setText(moneyViewModel.getName());

            myViewModel.setName("我的 ViewModel 改变");
            tvVM.setText(myViewModel.getName());
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
