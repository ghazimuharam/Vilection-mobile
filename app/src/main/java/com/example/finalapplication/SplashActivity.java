package com.example.finalapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

//    No delay splash screen
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // Start home activity
//        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//        // close splash activity
//        finish();
//    }

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start home activity
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                // close splash activity
                finish();
            }
        },2000);
    }
}
