package com.project.asli.home.institusi_dan_pembangunan_ekonomi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityIpesubmenu1Binding;
import com.project.asli.databinding.ActivityIpesubmenu2Binding;
import com.project.asli.databinding.ActivityIpesubmenu3Binding;

public class IPESubmenu3Activity extends AppCompatActivity {

    private ActivityIpesubmenu3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIpesubmenu3Binding.inflate(getLayoutInflater());
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}