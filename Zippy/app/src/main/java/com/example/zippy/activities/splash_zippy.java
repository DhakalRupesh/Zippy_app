package com.example.zippy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.zippy.R;

public class splash_zippy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_zippy);

        Handler hald = new Handler();
        hald.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_zippy.this, Login_Zippy.class);
                startActivity(intent);
                finish();
            }

        }, 3000);

    }

}


