package com.project.asli.home.pembangunan_daerah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPdsubmenu41Binding;

public class PDSubmenu41Activity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "title";
    private ActivityPdsubmenu41Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdsubmenu41Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        switch (title) {
            case "Implikasi perencanaan ekonomi daerah":
                binding.title.setText(title);
                binding.num1.setVisibility(View.VISIBLE);
                break;
            case "Tahap-tahap perencanaan ekonomi daerah":
                binding.title.setText(title);
                binding.num2.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.drawable.img4)
                        .into(binding.img4);
                break;
            case "Sumberdaya perencanaan untuk pembangunan daerah":
                binding.title.setText(title);
                binding.num3.setVisibility(View.VISIBLE);
                break;
            case "Peran pemerintah dalam pembangunan daerah":
                binding.title.setText(title);
                binding.num4.setVisibility(View.VISIBLE);
                break;
            case "Informasi yang dibutuhkan":
                binding.title.setText(title);
                binding.num5.setVisibility(View.VISIBLE);
                break;
        }

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