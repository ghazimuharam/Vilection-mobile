package com.example.finalapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalapplication.Model.CandidateResponse;
import com.example.finalapplication.R;

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
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CandidateAdapter.CandidateViewHolder holder, int position) {
        holder.candidateNameTextView.setText(candidates.get(position).getName());
        holder.candidateNPMTextView.setText(candidates.get(position).getNpm());
        holder.candidateFacultyTextView.setText(candidates.get(position).getFaculty());
        holder.candidateMajorTextView.setText(candidates.get(position).getMajor());
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class CandidateViewHolder extends RecyclerView.ViewHolder {

        TextView candidateNameTextView, candidateNPMTextView, candidateFacultyTextView, candidateMajorTextView;

        public CandidateViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            candidateNameTextView = itemView.findViewById(R.id.candidateNameTextView);
            candidateNPMTextView = itemView.findViewById(R.id.candidateNPMTextView);
            candidateFacultyTextView = itemView.findViewById(R.id.candidateFacultyTextView);
            candidateMajorTextView = itemView.findViewById(R.id.candidateMajorTextView);
        }
    }
}
