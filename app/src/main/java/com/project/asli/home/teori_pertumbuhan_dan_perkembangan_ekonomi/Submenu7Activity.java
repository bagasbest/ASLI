package com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivitySubmenu7Binding;

public class Submenu7Activity extends AppCompatActivity {

    public static final String EXTRA_ROLE = "";
    private ActivitySubmenu7Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmenu7Binding.inflate(getLayoutInflater());
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
                showPopup("Ketergantungan Kolonial", "Di sini terjadi dominasi politik, dalam bentuk penguasaan kolonial atau penjajah, dari negara pusat terhadap negara pinggiran. Kegiatan ekonomi yang utama adalah perdagangan ekspor dari hasil bumi yang dibutuhkan oleh negara penjajah. Para penjajah memonopoli tanah, pertambangan dan tenaga kerja. Hubungan antara penjajah dan penduduk setempat bersifat eksploitatif. \n");
            }
        });

        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Ketergantungan finansial-industrial.","Di sini tidak ada dominasi politik dalam bentuk penjajahan. Negara pinggiran secara politis merdeka. Tetapi, dalam kenyataannya, negara pinggiran ini masih dikuasai oleh kekuatan-kekuatan finansial dan industrial dari negara pusat sehingga praktis ekonomi negara pinggiran merupakan satelit dari negara pusat. Seperti pada ketergantungan kolonial, negara pinggiran masih mengekspor bahan mentah bagi kebutuhan industri negara pusat. Negara pusat menanamkan modalnya, baik langsung atau melalui kerja sama dengan pengusaha lokal, untuk menghasilkan bahan baku ini. Dengan demikian, pengendalian dilakukan melalui kekuasaan ekonomi, dalam bentuk kekuasaan finansial-industrial.\n");
            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Ketergantungan teknologis-industrial.","Ini adalah bentuk ketergantungan baru. Kegiatan ekonomi di negara pinggiran tidak lagi berupa ekspor bahan mentah untuk keperluan industri di negara pusat. Perusahaanperusahaan multinasional dari negara pusat mulai menanam modalnya dalam kegiatan industri yang produknya ditujukan ke pasar dalam negeri dari negara-negara pinggiran.");
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