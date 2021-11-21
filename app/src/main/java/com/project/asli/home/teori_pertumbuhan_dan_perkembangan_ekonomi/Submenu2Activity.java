package com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivitySubmenu2Binding;
import com.project.asli.databinding.ActivitySubmenuBinding;

public class Submenu2Activity extends AppCompatActivity {

    public static final String EXTRA_ROLE = "role";
    private ActivitySubmenu2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmenu2Binding.inflate(getLayoutInflater());
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
                showPopup("Adam Smith", "Ide pokok dalam bukunya yang berjudul An Inquiry Into The Nature And Causes Of The Wealth Of Nations, bahwa setiap kegiatan ekonomi yang didasarkan pada mekanisme pasar dinilai karena mampu mengalokasikan setiap sumber daya secara efisien.\n" + "\n" + "Agar inti dari proses pertumbuhan ekonomi menurut smit mudah dipahami, kita bedakan dua aspek utama dalam pertumbuhan ekonomi yaitu: Pertumbuhan Output Total Dan Pertumbuhan Penduduk.\n");
            }
        });

        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("David Ricardo","Dalam Bukunya Principles Of Political Economy And Taxation (1817) Ricardo mengungkapkan ada beberapa teori penting yaitu: \n\n" + "a. teori tentang nilai dan harga barang \n" + "b. teori tentang distribusi pendapatan atas faktor-faktor produksi yang diuraikan dalam teori upah, teori sewa tanah, teori Bunga dan teori laba\n" + "c. teori tentang perdagangan internaisonal \n" + "d. teori tentang akumulasi dan pertumbuhan ekonomi\n");
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