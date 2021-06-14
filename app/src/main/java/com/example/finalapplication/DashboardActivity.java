package com.example.finalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button beginVoting = (Button) findViewById(R.id.beginVoting);
        Button beginProfile = (Button) findViewById(R.id.beginProfile);
        Button logoutButton = (Button) findViewById(R.id.logoutButton);

        beginVoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, VotingActivity.class));
            }
        });

        beginProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("API-KEY", DashboardActivity.MODE_PRIVATE);
                settings.edit().remove("AuthToken").commit();

                startActivity(new Intent(DashboardActivity.this ,LoginActivity.class));
            }
        });
    }
}