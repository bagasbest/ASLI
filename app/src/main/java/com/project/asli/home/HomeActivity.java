package com.project.asli.home;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.asli.R;
import com.project.asli.databinding.ActivityHomeBinding;
import com.project.asli.home.filosofi.PhilosophyActivity;
import com.project.asli.home.institusi_dan_pembangunan_ekonomi.IPEDashboardActivity;
import com.project.asli.home.pembangunan_daerah.PDDashboardActivity;
import com.project.asli.home.pembangunan_pertanian.PPDashboardActivity;
import com.project.asli.home.quiz.materi1.QuizMateri1Activity;
import com.project.asli.home.quiz.quiz_akhir.LastQuizActivity;
import com.project.asli.home.quiz.quiz_score.QuizScoreActivity;
import com.project.asli.home.sumber.SourceActivity;
import com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi.DashboardActivity;
import com.project.asli.login.LoginActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private FirebaseUser user;
    private int sum = 0;
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
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = FirebaseAuth.getInstance().getCurrentUser();

        /// cek apakah saat ini sedang login atau tidak
        checkLoginOrNot();

        /// set background
        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        /// login
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        /// logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        /// filosofi daerah Bima
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PhilosophyActivity.class));
            }
        });

        /// Teori pertumbuhan dan perkembangan ekonomi
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DashboardActivity.class));
            }
        });

        /// Pembangunan Daerah
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PDDashboardActivity.class));
            }
        });

        /// pembangunan pertanian
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PPDashboardActivity.class));

            }
        });

        /// Institusi dan Pembangunan Ekonomi
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, IPEDashboardActivity.class));

            }
        });

        /// Sumber materi
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SourceActivity.class));
            }
        });


        /// kuis akhir
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mProgressDialog = new ProgressDialog(HomeActivity.this);

                mProgressDialog.setMessage("Mohon tunggu hingga proses selesai...");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();


                if (role.equals("user")) {
                    /// Jika belum pernah mengikuti quiz
                    if (!isQuizTaken) {
                        /// konfirmasi mengikuti quiz
                        new AlertDialog.Builder(HomeActivity.this)
                                .setTitle("Konfirmasi Mengikuti Quiz Terakhir")
                                .setMessage("Apakah anda yakin ingin mengikuti Quiz terakhir ?\n\nQuiz hanya bisa dilaksanakan 1 kali saja, ingin melanjutkan ?")
                                .setIcon(R.drawable.ic_baseline_warning_24)
                                .setPositiveButton("YA", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    /// quiz ke-1 sampai 4 harus sudah dikerjakan dan minimal memperoleh nilai standar pada masing masing quiz untuk melaksakan quiz akhir
                                    checkIsUserAlreadyPassAllOfQuiz(mProgressDialog);
                                })
                                .setNegativeButton("TIDAK", ((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    mProgressDialog.dismiss();
                                }))
                                .show();

                        /// jika sudah pernah mengikuti quiz
                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, "Anda sudah pernah mengikuti Quiz terakhir", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mProgressDialog.dismiss();
                    startActivity(new Intent(HomeActivity.this, LastQuizActivity.class));
                }
            }
        });

        /// skor seluruh quiz
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QuizScoreActivity.class));
            }
        });
    }

    private void checkIsUserAlreadyPassAllOfQuiz(ProgressDialog mProgressDialog) {
        /// cek dulu, apakaha user sudah mengikuti kuis 1 - 4
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore
                .getInstance()
                .collection("result")
                .whereEqualTo("userId", uid)
                .whereEqualTo("quizType", "4")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            if(task.getResult().getDocuments().size() > 0) {

                                /// selanjutnya, cek apakah seluruh nilai quiz sudah memenuhi standar atau belum
                                /// karena syarat untuk Quiz akhir adalah semua nilai minimal harus standar
                                cekApakahNilaiMemenuhiStandarAtauBelum(mProgressDialog);


                            } else {
                                mProgressDialog.dismiss();
                                Toast.makeText(HomeActivity.this, "Maaf, anda harus mengerjakan Quiz ke-1 sampai Quiz ke-4 terlebih dahulu, dan nilai memenuhi minimum nilai standar untuk masing - masing Quiz.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mProgressDialog.dismiss();
                        }
                    }
                });
    }

    private void cekApakahNilaiMemenuhiStandarAtauBelum(ProgressDialog mProgressDialog) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        ArrayList<String> standarNilai = new ArrayList<>();
        ArrayList<Double> nilai = new ArrayList<>();

        /// get semua standar nilai quiz 1 - 4
        FirebaseFirestore
                .getInstance()
                .collection("standardScore")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            standarNilai.add("" + document.get("value"));
                        }
                    }
                });

        /// get semua nilai dari user ini quiz 1 - 4
        FirebaseFirestore
                .getInstance()
                .collection("result")
                .whereEqualTo("userId", uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            nilai.add(document.getDouble("score"));
                        }

                        /// bandingkan nilai dengan standar nilai, pastikan nilai lebih tinggi atua sama dengan standar, jika ingin melaksanakan Quiz akhir
                        if (nilai.get(0) >= Double.parseDouble(standarNilai.get(0))) {
                            sum++;
                        }

                        if (nilai.get(1) >= Double.parseDouble(standarNilai.get(1))) {
                            sum++;
                        }

                        if (nilai.get(2) < Double.parseDouble(standarNilai.get(2))) {
                            sum++;
                        }

                        if (nilai.get(3) < Double.parseDouble(standarNilai.get(3))) {
                            sum++;
                        }

                        if (nilai.get(4) < Double.parseDouble(standarNilai.get(4))) {
                            sum++;
                        }


                        /// jika semua persyaratan terpenuhi, maka user dapat memulai Quiz akhir
                        if(sum == 5) {

                            /// tandai pengguna sudah mengukuti quiz ke-2
                            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            SharedPreferences sharedPref = HomeActivity.this.getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean(uid + "quiz_5", true);
                            editor.apply();

                            mProgressDialog.dismiss();
                            startActivity(new Intent(HomeActivity.this, LastQuizActivity.class));
                        } else {
                            mProgressDialog.dismiss();
                            Toast.makeText(HomeActivity.this, "Terdapat nilai quiz yang di bawah standar, silakhan konsultasikan ke dosen anda, supaya dosen bisa memberikan rekomendasi lulus.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah anda yakin ingin logout admin ?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("YA", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                })
                .setNegativeButton("TIDAK", null)
                .show();
    }

    private void checkLoginOrNot() {
        if(user != null) {
            binding.logoutBtn.setVisibility(View.VISIBLE);
        } else {
            binding.loginBtn.setVisibility(View.VISIBLE);
        }
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
        SharedPreferences sharedPref = HomeActivity.this.getPreferences(Context.MODE_PRIVATE);
        isQuizTaken = sharedPref.getBoolean( uid + "quiz_5", false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}