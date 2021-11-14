package com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivitySubmenu3Binding;

public class Submenu3Activity extends AppCompatActivity {

    public static final String EXTRA_ROLE = "";
    private ActivitySubmenu3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmenu3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);


        Glide.with(this)
                .load(R.drawable.img1)
                .into(binding.img1);


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}