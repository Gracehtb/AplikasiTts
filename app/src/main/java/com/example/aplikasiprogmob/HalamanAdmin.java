package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HalamanAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_admin);

        this.setTitle("SI KRS - Hai Admin");
    }

    public void DataDiri(View view) {
        Intent intent = new Intent(HalamanAdmin.this,CrudDosen.class);
        startActivity(intent);
    }

    public void DaftarDosen(View view) {
        Intent intent = new Intent(HalamanAdmin.this,CrudDosen.class);
        startActivity(intent);
    }
    public void MataKuliah(View view) {
        Intent intent = new Intent(HalamanAdmin.this,MataKuliah.class);
        startActivity(intent);
    }

    public void KelolaKrs(View view) {
        Intent intent = new Intent(HalamanAdmin.this,DataKrs.class);
        startActivity(intent);
    }

    public void DaftarMhs(View view) {
        Intent intent = new Intent(HalamanAdmin.this,DataMhs.class);
        startActivity(intent);
    }

}
