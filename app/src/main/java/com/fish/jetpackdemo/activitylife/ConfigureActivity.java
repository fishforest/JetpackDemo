package com.fish.jetpackdemo.activitylife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;

import com.fish.jetpackdemo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigureActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ConfigureActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_layout);
        findViewById(R.id.button).setOnClickListener((v -> {
            ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.tls);
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("say", "hello world");
        //存储2M 数据
//        outState.putByteArray("big", new byte[1024*1024*2]);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String restore = savedInstanceState.getString("say");
        Log.d("fish", restore);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState,  PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
