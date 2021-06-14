package com.example.finalapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalapplication.Adapter.CandidateAdapter;
import com.example.finalapplication.Api.InterfaceAPI;
import com.example.finalapplication.Api.RetrofitClientInstance;
import com.example.finalapplication.Model.CandidateResponse;
import com.example.finalapplication.Model.LoginRequest;
import com.example.finalapplication.Model.LoginResponse;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class VotingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

        final ConstraintLayout constraint = findViewById(R.id.votingConstraintLayout);

        SharedPreferences preferences = getSharedPreferences("API-KEY", Context.MODE_PRIVATE);
        String token = preferences.getString("AuthToken", "");

        String authorization = "Bearer " + token;
        Call<List<CandidateResponse>> call = api.getCandidate(authorization);

        call.enqueue(new Callback<List<CandidateResponse>>() {
            @Override
            public void onResponse(Call<List<CandidateResponse>> call, Response<List<CandidateResponse>> response) {
                if(response.isSuccessful()){
                    for (CandidateResponse candidate:response.body()) {
                        System.out.println(candidate.getName());
                    }

                    RecyclerView recyclerView = findViewById(R.id.votingRecyclerView);
                    CandidateAdapter candidateAdapter = new CandidateAdapter(VotingActivity.this, response.body());

                    recyclerView.setAdapter(candidateAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(VotingActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<CandidateResponse>> call, Throwable t) {
                Snackbar.make(constraint, "Something went wrong", Snackbar.LENGTH_LONG).show();
                Log.e("TAG", t.toString());
                t.printStackTrace();
            }
        });
    }
}