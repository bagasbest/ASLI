package com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.asli.R;
import com.project.asli.databinding.ActivityDashboardBinding;
import com.project.asli.home.quiz.materi1.QuizMateri1Activity;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
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
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        /// set bg
        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);


        // kembali
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        /// Teori Pertumbuhan Ekonomi
        binding.teori1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu1Activity.class);
                intent.putExtra(Submenu1Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

        /// Teori klasik
        binding.teori2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu2Activity.class);
                intent.putExtra(Submenu2Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

        /// Teori Keynesian (Harrod-domar)
        binding.teori3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu3Activity.class);
                intent.putExtra(Submenu3Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

        /// Neo klasik (Solo-Swan)
        binding.teori4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu4Activity.class);
                intent.putExtra(Submenu4Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });


        /// Teori Pertumbuhan Endogen
        binding.teori5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu5Activity.class);
                intent.putExtra(Submenu5Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });


        /// Teori Schumpeter
        binding.teori6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu6Activity.class);
                intent.putExtra(Submenu6Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });


        /// Teori ketergantungan
        binding.teori7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu7Activity.class);
                intent.putExtra(Submenu7Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

        /// quiz 1
        binding.view10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role.equals("user")) {

                    /// Jika belum pernah mengikuti quiz
                    if (!isQuizTaken) {

                        /// konfirmasi mengikuti quiz
                        new AlertDialog.Builder(DashboardActivity.this)
                                .setTitle("Konfirmasi Mengikuti Quiz ke-1")
                                .setMessage("Apakah anda yakin ingin mengikuti Quiz ke-1 ?\n\nQuiz hanya bisa dilaksanakan 1 kali saja. Jika nilai anda di bawah standar, anda tidak bisa mengikuti Quiz akhir. Ingin melanjutkan ?")
                                .setIcon(R.drawable.ic_baseline_warning_24)
                                .setPositiveButton("YA", (dialogInterface, i) -> {

                                    /// tandai pengguna sudah mengukuti quiz ke-1
                                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    SharedPreferences sharedPref = DashboardActivity.this.getPreferences(Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putBoolean(uid + "quiz_1", true);
                                    editor.apply();


                                    dialogInterface.dismiss();
                                    /// ke halaman quiz
                                    startActivity(new Intent(DashboardActivity.this, QuizMateri1Activity.class));
                                })
                                .setNegativeButton("TIDAK", null)
                                .show();

                        /// jika sudah pernah mengikuti quiz
                    } else {
                        Toast.makeText(DashboardActivity.this, "Anda sudah pernah mengikuti Quiz ke-1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivity(new Intent(DashboardActivity.this, QuizMateri1Activity.class));
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
        SharedPreferences sharedPref = DashboardActivity.this.getPreferences(Context.MODE_PRIVATE);
        isQuizTaken = sharedPref.getBoolean( uid + "quiz_1", false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}