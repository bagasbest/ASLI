package com.project.asli.home.teori_pertumbuhan_dan_perkembangan_ekonomi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivitySubmenuBinding;

public class Submenu1Activity extends AppCompatActivity {

    public static final String EXTRA_ROLE = "role";
    private ActivitySubmenuBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);

//        if(getIntent().getStringExtra(EXTRA_ROLE).equals("admin")) {
//            binding.edit.setVisibility(View.VISIBLE);
//        }


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.master1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Friedrich Lish", "Pemikiran List tertuang secara rinci di dalam bukunya yang berjudul The National System Of Political Economy, International Trade, Trade Policy And German Customs Union pada tahun 1856\n\nMenurut List, sistem liberalism yang laissez-faire tidak dapat menjamin alokasi sumberdaya secara optimal. Perkembangan ekonomi menurut list tergantung pada peran pemerintah, dunia bisnis, dan lignkunga kebudayaannya. Menurut list perkembangan ekonomi hanya akan terjadi jika dalam masyarakat ada kebebasan baik berpolitik maupun berkehidupan sosial sehari-hari. List menegaskan negara harus melindungi kepentingan golongan lemah dalam masyarakat.");
            }
        });

        binding.master2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Bruno Hildebrand", "Pemikiran Hildebrand tertuang dalam buku berjudul Die National Oekonomie Der Gegenwart Und Zukunft (1848).\n\nMenurut Hildebrand, kebijakan-kebijakan ekonomi haruslah tercermin dan berpijak dalam sejarah. Perkembangan ekonomi bukan didasarkan pada cara produksi ataupun cara konsumsi, tetapi pada cara distribusi. Menurut Hildebrand perkembangan ekonomi dapat dibagi 3 tahap: \n\n" + "1. perekonomian barter.\n" + "2. perekonomian uang.\n" + "3. pekekonomian kredit.\n");
            }
        });

        binding.master3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Karl Bucher", "Pendapat bucher merupakan sintesa dari pendapat list dan hilderbrand. Menurut Bucher perkembangan ekonomi akan melalui 3 tahap, yaitu : \n\n" + "1. perekonomian subsisten, dimana produksi untuk keperluan sendiri \n" + "2 perekonomian kota, dimana perdagangan sudah meluas \n" + "3. perekonomian nasional, dimana peran pedagang semakin penting.\n");
            }
        });

        binding.master4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Walt Whitman Rostow", "Salah satu teori pembangunan ekonomi yang sangat populer dan sering menjadi bahan perdebatan di kalangan para ekenom adalah teori pertumbuhan ekonomi linier (linear stages model) yang diajukan oleh Rostow, Pada awalnya, teori ini didasarkan pada tulisan Rostow dengan judul The Take-off Into Self-sustained Growth dalam Economic Journal edisi Maret 1956 dan The Stages of Economic Growth dalam The Economic Hinory Rewew tahun 1959. Kemudian pokok pikiran Rostow tersebut dikembangkannya secara  lebih komprehensif dalam bukunya yang berjudul The Stages of Economic Growth (1960)\n\n Menurut Rostow (1959), proses pembangunan ekonomi dapat dibedakan ke dalam lima tahap, yakni masyarakat traditional (the traditional society), prasyarat untuk lepas landas (the preconditions for take-off), lepas landas (the take-off ), menuju kedewasaan (the drive for mararily), dan masa konsumsi tinggi (the age of high mass.consumption).");
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