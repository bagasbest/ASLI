package com.project.asli.home.pembangunan_pertanian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPpsubmenu5Binding;

public class PPSubmenu5Activity extends AppCompatActivity {

    private ActivityPpsubmenu5Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPpsubmenu5Binding.inflate(getLayoutInflater());
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

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Manfaat dan efektivitas organisasi Bimas yaitu:", "Pertama adalah kesan bahwa dengan pola organisasi Bimas ternyata program intensifikasi padi dapat menuai kesuksesan, meskipun dalam Pelita II. Kenaikan hasil per hektar cenderung mulai menurun. \n\n" +
                        "Kedua bertitik tolak pada anggapan bahwa kelebihan organisasi. Bimas adalah pada sifatnya sebagai organisasi pendobrak untuk mempercepat pengenalan teknologi baru yang dilandasi penggunaan bibit unggul dan pupuk.\n");

            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showPopup(String title, String description) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(description)
                .setCancelable(false)
                .setPositiveButton("", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).setPositiveButtonIcon(getDrawable(R.drawable.ic_baseline_clear_24))
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}