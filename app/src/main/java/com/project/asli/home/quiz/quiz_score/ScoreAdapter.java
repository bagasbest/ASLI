package com.project.asli.home.quiz.quiz_score;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.project.asli.R;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private final ArrayList<ScoreModel> listScore = new ArrayList<>();

    private String role;
    public ScoreAdapter(String role) {
        this.role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<ScoreModel> items) {
        listScore.clear();
        listScore.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listScore.get(position), role);
    }

    @Override
    public int getItemCount() {
        return listScore.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, score;
        ConstraintLayout cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.score);
            cv = itemView.findViewById(R.id.cv);
        }

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        public void bind(ScoreModel model, String role) {
            name.setText("Nama Lengkap: " + model.getName());
            score.setText("Nilai: " + String.format("%.1f",  model.getScore()));

            if(role.equals("dosen")) {
                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(itemView.getContext(), ScoreDetailActivity.class);
                        intent.putExtra(ScoreDetailActivity.EXTRA_UID, model.getUserId());
                        itemView.getContext().startActivity(intent);
                    }
                });
            }
        }
    }
}
