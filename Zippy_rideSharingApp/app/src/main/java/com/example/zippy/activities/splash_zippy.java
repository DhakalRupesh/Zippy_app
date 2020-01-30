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
        hide();
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

    private void hide(){
        //hiding the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //making full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hiding the navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}


