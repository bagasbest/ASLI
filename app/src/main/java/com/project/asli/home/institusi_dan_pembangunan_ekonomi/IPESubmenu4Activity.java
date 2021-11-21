package com.project.asli.home.institusi_dan_pembangunan_ekonomi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityIpesubmenu4Binding;

public class IPESubmenu4Activity extends AppCompatActivity {

    private ActivityIpesubmenu4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIpesubmenu4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        Glide.with(this)
                .load(R.drawable.table4)
                .into(binding.table4);


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Tiga peran institusi dalam mendukung bekerjanya mekanisme pasar", "1. sebuah institusi dinilai mampu mengalirkan informasi mengenai kondisi pasar, produk, dan pemain yang ada didalamnya (information roles).\n\n" +
                        "2. sebuah institusi dinilai mampu mendefinisikan dan mendorong penegakan atas hak kepemilikan dan kontrak (enforcement roles).\n\n" +
                        "3. sebuah institusi dinilai mampu menciptakan atau bahkan mereduksi kompetisi didalam pasar (competition roles) berdasarkan tiga peran tersebut, setiap struktur institusi diharapkan mampu mempengaruhi distribusi atas aset-aset, pendapatan-pendapatan, dan biaya-biaya antar pelaku didalam sistem pasar tersebut.\n");
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IPESubmenu4Activity.this, IPESubmenu41Activity.class);
                intent.putExtra(IPESubmenu41Activity.EXTRA_TITLE, "Institusi-institusi pelengkap");
                startActivity(intent);
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IPESubmenu4Activity.this, IPESubmenu41Activity.class);
                intent.putExtra(IPESubmenu41Activity.EXTRA_TITLE, "Biaya, kapasitas dan korupsi");
                startActivity(intent);
            }
        });

        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IPESubmenu4Activity.this, IPESubmenu41Activity.class);
                intent.putExtra(IPESubmenu41Activity.EXTRA_TITLE, "Kapasitas SDM");
                startActivity(intent);
            }
        });

        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IPESubmenu4Activity.this, IPESubmenu41Activity.class);
                intent.putExtra(IPESubmenu41Activity.EXTRA_TITLE, "Tingkat teknologi");
                startActivity(intent);
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