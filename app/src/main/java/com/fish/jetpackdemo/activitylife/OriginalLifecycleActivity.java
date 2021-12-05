package com.fish.jetpackdemo.activitylife;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.fish.jetpackdemo.MyApp;
import com.fish.jetpackdemo.R;
import com.fish.jetpackdemo.lifecycle.LifeActivity;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OriginalLifecycleActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, OriginalLifecycleActivity.class);
        context.startActivity(intent);
    }

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin_lifecycle);
        findViewById(R.id.btn_start_dialog).setOnClickListener((v)->{
            showDialog();
        });
        findViewById(R.id.btn_start_window).setOnClickListener((v)->{
            showWindow();
        });
        findViewById(R.id.btn_start_normal).setOnClickListener((v)->{
            LifeActivity.start(v.getContext());
        });
        findViewById(R.id.btn_start_small).setOnClickListener((v)->{
            SmallActivity.start(v.getContext());
            handler.postDelayed(()->{
                findViewById(R.id.edit).invalidate();
            }, 2000);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("pick", "you");
        outState.putByteArray("fuck", new byte[100]);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("fish", "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("fish", "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("fish", "onStop() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("fish", "onResume() called");
    }

    private void showDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage("hello dialog").create();
        alertDialog.show();
    }

    private void showWindow() {
        if (checkPermission(this)) {
            showView();
        } else {
            startActivityForResult(getPermissionIntent(this), 100);
        }
    }

    public static boolean checkPermission(@NonNull Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int op = 24;
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            try {
                Class clazz = AppOpsManager.class;
                Method method = clazz.getDeclaredMethod("checkOp", int.class, int.class, String.class);
                return AppOpsManager.MODE_ALLOWED == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
            } catch (Exception e) {
                return false;
            }
        } else {
            return true;
        }
    }

    public static Intent getPermissionIntent(@NonNull Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String brand = Build.BRAND;
            if (TextUtils.isEmpty(brand)) {
                return null;
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (checkPermission(this)) {
                showView();
            }
        }
    }

    private void showView() {
        //获取WindowManager实例，这里的App是继承自Application
        WindowManager wm = (WindowManager) MyApp.getInstance().getSystemService(Context.WINDOW_SERVICE);

        //设置LayoutParams属性
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = 400;
        layoutParams.width = 400;
        layoutParams.format = PixelFormat.RGBA_8888;

        //窗口标记属性
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //Window类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        //构造TextView
        TextView textView = new TextView(this);
        textView.setWidth(1000);
        textView.setHeight(1000);
        textView.setBackground(new ColorDrawable(Color.RED));
        textView.setText("hello windowManager");
        textView.setOnClickListener((v)->{
            wm.removeViewImmediate(textView);
        });

        //将textView添加到WindowManager
        wm.addView(textView, layoutParams);
    }

}
