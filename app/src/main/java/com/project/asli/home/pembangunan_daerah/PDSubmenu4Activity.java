package com.project.asli.home.pembangunan_daerah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPdsubmenu4Binding;

public class PDSubmenu4Activity extends AppCompatActivity {

    private ActivityPdsubmenu4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdsubmenu4Binding.inflate(getLayoutInflater());
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
                Intent intent = new Intent(PDSubmenu4Activity.this, PDSubmenu41Activity.class);
                intent.putExtra(PDSubmenu41Activity.EXTRA_TITLE, "Implikasi perencanaan ekonomi daerah");
                startActivity(intent);            }
        });


        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu4Activity.this, PDSubmenu41Activity.class);
                intent.putExtra(PDSubmenu41Activity.EXTRA_TITLE, "Tahap-tahap perencanaan ekonomi daerah");
                startActivity(intent);            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu4Activity.this, PDSubmenu41Activity.class);
                intent.putExtra(PDSubmenu41Activity.EXTRA_TITLE, "Sumberdaya perencanaan untuk pembangunan daerah");
                startActivity(intent);            }
        });

        binding.num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu4Activity.this, PDSubmenu41Activity.class);
                intent.putExtra(PDSubmenu41Activity.EXTRA_TITLE, "Peran pemerintah dalam pembangunan daerah");
                startActivity(intent);            }
        });

        binding.num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDSubmenu4Activity.this, PDSubmenu41Activity.class);
                intent.putExtra(PDSubmenu41Activity.EXTRA_TITLE, "Informasi yang dibutuhkan");
                startActivity(intent);            }
        });

    }
}