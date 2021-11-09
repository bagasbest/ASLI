package com.project.asli.home.pembangunan_pertanian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPpdashboardBinding;
import com.project.asli.home.pembangunan_daerah.PDDashboardActivity;
import com.project.asli.home.quiz.materi1.QuizMateri1Activity;
import com.project.asli.home.quiz.materi2.QuizMateri2Activity;
import com.project.asli.home.quiz.materi3.QuizMateri3Activity;

public class PPDashboardActivity extends AppCompatActivity {

    private ActivityPpdashboardBinding binding;
    private String role;
    private boolean isQuizTaken = false;

    @Override
    protected void onResume() {
        super.onResume();
        /// cek apakah user saat ini admin atau pengguna biasa
        checkRole();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPpdashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /// kerjakan quiz ke 3
        binding.view10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mProgressDialog = new ProgressDialog(PPDashboardActivity.this);

                mProgressDialog.setMessage("Mohon tunggu hingga proses selesai...");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();


                if (role.equals("user")) {

                    /// Jika belum pernah mengikuti quiz
                    if (!isQuizTaken) {


                        /// konfirmasi mengikuti quiz
                        new AlertDialog.Builder(PPDashboardActivity.this)
                                .setTitle("Konfirmasi Mengikuti Quiz ke-3")
                                .setMessage("Apakah anda yakin ingin mengikuti Quiz ke-3 ?\n\nQuiz hanya bisa dilaksanakan 1 kali saja. Jika nilai anda di bawah standar, anda tidak bisa mengikuti Quiz akhir. Ingin melanjutkan ?")
                                .setIcon(R.drawable.ic_baseline_warning_24)
                                .setPositiveButton("YA", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    /// quiz ke-1 harus sudah dikerjakan untuk memulai quiz ke-2
                                    cekApakahQuizKeduaUdahDikerjakanAtauBelum(mProgressDialog);

                                })
                                .setNegativeButton("TIDAK", ((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    mProgressDialog.dismiss();
                                }))
                                .show();

                        /// jika sudah pernah mengikuti quiz
                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(PPDashboardActivity.this, "Anda sudah pernah mengikuti Quiz ke-3", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mProgressDialog.dismiss();
                    startActivity(new Intent(PPDashboardActivity.this, QuizMateri3Activity.class));
                }
            }
        });
    }

    private void cekApakahQuizKeduaUdahDikerjakanAtauBelum(ProgressDialog mProgressDialog) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore
                .getInstance()
                .collection("result")
                .whereEqualTo("userId", uid)
                .whereEqualTo("quizType", "2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            if(task.getResult().getDocuments().size() > 0) {
                                mProgressDialog.dismiss();

                                /// tandai pengguna sudah mengukuti quiz ke-2
                                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                SharedPreferences sharedPref = PPDashboardActivity.this.getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putBoolean(uid + "quiz_3", true);
                                editor.apply();

                                startActivity(new Intent(PPDashboardActivity.this, QuizMateri3Activity.class));
                            } else {
                                mProgressDialog.dismiss();
                                Toast.makeText(PPDashboardActivity.this, "Maaf, anda harus mengerjakan Quiz ke-2 terlebih dahulu", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mProgressDialog.dismiss();
                        }
                    }
                });
    }

    private void checkRole() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        role = "" + documentSnapshot.get("role");
                    }
                });


        /// Quiz hanya bisa sekali di akses oleh mahasiswa
        SharedPreferences sharedPref = PPDashboardActivity.this.getPreferences(Context.MODE_PRIVATE);
        isQuizTaken = sharedPref.getBoolean( uid + "quiz_3", false);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}