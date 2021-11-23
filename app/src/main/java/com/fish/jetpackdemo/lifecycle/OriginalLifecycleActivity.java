package com.fish.jetpackdemo.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OriginalLifecycleActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, OriginalLifecycleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
