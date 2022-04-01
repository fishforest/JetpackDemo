package com.fish.jetpackdemo.livedata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fish.jetpackdemo.R;
import com.fish.jetpackdemo.global.SaveTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import static androidx.lifecycle.Lifecycle.State.DESTROYED;
import static androidx.lifecycle.Lifecycle.State.STARTED;

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

//        visitViewModel = SaveTest.getIns().getVisitViewModel(this);
        visitViewModel = new ViewModelProvider(this).get(VisitViewModel .class);
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
//            new Thread(()->{
//                int a = (int)(Math.random() * 10);
//                //获取LiveData实例，更新LiveData
//                simpleLiveData.getName().postValue("singleName:" + a);
//            }).start();
            int a = (int)(Math.random() * 10);
            //获取LiveData实例，更新LiveData
            simpleLiveData.getName().postValue("singleName:" + a);

            GlobalLiveData.getInstance().getSimpleLiveData().getName().setValue("from global");
        });

        findViewById(R.id.btn_change_age).setOnClickListener((v)->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 10;
                    while (count > 0) {
                        LiveDataPostUtil.postValue(simpleLiveData.getName(), count + "");
                        count--;
                    }
                }
            }).start();
//            handler.postDelayed(runnable, 2000);
        });

        findViewById(R.id.original_callback).setOnClickListener((v)->{
            NetUtil.INSTANCE.getUserInfo(new NetUtil.InfoNotify() {
                @Override
                public void notify(int a) {
                    runOnUiThread(()->{
                        //如果Activity 正在销毁或者已经销毁，那就没必要Toast了
                        if (!LiveDataActivity.this.isFinishing() && !LiveDataActivity.this.isDestroyed()) {
                            Toast.makeText(LiveDataActivity.this, "a=" + a, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        });

        findViewById(R.id.btn_sticky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalLiveData.getInstance().getSimpleLiveData().getEasyName().setValue("easy name");
            }
        });

        handleSingleLiveData();

        GlobalLiveData.getInstance().getSimpleLiveData().getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LiveDataActivity.this, "global name:" + s, Toast.LENGTH_SHORT).show();
            }
        });

        GlobalLiveData.getInstance().getSimpleLiveData().getEasyName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LiveDataActivity.this, "global easy name:" + s, Toast.LENGTH_SHORT).show();
            }
        });
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
        //构造LiveData
        simpleLiveData = new SimpleLiveData();
        //获取LiveData实例
//        simpleLiveData.getName().observe(this, (data)-> {
//            //监听LiveData，此处的data参数类型即是为setValue(name)时name 的类型-->String
//            Toast.makeText(LiveDataActivity.this, "singleLiveData name:" + data, Toast.LENGTH_SHORT).show();
//        });

//        simpleLiveData.getName().observeForever(s -> {
//            Toast.makeText(LiveDataActivity.this, "singleLiveData name:" + s, Toast.LENGTH_SHORT).show();
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetUtil.INSTANCE.removeListener();
    }

}
