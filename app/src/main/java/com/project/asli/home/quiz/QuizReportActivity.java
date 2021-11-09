package com.project.asli.home.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityQuizReportBinding;

public class QuizReportActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "type";
    private ActivityQuizReportBinding binding;
    private QuizResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        initRecylerView();
        initViewModel();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initRecylerView() {
        binding.reportRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuizResultAdapter();
        binding.reportRv.setAdapter(adapter);
    }

    private void initViewModel() {
        QuizResultViewModel viewModel = new ViewModelProvider(this).get(QuizResultViewModel.class);

        binding.progressBar.setVisibility(View.VISIBLE);
        switch (getIntent().getStringExtra(EXTRA_TYPE)) {
            case "1":
                viewModel.setLisQuiz("quiz_1");
                break;
            case "2":
                viewModel.setLisQuiz("quiz_2");
                break;
            case "3":
                viewModel.setLisQuiz("quiz_3");
                break;
            case "4":
                viewModel.setLisQuiz("quiz_4");
                break;
            case "5":
                viewModel.setLisQuiz("quiz_5");
                break;
        }
        viewModel.getListQuiz().observe(this, quiz -> {
            if (quiz.size() > 0) {
                adapter.setData(quiz);
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}