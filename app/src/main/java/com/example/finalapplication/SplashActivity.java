package com.example.finalapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

                SharedPreferences preferences = getSharedPreferences("API-KEY", Context.MODE_PRIVATE);
                String token = preferences.getString("AuthToken", "");

                if(token.isEmpty()){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, VotingActivity.class));
                }

                // close splash activity
                finish();
            }
        },2000);
    }
}
