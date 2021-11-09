package com.project.asli.home.quiz.quiz_score;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.asli.R;
import com.project.asli.databinding.ActivityScoreDetailBinding;
import java.util.ArrayList;

public class ScoreDetailActivity extends AppCompatActivity {

    public static final String EXTRA_UID = "uid";
    private ActivityScoreDetailBinding binding;
    private final ArrayList<String> listStandardScore = new ArrayList<>();
    private final ArrayList<Double> listNilai = new ArrayList<>();
    private final ArrayList<String> listNilaiId = new ArrayList<>();
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        userId = getIntent().getStringExtra(EXTRA_UID);

        /// get user biodata
        getUserBiodata();

        ///get all nilai mahasiswa yang dipilih from db
        getNilaiFromDb();

        /// get all standar nilai from db
        getAllStandardNilai();

        /// kembali
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        /// luluskan quiz 1
        binding.quiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNilai(0);
            }
        });

        /// luluskan quiz 2
        binding.quiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNilai(1);
            }
        });

        /// luluskan quiz 3
        binding.quiz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNilai(2);
            }
        });

        /// luluskan quiz 4
        binding.quiz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNilai(3);
            }
        });

        /// luluskan quiz 5
        binding.quiz5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNilai(4);
            }
        });

    }

    private void updateNilai(int standardIdx) {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi Meluluskan Quiz")
                .setMessage("Apakah anda yakin ingin merekomendasikan mahasiswa ini untuk lulus pada Quiz ini ?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("YA", (dialogInterface, i) -> {
                    dialogInterface.dismiss();

                    FirebaseFirestore
                            .getInstance()
                            .collection("result")
                            .document(listNilaiId.get(standardIdx))
                            .update("score", Double.parseDouble(listStandardScore.get(standardIdx)))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ScoreDetailActivity.this, "Anda telah memberikan rekomendasi lulus pada Quiz ini", Toast.LENGTH_SHORT).show();
                                        if(standardIdx == 0) {
                                            binding.quiz1.setVisibility(View.INVISIBLE);
                                            binding.nilai1.setText("Nilai: " + listStandardScore.get(standardIdx));
                                        } else if (standardIdx == 1) {
                                            binding.quiz2.setVisibility(View.INVISIBLE);
                                            binding.nilai2.setText("Nilai: " + listStandardScore.get(standardIdx));
                                        } else if (standardIdx == 2) {
                                            binding.quiz3.setVisibility(View.INVISIBLE);
                                            binding.nilai3.setText("Nilai: " + listStandardScore.get(standardIdx));
                                        } else if (standardIdx == 3) {
                                            binding.quiz4.setVisibility(View.INVISIBLE);
                                            binding.nilai4.setText("Nilai: " + listStandardScore.get(standardIdx));
                                        } else if (standardIdx == 4 ) {
                                            binding.quiz5.setVisibility(View.INVISIBLE);
                                            binding.nilai5.setText("Nilai: " + listStandardScore.get(standardIdx));
                                        }
                                    } else {
                                        Toast.makeText(ScoreDetailActivity.this, "Ups, terdapat kendala ketika proses, silahkan coba lagi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                })
                .setNegativeButton("TIDAK", null)
                .show();


    }

    private void getUserBiodata() {
        FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        binding.name.setText("Nama Mahasiswa: " + documentSnapshot.get("name"));
                        binding.nim.setText("NIM: " + documentSnapshot.get("nim"));
                        binding.email.setText("Email: " + documentSnapshot.get("email"));
                    }
                });
    }

    private void getNilaiFromDb() {
        FirebaseFirestore
                .getInstance()
                .collection("result")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            listNilaiId.add("" + document.get("scoreId"));
                            listNilai.add(document.getDouble("score"));
                        }
                        populateNilai();
                    }
                });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void populateNilai() {
        if (listNilai.size() >= 1) {
            binding.nilai1.setText("Nilai: " + String.format("%.1f", listNilai.get(0)));
        }

        if (listNilai.size() >= 2) {
            binding.nilai2.setText("Nilai: " + String.format("%.1f", listNilai.get(1)));
        }

        if (listNilai.size() >= 3) {
            binding.nilai3.setText("Nilai: " + String.format("%.1f", listNilai.get(2)));
        }

        if (listNilai.size() >= 4) {
            binding.nilai4.setText("Nilai: " + String.format("%.1f", listNilai.get(3)));
        }

        if (listNilai.size() == 5) {
            binding.nilai5.setText("Nilai: " + String.format("%.1f", listNilai.get(4)));
        }
    }

    private void getAllStandardNilai() {
        FirebaseFirestore
                .getInstance()
                .collection("standardScore")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            listStandardScore.add("" + document.get("value"));
                        }
                        populateStandardNilai();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void populateStandardNilai() {
        binding.standarQuiz1.setText("Standar kelulusan: " + listStandardScore.get(0));
        binding.standarQuiz2.setText("Standar kelulusan: " + listStandardScore.get(1));
        binding.standarQuiz3.setText("Standar kelulusan: " + listStandardScore.get(2));
        binding.standarQuiz4.setText("Standar kelulusan: " + listStandardScore.get(3));
        binding.standarQuiz5.setText("Standar kelulusan: " + listStandardScore.get(4));


        checkApakahNilaiLebihKecilDariStandar();
    }

    private void checkApakahNilaiLebihKecilDariStandar() {

        if (listNilai.size() >= 1 && listNilai.get(0) < Double.parseDouble(listStandardScore.get(0))) {
            binding.quiz1.setVisibility(View.VISIBLE);
        }

        if (listNilai.size() >= 2 && listNilai.get(1) < Double.parseDouble(listStandardScore.get(1))) {
            binding.quiz2.setVisibility(View.VISIBLE);
        }

        if (listNilai.size() >= 3 && listNilai.get(2) < Double.parseDouble(listStandardScore.get(2))) {
            binding.quiz3.setVisibility(View.VISIBLE);
        }

        if (listNilai.size() >= 4 && listNilai.get(3) < Double.parseDouble(listStandardScore.get(3))) {
            binding.quiz4.setVisibility(View.VISIBLE);
        }

        if (listNilai.size() == 5 && listNilai.get(4) < Double.parseDouble(listStandardScore.get(4))) {
            binding.quiz5.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}