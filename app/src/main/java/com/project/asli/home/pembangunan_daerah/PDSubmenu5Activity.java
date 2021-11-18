package com.project.asli.home.pembangunan_daerah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPdsubmenu5Binding;

public class PDSubmenu5Activity extends AppCompatActivity {

    private ActivityPdsubmenu5Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdsubmenu5Binding.inflate(getLayoutInflater());
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
                Intent intent = new Intent(PDSubmenu5Activity.this, PDSubmenu51Activity.class);
                intent.putExtra(PDSubmenu51Activity.EXTRA_NUM, "Analisis shift-share");
                startActivity(intent);
            }
        });

        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu5Activity.this, PDSubmenu51Activity.class);
                intent.putExtra(PDSubmenu51Activity.EXTRA_NUM, "Location quotients");
                startActivity(intent);
            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu5Activity.this, PDSubmenu51Activity.class);
                intent.putExtra(PDSubmenu51Activity.EXTRA_NUM, "Analisis overlay");
                startActivity(intent);
            }
        });

        binding.num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu5Activity.this, PDSubmenu51Activity.class);
                intent.putExtra(PDSubmenu51Activity.EXTRA_NUM, "Tipologi Klassen (identifikasi daerah tertinggal)");
                startActivity(intent);
            }
        });

        binding.num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu5Activity.this, PDSubmenu51Activity.class);
                intent.putExtra(PDSubmenu51Activity.EXTRA_NUM, "Analisis input-output");
                startActivity(intent);
            }
        });

        binding.num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu5Activity.this, PDSubmenu51Activity.class);
                intent.putExtra(PDSubmenu51Activity.EXTRA_NUM, "Rasio penduduk-pengerjaan (RPP)");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}