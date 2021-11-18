package com.project.asli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.project.asli.databinding.ActivityMainBinding;
import com.project.asli.home.HomeActivity;
import com.project.asli.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);
        Glide.with(this)
                .load(R.drawable.ic_baseline_home_24)
                .into(binding.icon);

        /// delay di splash screen selama 4 second
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(MainActivity.this, LoginActivity.class));
               finish();
            }
        }, 4000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}