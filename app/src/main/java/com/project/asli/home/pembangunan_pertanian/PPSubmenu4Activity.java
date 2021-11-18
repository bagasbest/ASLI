package com.project.asli.home.pembangunan_pertanian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPpsubmenu4Binding;

public class PPSubmenu4Activity extends AppCompatActivity {

    private ActivityPpsubmenu4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPpsubmenu4Binding.inflate(getLayoutInflater());
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


        binding.num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Perubahan teknologi dan inovasi", "pada sebagian besar NSB, tekonlogi baru di bidang pertanian dan inovasi-inovasi dalam kegiatan-kegiatan pertanian merupakan prasyarat bagi upaya-upaya dalam meningkatkan output dan produktivitas.  Ada dua sumber inovasi teknologi yang dinilai dapat meningkatkan hasil-hasil pertanian, tetapi kedua sumber ini mempunyai implikasi-implikasi yang berbeda bagi pembangunan pertanian di NSB.\n\nDua sumber inovasi tersebut adalah: pertama, pengenalan terhadap mekanisme pertanian sebagai ganti tenaga kerja manusia dan invoasi kedua adlaah inovasi biologis (seperti bibit unggul) dan kimiawi (pupuk buatan, peptisida, insektisida, dan lain sebagainya) merupakan usaha untuk memperbaiki mutu tanah yang ada dengan meningkatkan hasil (produktivitas) per hektar meskipun memang tidak langsung meningkatkan output setiap tenaga kerja.\n");

            }
        });

        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Perbaikan pola pemilikan tanah", "struktur pertanian dan pola penggunaan tanah perlu di sesuaikan dengan tujuan ganda, yaitu meningkatkan produksi pangan dan meningkatkan pemerataan keuntungan bagi petani secara luas. Pertanian dan pembangunan desa yang menguntungkan rakyat kecil hanya dapat tercapai melalui usaha bersama antara pemerintah dengan semua petani bukan hanya dengan petani kaya saja. Adapun langkah pertama dalam usaha ini adalah pemberian dan perbaikan hak-hak penggunaan tanah kepada masing-masing petani.\n");

            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Kebijakan-kebijakan penunjang", "seluruh keuntungan dari pembangunan usaha tani kecil tidak akan dapat dicapai jika pemerintah tidak menciptakan kebijakan atau sistem kelembagaan yang menunjang. Misalnya berupa insentif-insentif yang diperlukan, kesempatan-kesempatan berusaha dalam kegiatan ekonomi, dan kemudahan untuk memperoleh input yang diperlukan memungkinkan para petani kecil untuk meningkatkan output mereka dan sekaligus meningkatkan produktivitas mereka.\n");

            }
        });

        binding.num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Tujuan pembangunan terpadu", "pembangunan pedesaan terutama sekali masih tergantung pada kemajuan usaha tapi dari para petani kecil. Kemajuan tersebut meliputi: 1) perbaikan taraf hidup termasuk pendapatan, pendidikan, kesehatan atau nutrisi, 2) mengurangi ketimpangan permerataan pendapatan di perdesaan dari ketimpangan pendapatan antara perdesaan dan perkotaan serta kesempatan-kesempatan berusaha, 3) perbaikan kapasitas sektor perdesaan dari waktu ke waktu.\n");

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