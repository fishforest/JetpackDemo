package com.fish.jetpackdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.fish.jetpackdemo.dialog.BottomDialogFragment;
import com.fish.jetpackdemo.dialog.MyFragment;
import com.fish.jetpackdemo.dialog.Purefragment;
import com.fish.jetpackdemo.lifecycle.LifeActivity;
import com.fish.jetpackdemo.livedata.LiveDataActivity;
import com.fish.jetpackdemo.uiflush.FlushUIActivity;
import com.fish.jetpackdemo.uiflush.MyImageView;
import com.fish.jetpackdemo.activitylife.OriginalLifecycleActivity;
import com.fish.jetpackdemo.viewmodel.ViewModelActivity;
import com.fish.shellutil.ShellUtil;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler(Looper.myLooper());

    private TextView textView;
    private MyImageView imageView;
    private int count = 0;

    private Bitmap bitmap;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            textView.setText(count++ + "");
            imageView.requestLayout();
            imageView.setImageBitmap(bitmap);
            handler.postDelayed(this::run, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.iv);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tls);

        findViewById(R.id.btn_original_lifecycle).setOnClickListener((v) -> {
            OriginalLifecycleActivity.start(v.getContext());
//            handler.postDelayed(runnable, 1000);
        });
        findViewById(R.id.btn_test_viewmodel).setOnClickListener((v) -> {
            ViewModelActivity.start(v.getContext());
        });

        findViewById(R.id.btn_test_lifecycle).setOnClickListener((v)->{
            LifeActivity.start(v.getContext());
        });

        findViewById(R.id.btn_test_livedata).setOnClickListener((v)->{
//            LiveDataActivity.start(v.getContext());
            (new BottomDialogFragment()).show(MainActivity.this.getSupportFragmentManager(), "fish");

        });

        findViewById(R.id.btn_test_flush_ui).setOnClickListener((v)->{
//            FlushUIActivity.start(v.getContext());

            (new MyFragment()).show(MainActivity.this.getSupportFragmentManager(), "fish");

//            addFragment(new Purefragment());
        });

        findViewById(R.id.btn_test_su).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int granted = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (granted != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
                }
//                new ShellUtil() {
//
//                    @Override
//                    protected ArrayList<String> getCommandsToExecute() {
//                        ArrayList<String> list = new ArrayList<>();
//                        list.add("ls /data/data");
//                        return list;
//                    }
//                }.execute();

                ShellUtil.execSuCmd("ls /data");
//                ShellUtil.execute("ls /data/data");
            }
        });

    }

    private void addFragment(Fragment fragment) {
//        list.add(fragment);
        //获取Fragment管理对象
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务
        //添加fragment
        transaction.add(R.id.container, fragment);
        //提交动作
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int a = 5;
    }
}