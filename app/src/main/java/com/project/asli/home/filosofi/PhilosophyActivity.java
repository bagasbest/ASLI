package com.project.asli.home.filosofi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPhilosophyBinding;

public class PhilosophyActivity extends AppCompatActivity {

    private ActivityPhilosophyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhilosophyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        binding.num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Maja labo dahu", "Malu berbuat salah dan takut berbuat salah, malu kepada sesama dan takut berbuat salah kepada Allah dan aturan adat.");
            }
        });


        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Ngaha aina ngoho", "Makan jangan berlebihan; dimana manusia harus mengontrol diri agar tidak serakah dalam melakukan kegiatan dan perbuatannya.");
            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Mbolo radampa", "Musyawarah untuk mencari samanya cara berfikir untuk mencapai kesamaan kehendak");
            }
        });

        binding.num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("su’u sawa’u sia sawale", "Dalam mengerjakan tugas harus bertanggung jawab, bekerjasama dan bahu membahu.");

            }
        });

        binding.num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Gahi rawi pahu", "Ucapan dan perbuatan harus sama.");

            }
        });

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showPopup(String title, String description) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(description)
                .setCancelable(false)
                .setPositiveButton("", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).setPositiveButtonIcon(getDrawable(R.drawable.ic_baseline_clear_24))
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}