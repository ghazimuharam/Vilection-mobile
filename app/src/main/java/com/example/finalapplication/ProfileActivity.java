package com.example.finalapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.finalapplication.Api.InterfaceAPI;
import com.example.finalapplication.Api.RetrofitClientInstance;
import com.example.finalapplication.Model.CandidateResponse;
import com.example.finalapplication.Model.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView userNameTextView = findViewById(R.id.userNameTextView);
        final TextView userNPMTextView = findViewById(R.id.userNPMTextView);
        final TextView userFacultyTextView = findViewById(R.id.userFacultyTextView);
        final TextView userMajorTextView = findViewById(R.id.userMajorTextView);
        final TextView userStatusTextView = findViewById(R.id.userStatusTextView);

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

        SharedPreferences preferences = getSharedPreferences("API-KEY", Context.MODE_PRIVATE);
        String token = preferences.getString("AuthToken", "");

        String authorization = "Bearer " + token;
        Call<UserResponse> call = api.getUser(authorization);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    userNameTextView.setText(response.body().getName().toString());
                    userNPMTextView.setText(response.body().getNpm().toString());
                    userFacultyTextView.setText(response.body().getFaculty().toString());
                    userMajorTextView.setText(response.body().getMajor().toString());

                    if(response.body().getIs_voted() == 1){
                        userStatusTextView.setText("Anda sudah memilih");
                    }else{
                        userStatusTextView.setText("Anda belum memilih");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();
            }
        });
    }
}