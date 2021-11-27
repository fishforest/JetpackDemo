package com.fish.jetpackdemo.uiflush;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fish.jetpackdemo.R;

import androidx.appcompat.app.AppCompatActivity;

public class FlushUIActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, FlushUIActivity.class);
        context.startActivity(intent);
    }


    TextView textView;

    private Runnable requestRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("fish", "request layout call");
            textView.requestLayout();
            textView.postDelayed(this, 1000);
        }
    };

    private Runnable invalidateRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("fish", "request invalidate call");
            textView.invalidate();
            textView.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flush_ui);

        textView = findViewById(R.id.tv);

        findViewById(R.id.btn_request).setOnClickListener((v)->{
            textView.postDelayed(requestRunnable, 1000);
        });

        findViewById(R.id.btn_invalidate).setOnClickListener((v)->{
            textView.postDelayed(invalidateRunnable, 1000);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("fish", "onResume called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("fish", "onDestroy called");
    }
}
