package com.project.asli.home.pembangunan_daerah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPdsubmenu2Binding;

public class PDSubmenu2Activity extends AppCompatActivity {

    private ActivityPdsubmenu2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdsubmenu2Binding.inflate(getLayoutInflater());
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
                showPopup("Pembangunan ekonomi daerah", "Pembangunan ekonomi daerah adalah suatu proses yaitu suatu proses yang mencangkup pembentukan institusi-institusi baru, pembangunan industry-industry alternatif. Perbaikan kapasitas tenaga kerja yang ada untuk menghasilkan pruduk dan jasa yang lebih baik, identifikasi pasar-pasar baru, alih ilmu pengetahuan dan pengembaran perusahaan-perusahaan baru. \n");
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