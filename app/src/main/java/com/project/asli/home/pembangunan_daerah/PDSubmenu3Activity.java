package com.project.asli.home.pembangunan_daerah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPdsubmenu2Binding;
import com.project.asli.databinding.ActivityPdsubmenu3Binding;

public class PDSubmenu3Activity extends AppCompatActivity {

    private ActivityPdsubmenu3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdsubmenu3Binding.inflate(getLayoutInflater());
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
                showPopup("Teori Ekonomi Neo-Klasik", "Teori ini memberikan dua konsep pokok dalam pembangunan ekonomi daerah yaitu keseimbangan equilibrium dan mobilitas faktor produksi artinya sistem perekonomian ini akan mencapai titik keseimbangan alamiah jika modal bisa mengalir tanpa pembatasan Oleh karena itu model akan mengalir dari daerah yang berupah tinggi menuju daerah yang berupah rendah.\n");
            }
        });


        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Teori Basis Ekonomi", "Teori ini menyatakan bahwa faktor penentu utama pertumbuhan ekonomi suatu daerah adalah berhubungan langsung dengan permintaan akan barang dan jasa dari luar daerah Kelemahan model ini adalah bahwa model ini didasarkan pada permintaan eksternal bukan internal dan pada akhirnya akan menyebabkan ketergantungan yang sangat tinggi terhadap kekuatan kekuatan pasar nasional maupun global.\n");
            }
        });


        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Teori Lokasi", "Teori ini menitik beratkan pada lokasi atau tempat,  dimana pengembangan lokasi atau tempat tersebut digunakan untuk mengembangkan kawasan Industri.  perusahaan cenderung untuk meminimalisir biaya-biaya yang dikeluarkan dalam memilih tempat kegiatan serta memaksimalkan peluang untuk mendekati pasar sasaran hasil produksinya. kelemahan teori ini adalah bahwa teknologi dan komunikasi sudah begitu jauh berkembang dan telah mengubah peran lokasi tertentu untuk kegiatan produksi dan industri barang.\n");
            }
        });

        binding.num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Teori Tempat Sentral", "Teori tempat Sentral ini didukung oleh sejumlah Tempat yang menyediakan sumber daya seperti bahan baku, tenaga kerja yang merupakan suatu wilayah pemukiman penyedia jasa- jasa bagi penduduk daerah sekitarnya.\n");
            }
        });

        binding.num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Teori kausasi kumulatif", "Pembangunan di daerah-daerah yang lebih maju akan menyebabkan suatu keadaan yang akan menimbulkan hambatan yang lebih besar pada daerah yang terbelakang untuk dapat maju dan berkembang. Suatu keadaan yang dapat menghambat perkembangan ini digolongkannya sebagai backwass effects. Disisi lain perkembangan di daerah yang lebih maju juga dapat menimbulkan suatu keadaan yang akan mendorong perkembangan bagi daerah yang lebih msikin dan dinamakan spread effects.\n");
            }
        });

        binding.num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Model Daya Tarik (Attraction)", "Model daya tarik ini adalah model pembangunan ekonomi yang paling banyak digunakan teori otonomi yang mendasarinya adalah bahwa suatu masyarakat dapat memperbaiki posisi pasarnya terhadap industrialis melalui pemberian insentif atau subsidi.\n");
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