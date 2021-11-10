package com.project.asli.home.quiz.quiz_score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.asli.R;
import com.project.asli.databinding.ActivityQuizScoreBinding;

public class QuizScoreActivity extends AppCompatActivity {

    private ActivityQuizScoreBinding binding;
    private ScoreAdapter adapter;
    private String quizType = "Quiz ke-1";
    private String role = "bukan dosen";

    @Override
    protected void onResume() {
        super.onResume();
        /// checkRole
        checkRole();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);


        // filter quiz ke 1 atau 2 dan seterusnya
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quiz_type, android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        binding.quizType.setAdapter(adapter);
        binding.quizType.setOnItemClickListener((adapterView, view, i, l) -> {
            initRecyclerView();
            quizType = binding.quizType.getText().toString();
            switch (quizType) {
                case "Quiz ke-1":
                    initViewModel("1");
                    break;
                case "Quiz ke-2":
                    initViewModel("2");
                    break;
                case "Quiz ke-3":
                    initViewModel("3");
                    break;
                case "Quiz ke-4":
                    initViewModel("4");
                    break;
                case "Quiz Terakhir":
                    initViewModel("5");
                    break;
            }
        });


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void checkRole() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (("" + documentSnapshot.get("role")).equals("dosen")) {
                            role = "dosen";
                        }
                        initRecyclerView();
                        switch (quizType) {
                            case "Quiz ke-1":
                                initViewModel("1");
                                break;
                            case "Quiz ke-2":
                                initViewModel("2");
                                break;
                            case "Quiz ke-3":
                                initViewModel("3");
                                break;
                            case "Quiz ke-4":
                                initViewModel("4");
                                break;
                            case "Quiz Terakhir":
                                initViewModel("5");
                                break;
                        }
                    }
                });
    }

    private void initRecyclerView() {
        binding.rvScore.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ScoreAdapter(role);
        binding.rvScore.setAdapter(adapter);
    }

    private void initViewModel(String quizTypes) {
        ScoreViewModel viewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        viewModel.setListScore(quizTypes);
        viewModel.getListScore().observe(this, quiz -> {
            if (quiz.size() > 0) {
                binding.noData.setVisibility(View.GONE);
                adapter.setData(quiz);

            } else {
                binding.noData.setVisibility(View.VISIBLE);
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