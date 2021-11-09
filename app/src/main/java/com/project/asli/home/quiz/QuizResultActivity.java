package com.project.asli.home.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.asli.R;
import com.project.asli.databinding.ActivityQuizMateriResultBinding;
import com.project.asli.home.HomeActivity;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class QuizResultActivity extends AppCompatActivity {

    public static final String CORRECT = "correct";
    public static final String FAILURE = "fail";
    public static final String TOTAL = "total";
    public static final String TYPE = "type";
    private ActivityQuizMateriResultBinding binding;
    private String name;
    private static final String SHARED_PREFS = "sharedPrefs";
    private String myUid;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizMateriResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        /// get name
        getName();

        /// get standar nilai
        getStandardScore();

        binding.textView8.setText("Skor Kamu\nPada Quiz ke-" + getIntent().getStringExtra(TYPE));

        binding.correctAns.setText("Jawaban Benar: " + getIntent().getIntExtra(CORRECT, 0));
        binding.failAns.setText("Jawaban Salah: " + getIntent().getIntExtra(FAILURE, 0));

        // hitung total skor 1 - 100
        double questionTotal = (Double.parseDouble(String.valueOf(getIntent().getIntExtra(CORRECT, 0))) / Double.parseDouble(String.valueOf(getIntent().getIntExtra(TOTAL, 0)))) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        binding.score.setText(decimalFormat.format(questionTotal));

        // ke halaman laporan Quiz
        binding.button.setOnClickListener(view -> {
            Intent intent = new Intent(QuizResultActivity.this, QuizReportActivity.class);
            intent.putExtra(QuizReportActivity.EXTRA_TYPE, getIntent().getStringExtra(TYPE));
            startActivity(intent);
        });

        // kembali ke homepage
        binding.finishBtn.setOnClickListener(view -> {
                saveScore(questionTotal);
        });
    }

    private void getStandardScore() {
        FirebaseFirestore.getInstance()
                .collection("standardScore")
                .document(getIntent().getStringExtra(TYPE))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String standard = "" + documentSnapshot.get("value");
                        binding.standard.setText("Standar Kelulusan: " + standard);

                        double totalScore = (Double.parseDouble(String.valueOf(getIntent().getIntExtra(CORRECT, 0))) / Double.parseDouble(String.valueOf(getIntent().getIntExtra(TOTAL, 0)))) * 100;

                        if(Double.parseDouble(standard) >= totalScore) {
                            binding.keteranganLulus.setText("Keterangan: Lulus");
                        } else {
                            binding.keteranganLulus.setText("Keterangan: Tidak Lulus");
                        }
                    }
                });
    }

    private void getName() {
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore.getInstance()
                .collection("users")
                .document(myUid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        name = "" + documentSnapshot.get("name");
                        binding.nameEt.setText(name);
                    }
                });
    }

    private void saveScore(double scores) {


        // simpan skor ke firebase
        ProgressDialog mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("Mohon tunggu hingga proses selesai...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String scoreId = String.valueOf(System.currentTimeMillis());

        Map<String, Object> score = new HashMap<>();
        score.put("scoreId", scoreId);
        score.put("score", scores);
        score.put("name", name);
        score.put("quizType", getIntent().getStringExtra(TYPE));
        score.put("nameTemp", name.toLowerCase());
        score.put("userId", myUid);

        FirebaseFirestore
                .getInstance()
                .collection("result")
                .document(scoreId)
                .set(score)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            mProgressDialog.dismiss();
                            //hapus semua jawaban tersimpan
                            SharedPreferences delete = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                            delete.edit().clear().apply();
                            // berhasil simpan skor
                            Toast.makeText(QuizResultActivity.this, "Berhasil menyimpan skor", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(QuizResultActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            mProgressDialog.dismiss();
                            Toast.makeText(QuizResultActivity.this, "Gagal menyimpan skor", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}