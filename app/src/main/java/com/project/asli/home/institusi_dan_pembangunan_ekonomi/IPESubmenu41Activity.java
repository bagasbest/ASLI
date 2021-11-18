package com.project.asli.home.institusi_dan_pembangunan_ekonomi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityIpesubmenu41Binding;

public class IPESubmenu41Activity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "title";
    private ActivityIpesubmenu41Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIpesubmenu41Binding.inflate(getLayoutInflater());
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

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        binding.textView11.setText(title);
        switch (title) {
            case "Institusi-institusi pelengkap":
                binding.num1.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.drawable.table1)
                        .into(binding.table1);
                break;
            case "Biaya, kapasitas dan korupsi":
                binding.num2.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.drawable.table2)
                        .into(binding.table2);
                Glide.with(this)
                        .load(R.drawable.table3)
                        .into(binding.table3);
                break;
            case "Kapasitas SDM":
                binding.num3.setVisibility(View.VISIBLE);
                break;
            case "Tingkat teknologi":
                binding.num4.setVisibility(View.VISIBLE);
                break;
        }




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}