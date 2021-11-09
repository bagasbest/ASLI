package com.project.asli.home.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.asli.R;
import com.project.asli.databinding.ActivityQuizStandardBinding;

import java.util.HashMap;
import java.util.Map;

public class QuizStandardActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_TOTAL_QUESTION = "size";
    private ActivityQuizStandardBinding binding;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizStandardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        binding.textView11.setText("Standar Kelulusan Quiz Ke-" + getIntent().getStringExtra(EXTRA_TYPE));
        binding.textView19.setText("Silahkan tentukan standar nilai untuk Quiz ke-" + getIntent().getStringExtra(EXTRA_TYPE));

        /// get total soal
        int totalQuestion = getIntent().getIntExtra(EXTRA_TOTAL_QUESTION, 0);
        binding.soal.setText("Quiz ini memiliki total soal sebanyak: " + totalQuestion);

        double totalScore = (1 / Double.parseDouble(String.valueOf(totalQuestion))) * 100;
        binding.poinPerSoal.setText("Setiap soal benar, mendapatkan nilai: " + String.format("%.1f", totalScore));


        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = binding.value.getText().toString().trim();
                if(value.isEmpty() || Integer.parseInt(value) > 100 || Integer.parseInt(value) < 1) {
                    Toast.makeText(QuizStandardActivity.this, "Harap isi standar nilai dengan benar!", Toast.LENGTH_SHORT).show();
                    return;
                }

                binding.progressBar.setVisibility(View.VISIBLE);
                Map<String, Object> standardScore = new HashMap<>();
                standardScore.put("value", value);

                FirebaseFirestore.getInstance()
                        .collection("standardScore")
                        .document(getIntent().getStringExtra(EXTRA_TYPE))
                        .set(standardScore)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    binding.progressBar.setVisibility(View.GONE);
                                    showSuccessDialog();
                                } else {
                                    binding.progressBar.setVisibility(View.GONE);
                                    showFailureDialog();
                                }
                            }
                        });
            }
        });


        /// kembali
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    private void showFailureDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Gagal Memperbarui Standar Nilai")
                .setMessage("silahkan periksa koneksi internet anda, dan coba lagi nanti")
                .setIcon(R.drawable.ic_baseline_clear_24)
                .setPositiveButton("OKE", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Berhasil Memperbarui Standar Nilai")
                .setMessage("Standar nilai telah berubah")
                .setIcon(R.drawable.ic_baseline_check_circle_outline_24)
                .setPositiveButton("OKE", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    onBackPressed();
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}