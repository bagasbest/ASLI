package com.project.asli.home.pembangunan_daerah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPdsubmenu1Binding;

public class PDSubmenu1Activity extends AppCompatActivity {

    private ActivityPdsubmenu1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdsubmenu1Binding.inflate(getLayoutInflater());
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
                showPopup("Daerah Homogen", "Daerah dianggap ruang dimana kegiatan ekonomi terjadi dan di dalam berbagai pelosok ruang tersebut terdapat sifat-sifat yang sama. Kesamaan sifat-sifat tersebut, antara lain tercermin dari segi pendapatan perkapitanya, social budayanya, geografisnya, dan lain sebagainya. Daerah dalam definisi ini disebut dengan daerah homogen.\n");
            }
        });

        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Daerah Nodal","Suatu daerah di anggap sebagai suatu “ruang ekonomi” yang dikuasai oleh satu atau beberapa pusat kegiatan ekonomi. Daerah dalam definisi seperti ini disebut dengan daerah nodal.\n");
            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Daerah Perencanaan atau Daerah Administrasi","Suatu daerah adalah “ruang ekonomi” yang berada di bawah atu administrasi tertentu, seperti satu provinsi, kabupaten, kecamatan, dan sebagainya. Jadi, di sini didasarkan atas pembagian administratif suatu negara. Daerah dalam definisi seperti ini disebut dengan daerah perencanaan atau daerah administrasi. \n");
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