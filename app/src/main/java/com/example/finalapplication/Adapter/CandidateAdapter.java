package com.example.finalapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalapplication.*;
import com.example.finalapplication.Api.InterfaceAPI;
import com.example.finalapplication.Api.RetrofitClientInstance;
import com.example.finalapplication.Model.CandidateResponse;
import com.example.finalapplication.Model.CandidateVoteResponse;
import com.example.finalapplication.Model.LoginResponse;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder> {

    List<CandidateResponse> candidates;
    Context context;

    public CandidateAdapter(Context ctx, List<CandidateResponse> candidates){
        this.context = ctx;
        this.candidates = candidates;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.candidate_row, parent, false);
        return new CandidateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull final CandidateAdapter.CandidateViewHolder holder, int position) {
        holder.candidateNameTextView.setText(candidates.get(position).getName());
        holder.candidateNPMTextView.setText(candidates.get(position).getNpm());
        holder.candidateFacultyTextView.setText(candidates.get(position).getFaculty());
        holder.candidateMajorTextView.setText(candidates.get(position).getMajor());
        holder.candidateIdTextView.setText(Integer.toString(candidates.get(position).getId()));
        holder.candidateVisionTextView.setText(candidates.get(position).getVision());
        holder.candidateMissionTextView.setText(candidates.get(position).getMission());

        holder.voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

                SharedPreferences preferences = context.getSharedPreferences("API-KEY", Context.MODE_PRIVATE);
                String token = preferences.getString("AuthToken", "");

                Call<CandidateVoteResponse> call = api.voteCandidate("Bearer "+ token, holder.candidateIdTextView.getText().toString());

                call.enqueue(new Callback<CandidateVoteResponse>() {
                    @Override
                    public void onResponse(Call<CandidateVoteResponse> call, Response<CandidateVoteResponse> response) {
                        if(response.isSuccessful()){
                            if(response.body().getStatus().equalsIgnoreCase("success")){
                                Snackbar.make(((Activity) context).findViewById(R.id.votingConstraintLayout), "Voted Successfully!", Snackbar.LENGTH_LONG).show();

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        context.startActivity(new Intent((Activity) context, MainActivity.class));
                                        ((Activity) context).finish();
                                    }
                                },2000);
                            }else{
                                Snackbar.make(((Activity) context).findViewById(R.id.votingConstraintLayout), "You are already voted!", Snackbar.LENGTH_LONG).show();
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        context.startActivity(new Intent((Activity) context, MainActivity.class));
                                        ((Activity) context).finish();
                                    }
                                },2000);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CandidateVoteResponse> call, Throwable t) {
                        Snackbar.make(((Activity) context).findViewById(R.id.votingConstraintLayout), "Error connecting to server", Snackbar.LENGTH_LONG).show();
                        Log.e("TAG", t.toString());
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class CandidateViewHolder extends RecyclerView.ViewHolder {

        TextView candidateNameTextView, candidateNPMTextView, candidateFacultyTextView, candidateMajorTextView, candidateIdTextView, candidateVisionTextView, candidateMissionTextView;
        Button voteButton;
        public CandidateViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            candidateNameTextView = itemView.findViewById(R.id.candidateNameTextView);
            candidateNPMTextView = itemView.findViewById(R.id.candidateNPMTextView);
            candidateFacultyTextView = itemView.findViewById(R.id.candidateFacultyTextView);
            candidateMajorTextView = itemView.findViewById(R.id.candidateMajorTextView);
            candidateIdTextView = itemView.findViewById(R.id.candidateIdTextView);
            candidateVisionTextView = itemView.findViewById(R.id.candidateVisionTextView);
            candidateMissionTextView = itemView.findViewById(R.id.candidateMissionTextView);
            voteButton = itemView.findViewById(R.id.voteButton);
        }
    }
}
