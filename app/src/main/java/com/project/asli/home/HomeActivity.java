package com.project.asli.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.asli.R;
import com.project.asli.databinding.ActivityHomeBinding;
import com.project.asli.home.filosofi.PhilosophyActivity;
import com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi.DashboardActivity;
import com.project.asli.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = FirebaseAuth.getInstance().getCurrentUser();

        /// cek apakah saat ini sedang login atau tidak
        checkLoginOrNot();

        /// set background
        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        /// login
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        /// logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        /// filosofi daerah Bima
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PhilosophyActivity.class));
            }
        });

        /// Teori pertumbuhan dan perkembangan ekonomi
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DashboardActivity.class));
            }
        });
    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah anda yakin ingin logout admin ?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("YA", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    FirebaseAuth.getInstance().signOut();
                    binding.logoutBtn.setVisibility(View.INVISIBLE);
                    binding.loginBtn.setVisibility(View.VISIBLE);

                })
                .setNegativeButton("TIDAK", null)
                .show();
    }

    private void checkLoginOrNot() {
        if(user != null) {
            binding.logoutBtn.setVisibility(View.VISIBLE);
        } else {
            binding.loginBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}