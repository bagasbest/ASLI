package com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivitySubmenu6Binding;

public class Submenu6Activity extends AppCompatActivity {

    public static final String EXTRA_ROLE = "";
    private ActivitySubmenu6Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmenu6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

        Glide.with(this)
                .load(R.drawable.img3)
                .into(binding.img3);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("3 Pengaruh Inovasi\n\n", "1. Diperkenalkannya teknologi baru\n" +
                        "2. Menimbulkan keuntungan yang lebih (keuntungan monopolistis) yang merupakan sumber dana penting bagi akumulasi modal\n" +
                        "3. Inovasi akan di ikuti oleh timbulnya proses peniruan (imitasi) yaitu adanya pengusaha-pengusaha lain yang meniru teknologi baru tersebut\n");
            }
        });

        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Faktor-faktor Penunjang Inovasi\n\n", "Menurut Schumpeter ada 5 macam kegiatan yang termasuk sebagai inovasi yaitu :\n\n" +
                        "1. Di perkenalkannya produk baru yang sebelumnya tidak ada\n" +
                        "2. Di perkenalkannya cara berproduksi baru\n" +
                        "3. Pembukaan daerah-daerah pasar baru\n" +
                        "4. Penemuan sumber-sumber bahan mentah baru\n" +
                        "5. Perubahan organisasi industry sehingga efisiensi industry\n");
            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Runtuhnya Kapitalisme\n\n", "1. Sistem kapitalis merupakan system yang paling cocok bagi timbulnya inovasi, pembangunan ekonomi dan pertumbuhan ekonomi. Dengam demikian menurut Schumpeter bagi Negara-negara sedang berkembang yang berusaha mengejar kemajuan ekonomi (pertumbuhan out put) maka system kapitalisasi tersebut sangat sesuai untuk diterapkan.\n\n" +
                        "2. Schumpeter berpendapat bahwa dalam jangka panjang sistem kapitalis akan meningkatkan pendapatan perkapita masyarakat sekaligus distribusi pendapatannya merata. Distibusi pendapatan yang semakin merata ini disebabkan oleh adanya inovasi-inovasi yang akan mengarah kepada barang-barang yang di konsumsi oleh orang banyak sehingga barang-barang menjadi melimpah.\n\n" +
                        "3. Menurut Schumpeter bahwa dalam jangka panjang system kapitalis akan “runtuh” karena adanya transformasi gradual di dalam system tersebut menuju kearah system yang lebih sosialistis. Ciri dari system kapitalis itu sendiri akan berubah justru karena kesuksesannya dalam mencapai kemajuan ekonomi dan kemakmuran. Dengan semakin makmurnya masyarakat maka akan terjadi proses perubahan kelembagaan dan perubahan pandangan masyarakat yang semakin jauh dari system kapitalis asli.\n");
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