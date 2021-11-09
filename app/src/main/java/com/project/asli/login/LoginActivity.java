package com.project.asli.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.asli.R;
import com.project.asli.databinding.ActivityLoginBinding;
import com.project.asli.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        /// autoLogin
        autoLogin();

        /// login
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// validasi inputan
                formValidation();
            }
        });


        /// kembali ke halaman sebelumnya
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }

    private void autoLogin() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    private void formValidation() {
        String email = binding.email.getText().toString().trim();
        String password = binding.password.getText().toString().trim();

        if(email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email harus diisi", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Kata sandi harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        /// cek apakah ada akun tersebut
        binding.progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            /// login berhasil, ke home page
                            binding.progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            /// login gagal
                            binding.progressBar.setVisibility(View.GONE);
                            showFailureDialog();
                        }
                    }
                });
    }

    /// munculkan dialog ketika gagal login
    private void showFailureDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Gagal melakukan login")
                .setMessage("Silahkan cek email/password anda, atau periksa koneksi internet anda.")
                .setIcon(R.drawable.ic_baseline_clear_24)
                .setPositiveButton("OKE", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}