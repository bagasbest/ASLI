package com.project.asli.home.pembangunan_daerah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.asli.R;
import com.project.asli.databinding.ActivityPdsubmenu51Binding;

public class PDSubmenu51Activity extends AppCompatActivity {

    public static final String EXTRA_NUM = "num";
    private ActivityPdsubmenu51Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdsubmenu51Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String title = getIntent().getStringExtra(EXTRA_NUM);

        Glide.with(this)
                .load(R.drawable.bg_darken)
                .into(binding.bg);
        binding.title.setText(title);


        switch (title) {
            case "Analisis shift-share":
                binding.num1.setVisibility(View.VISIBLE);
                break;
            case "Location quotients":
                binding.num2.setVisibility(View.VISIBLE);
                break;
            case "Analisis overlay":
                binding.num3.setVisibility(View.VISIBLE);
                break;
            case "Tipologi Klassen (identifikasi daerah tertinggal)":
                binding.num4.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.drawable.img6)
                        .into(binding.img6);

                Glide.with(this)
                        .load(R.drawable.img7)
                        .into(binding.img7);

                Glide.with(this)
                        .load(R.drawable.img9)
                        .into(binding.img8);

                break;
            case "Analisis input-output":
                binding.num5.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.drawable.img8)
                        .into(binding.img9);
                break;
            case "Rasio penduduk-pengerjaan (RPP)":
                binding.num6.setVisibility(View.VISIBLE);
                break;
            case "Kapasitas pengembangan ekonomi masyarakat":
                binding.num7.setVisibility(View.VISIBLE);
                break;
        }

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Kriteria penggolongan LQ", "1. Kegiatan industri yang melayani pasar di daerah itu sendiri maupun di luar daerah yang bersangkutan industri seperti ini dinamakan industri basis\n\n" +
                        "2. Kegiatan ekonomi atau industri yang hanya melayani pasar di daerah tersebut jenis industri ini dinamakan industri non basis atau industri lokal\n");

            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Tiga asumsi yang digunakan dalam teknik LQ", "1. Semua penduduk di setiap daerah mempunyai pola permintaan yang sama dengan pola permintaan pada tingkat nasional\n\n" +
                        "2. Produktivitas tenaga kerja sama antara daerah dan nasional\n\n" +
                        "3. Setiap industri menghasilkan barang yang homogen pada setiap sektor\n");

            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Tiga Kelemahan Penggunaan LQ", "1. Berasumsi bahwa permintaan di setiap daerah identik dengan pola permintaan bangsa dan bahwa produktivitas setiap pekerja di setiap sektor regional sama dengan produktivitas setiap pekerja dalam industri industri nasional. Selera atau pola konsumsi dari anggota masyarakat sering kali berbeda baik antar daerah maupun dalam suatu daerah\n\n" +
                        "Tingkat konsumsi rata-rata untuk suatu jenis barang untuk setiap daerah adalah berbeda artinya konsumsi rata-rata bahan pakaian daerah A lebih besar dari 1 namun daerah A mengimpor bahan pakaian, sedang daerah B yang LQ industri bahan pakaian lebih kecil dari 1 namun dapat mengekspor bahan pakaian\n\n" +
                        "Bahan keperluan industri berbeda antar daerah. Artinya daerah A memakai benang tenun dari kapas sedang daerah B lebih banyak memakai bahan tenun sintetis. Meskipun industri permintaan kapas daerah a mempunyai LQ lebih besar dari 1, daerah itu mungkin harus mengimpor bahan tenun dari daerah B yang mungkin industri tekstil di daerah B mempunyai LQ kurang dari 1\n\n");

            }
        });

        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Lembaga-lembaga masyarakat", "Misalnya : Organisasi organisasi keagamaan organisasi organisasi sosial organisasi kelompok masyarakat dan sebagainya.\n");

            }
        });

        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Struktur ekonomi", "Organisasi-organisasi dengan fokus daerah misalnya Kadinda, asosiasi-asosiasi, kelompok usaha, organisasi pekerja, perusahaan perusahaan yang di berada di daerah tersebut, lembaga-lembaga pembangunan pemerintah dan lain-lain.\n");

            }
        });

        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Lembaga-lembaga politik", "Pemerintah daerah merupakan Kunci keberhasilan pembangunan ekonomi daerah. Oleh karena itu pemerintah daerah dan semua jajarannya harus mempunyai kapasitas yang tinggi untuk menjadi partisipan yang penuh dalam proses pembangunan daerah.\n");

            }
        });

        binding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Lembaga-lembaga keuangan", "Misalnya : Bank, perusahaan asuransi, perusahaan-perusahaan di daerah tersebut, lembaga-lembaga modal ventura, lembaga-lembaga yang membantu pengembangan industri dan pengusaha kecil dan sebagainya.\n");

            }
        });

        binding.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup("Lembaga-lembaga Pendidikan dan Pelatihan", "Pendidikan, terutama pendidikan tinggi merupakan sumber daya utama dalam pembangunan ekonomi, lembaga-lembaga pendidikan dalam pelatihan akan mampu menyediakan sumber daya manusia yang terlatih dan keahlian keaslian di bidang penelitian dan pengembangan bagi program pembangunan ekonomi.\n");

            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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