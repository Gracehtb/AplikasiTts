package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplikasiprogmob.Model.DaftarKrs;

public class HalamanMhs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_mhs);

        this.setTitle("SI KRS - Hai Grace Hutabarat");
    }

        public void DataDiri(View view) {
            Intent intent = new Intent(HalamanMhs.this,CrudDmhs.class);
            startActivity(intent);
        }

    public void DaftarKrs(View view) {
        Intent intent = new Intent(HalamanMhs.this, DataKrs.class);
        startActivity(intent);
    }
    public void LihatKelas(View view) {
        Intent intent = new Intent(HalamanMhs.this,LihatKelas.class);
        startActivity(intent);
    }


    }

