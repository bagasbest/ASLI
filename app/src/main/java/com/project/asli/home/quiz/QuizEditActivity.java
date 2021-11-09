package com.project.asli.home.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.asli.R;
import com.project.asli.databinding.ActivityQuizEditBinding;

import java.util.HashMap;
import java.util.Map;

public class QuizEditActivity extends AppCompatActivity {

    public static final String QUESTION = "question";
    public static final String A = "a";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String ANSWER = "ans";
    public static final String QUESTION_ID = "qid";
    public static final String TYPE = "type";
    private ActivityQuizEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        binding.aEt.setText(getIntent().getStringExtra(A));
        binding.bEt.setText(getIntent().getStringExtra(B));
        binding.cEt.setText(getIntent().getStringExtra(C));
        binding.dEt.setText(getIntent().getStringExtra(D));
        binding.questionEt.setText(getIntent().getStringExtra(QUESTION));
        binding.answerEt.setText(getIntent().getStringExtra(ANSWER));

        // kembali ke halaman soal quiz A
        binding.imageButton.setOnClickListener(view -> onBackPressed());

        // simpan soal quiz A
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
            Toast.makeText(QuizEditActivity.this, "Soal Quiz tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (a.isEmpty()) {
            Toast.makeText(QuizEditActivity.this, "Pilihan A tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (b.isEmpty()) {
            Toast.makeText(QuizEditActivity.this, "Pilihan B tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (c.isEmpty()) {
            Toast.makeText(QuizEditActivity.this, "Pilihan C tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (d.isEmpty()) {
            Toast.makeText(QuizEditActivity.this, "Pilihan D tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (answer.isEmpty()) {
            Toast.makeText(QuizEditActivity.this, "Jawaban tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        // simpan soal ke firebase
      binding.progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> questionMap = new HashMap<>();
        questionMap.put("question", question);
        questionMap.put("a", a);
        questionMap.put("b", b);
        questionMap.put("c", c);
        questionMap.put("d", d);
        questionMap.put("answer", answer);


        FirebaseFirestore
                .getInstance()
                .collection(getIntent().getStringExtra(TYPE))
                .document(getIntent().getStringExtra(QUESTION_ID))
                .update(questionMap)
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
                .setTitle("Gagal Mengedit Soal Quiz")
                .setMessage("Terdapat kesalahan ketika mengedit soal quiz, silahkan periksa koneksi internet anda, dan coba lagi nanti")
                .setIcon(R.drawable.ic_baseline_clear_24)
                .setPositiveButton("OKE", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Berhasil Mengedit Soal Quiz")
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