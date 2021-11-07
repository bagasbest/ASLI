package com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.project.asli.R;
import com.project.asli.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /// cek apakah user saat ini admin atau pengguna biasa
        checkRole();

        /// set bg
        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);


        // kembali
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        /// Teori Pertumbuhan Ekonomi
        binding.teori1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu1Activity.class);
                intent.putExtra(Submenu1Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

        /// Teori klasik
        binding.teori2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu2Activity.class);
                intent.putExtra(Submenu2Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

        /// Teori Keynesian (Harrod-domar)
        binding.teori3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu3Activity.class);
                intent.putExtra(Submenu3Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

        /// Neo klasik (Solo-Swan)
        binding.teori4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu4Activity.class);
                intent.putExtra(Submenu4Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });


        /// Teori Pertumbuhan Endogen
        binding.teori5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu5Activity.class);
                intent.putExtra(Submenu5Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });


        /// Teori Schumpeter
        binding.teori6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu6Activity.class);
                intent.putExtra(Submenu6Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });


        /// Teori ketergantungan
        binding.teori7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Submenu7Activity.class);
                intent.putExtra(Submenu7Activity.EXTRA_ROLE, role);
                startActivity(intent);
            }
        });

    }

    private void checkRole() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            role = "admin";
        } else {
            role = "user";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}