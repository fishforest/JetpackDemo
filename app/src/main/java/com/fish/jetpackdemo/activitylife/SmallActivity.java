package com.fish.jetpackdemo.activitylife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.fish.jetpackdemo.R;

import androidx.appcompat.app.AppCompatActivity;

public class SmallActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, SmallActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samll_layout);
        resizeWindow();
    }

    private void resizeWindow() {
        getWindow().setLayout(400, 400);
    }
}
