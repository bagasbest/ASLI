package com.project.asli.home.institusi_dan_pembangunan_ekonomi;

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
import com.project.asli.databinding.ActivityIpedashboardBinding;
import com.project.asli.home.quiz.materi1.QuizMateri1Activity;
import com.project.asli.home.quiz.materi4.QuizMateri4Activity;

public class IPEDashboardActivity extends AppCompatActivity {

    private ActivityIpedashboardBinding binding;
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
        binding = ActivityIpedashboardBinding.inflate(getLayoutInflater());
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

        binding.teori1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IPEDashboardActivity.this, IPESubmenu1Activity.class));
            }
        });

        binding.teori2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IPEDashboardActivity.this, IPESubmenu2Activity.class));

            }
        });

        binding.teori3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IPEDashboardActivity.this, IPESubmenu3Activity.class));

            }
        });

        binding.teori4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IPEDashboardActivity.this, IPESubmenu4Activity.class));

            }
        });

        /// kerjakan quiz ke 4
        binding.view10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mProgressDialog = new ProgressDialog(IPEDashboardActivity.this);

                mProgressDialog.setMessage("Mohon tunggu hingga proses selesai...");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();


                if (role.equals("user")) {

                    /// Jika belum pernah mengikuti quiz
                    if (!isQuizTaken) {


                        /// konfirmasi mengikuti quiz
                        new AlertDialog.Builder(IPEDashboardActivity.this)
                                .setTitle("Konfirmasi Mengikuti Quiz ke-4")
                                .setMessage("Apakah anda yakin ingin mengikuti Quiz ke-4 ?\n\nQuiz hanya bisa dilaksanakan 1 kali saja. Jika nilai anda di bawah standar, anda tidak bisa mengikuti Quiz akhir. Ingin melanjutkan ?")
                                .setIcon(R.drawable.ic_baseline_warning_24)
                                .setPositiveButton("YA", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    /// quiz ke-1 harus sudah dikerjakan untuk memulai quiz ke-2
                                    cekApakahQuizKetigaUdahDikerjakanAtauBelum(mProgressDialog);

                                })
                                .setNegativeButton("TIDAK", ((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    mProgressDialog.dismiss();
                                }))
                                .show();

                        /// jika sudah pernah mengikuti quiz
                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(IPEDashboardActivity.this, "Anda sudah pernah mengikuti Quiz ke-4", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mProgressDialog.dismiss();
                    startActivity(new Intent(IPEDashboardActivity.this, QuizMateri4Activity.class));
                }
            }
        });

    }

    private void cekApakahQuizKetigaUdahDikerjakanAtauBelum(ProgressDialog mProgressDialog) {

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore
                .getInstance()
                .collection("result")
                .whereEqualTo("userId", uid)
                .whereEqualTo("quizType", "3")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            if(task.getResult().getDocuments().size() > 0) {
                                mProgressDialog.dismiss();

                                /// tandai pengguna sudah mengukuti quiz ke-2
                                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                SharedPreferences sharedPref = IPEDashboardActivity.this.getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putBoolean(uid + "quiz_4", true);
                                editor.apply();

                                startActivity(new Intent(IPEDashboardActivity.this, QuizMateri4Activity.class));
                            } else {
                                mProgressDialog.dismiss();
                                Toast.makeText(IPEDashboardActivity.this, "Maaf, anda harus mengerjakan Quiz ke-3 terlebih dahulu", Toast.LENGTH_SHORT).show();
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
        SharedPreferences sharedPref = IPEDashboardActivity.this.getPreferences(Context.MODE_PRIVATE);
        isQuizTaken = sharedPref.getBoolean( uid + "quiz_4", false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}