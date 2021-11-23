package com.fish.jetpackdemo.livedata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.fish.jetpackdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LiveDataActivity extends AppCompatActivity {

    private final String TAG = LiveDataActivity.class.getSimpleName();

    private Handler handler = new Handler(Looper.getMainLooper());

    public static void start(Context context) {
        Intent intent = new Intent(context, LiveDataActivity.class);
        context.startActivity(intent);
    }

    private VisitViewModel visitViewModel;
    private SimpleLiveData simpleLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);

        visitViewModel = new ViewModelProvider(this).get(VisitViewModel.class);
        visitViewModel.getName().observe(this, (data)-> {
            Log.d(TAG, "observe name change:" + data);
        });

        visitViewModel.getAgeList().observe(this, (data)-> {
            Log.d(TAG, "observe ageList change:");
            for (Integer age : data) {
                Log.d(TAG, "age:" + age);
            }
        });

        findViewById(R.id.btn_change_name).setOnClickListener((v)->{
            int a = (int)(Math.random() * 10);
//            visitViewModel.getName().setValue("name" + a);
            new Thread(()->{

            }).start();
            simpleLiveData.getName().setValue("singleName" + a);
        });

        findViewById(R.id.btn_change_age).setOnClickListener((v)->{
            handler.postDelayed(runnable, 2000);
        });

        handleSingleLiveData();
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int a = (int)(Math.random() * 100);
            List<Integer> list = new ArrayList<>();
            list.add(a);
            list.add(a + 1);
            visitViewModel.getAgeList().postValue(list);
            handler.postDelayed(this, 2000);
        }
    };


    private void handleSingleLiveData() {
        simpleLiveData = new SimpleLiveData();
        simpleLiveData.getName().observe(this, (data)-> {
            Log.d(TAG, "singleLiveData name:" + data);
        });
    }
}
