package com.project.asli.home.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.asli.R;
import com.project.asli.databinding.ActivityQuizAddBinding;

import java.util.HashMap;
import java.util.Map;

public class QuizAddActivity extends AppCompatActivity {

    public static final String TYPE = "type_materi";
    private ActivityQuizAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        // kembali ke halaman soal quiz
        binding.imageButton.setOnClickListener(view -> onBackPressed());

        // simpan soal quiz
        binding.saveBtn.setOnClickListener(view -> {
            // verifikasi kolom isian
            formVerification();
        });


    }

    private void formVerification() {
        String question = binding.questionEt.getText().toString();
        String a = binding.aEt.getText().toString().trim();
        String b = binding.bEt.getText().toString().trim();
        String c = binding.cEt.getText().toString().trim();
        String d = binding.dEt.getText().toString().trim();
        String answer = binding.answerEt.getText().toString().trim();

        if(question.isEmpty()) {
            Toast.makeText(QuizAddActivity.this, "Soal Quiz tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (a.isEmpty()) {
            Toast.makeText(QuizAddActivity.this, "Pilihan A tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (b.isEmpty()) {
            Toast.makeText(QuizAddActivity.this, "Pilihan B tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (c.isEmpty()) {
            Toast.makeText(QuizAddActivity.this, "Pilihan C tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (d.isEmpty()) {
            Toast.makeText(QuizAddActivity.this, "Pilihan D tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (answer.isEmpty()) {
            Toast.makeText(QuizAddActivity.this, "Jawaban tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        // simpan soal ke firebase
        binding.progressBar.setVisibility(View.VISIBLE);

        String questionId = String.valueOf(System.currentTimeMillis());

        Map<String, Object> questionMap = new HashMap<>();
        questionMap.put("questionId", questionId);
        questionMap.put("question", question);
        questionMap.put("a", a);
        questionMap.put("b", b);
        questionMap.put("c", c);
        questionMap.put("d", d);
        questionMap.put("answer", answer);


        FirebaseFirestore
                .getInstance()
                .collection(getIntent().getStringExtra(TYPE))
                .document(questionId)
                .set(questionMap)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        binding.progressBar.setVisibility(View.GONE);
                        showSuccessDialog();
                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                        showFailureDialog();
                    }
                });

    }

    private void showFailureDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Gagal Menambahkan Soal Quiz")
                .setMessage("Terdapat kesalahan ketika menambahkan soal quiz, silahkan periksa koneksi internet anda, dan coba lagi nanti")
                .setIcon(R.drawable.ic_baseline_clear_24)
                .setPositiveButton("OKE", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Berhasil Menambahkan Soal Quiz")
                .setMessage("soal quiz akan segera terbit, anda dapat mengedit atau menghapus soal jika terdapat kesalahan")
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