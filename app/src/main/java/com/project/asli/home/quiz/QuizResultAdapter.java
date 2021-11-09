package com.project.asli.home.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.asli.R;
import java.util.ArrayList;

public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.ViewHolder>{

    private final ArrayList<QuizModel> listQuiz = new ArrayList<>();
    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<QuizModel> items) {
        listQuiz.clear();
        listQuiz.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizResultAdapter.ViewHolder holder, int position) {
        holder.bind(listQuiz.get(position));
    }

    @Override
    public int getItemCount() {
        return listQuiz.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView question;
        ImageView result;
        String SHARED_PREFS = "sharedPrefs";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.soal);
            result = itemView.findViewById(R.id.result);
        }

        public void bind(QuizModel model) {
            SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

            question.setText(model.getQuestion());
            if(sharedPreferences.getString(String.valueOf(getAdapterPosition()), "").equals(model.getAnswer())) {
                result.setBackgroundResource(R.drawable.ic_baseline_check_circle_outline_24);
            } else {
                result.setBackgroundResource(R.drawable.ic_baseline_clear_24);
            }
        }
    }
}
