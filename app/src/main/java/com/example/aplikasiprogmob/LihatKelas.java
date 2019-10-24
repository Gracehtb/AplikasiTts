package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aplikasiprogmob.Adapter.DataDosenAdapter;
import com.example.aplikasiprogmob.Adapter.DataKelasAdapter;
import com.example.aplikasiprogmob.Model.DataDosen;
import com.example.aplikasiprogmob.Model.DataKelas;

import java.util.ArrayList;

public class LihatKelas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataKelasAdapter dataKelasAdapter;
    private ArrayList<DataKelas> dataKelasArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kelas);

        addData();

        this.setTitle("SI KRS - Hai Grace Hutabarat");

        recyclerView = findViewById(R.id.rvKelas);
        dataKelasAdapter = new DataKelasAdapter(dataKelasArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LihatKelas.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataKelasAdapter);
    }

    private void addData() {
        dataKelasArrayList = new ArrayList<>();
        dataKelasArrayList.add(new DataKelas("SI0001", "Dasar-dasar Pemograman", "Senin", "2",
                "Katon Wijana", "30 Mhs"));
        dataKelasArrayList.add(new DataKelas("SI0002", "Pengantar Sistem Informasi", "Selasa", "1",
                "Umi Proboyekti", "35 Mhs"));
        dataKelasArrayList.add(new DataKelas("SI0003", "Konsep Sistem Informasi", "Rabu", "3",
                "Yetli Oslan", "31 Mhs"));
    }


}
