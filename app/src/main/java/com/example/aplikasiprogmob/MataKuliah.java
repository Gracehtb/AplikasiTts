package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplikasiprogmob.Adapter.DataDosenAdapter;
import com.example.aplikasiprogmob.Adapter.DataMatkulAdapter;
import com.example.aplikasiprogmob.Model.DataDosen;
import com.example.aplikasiprogmob.Model.DataMatkul;

import java.util.ArrayList;

public class MataKuliah extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataMatkulAdapter dataMatkulAdapter;
    private ArrayList<DataMatkul> dataMatkulArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mata_kuliah);

        addData();

        this.setTitle("SI KRS - Hai Kaprodi");

        recyclerView = findViewById(R.id.rvMatkul);
        dataMatkulAdapter = new DataMatkulAdapter(dataMatkulArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MataKuliah.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataMatkulAdapter);
    }

    private void addData() {
        dataMatkulArrayList = new ArrayList<>();
        dataMatkulArrayList.add(new DataMatkul("SI0001", "Dasar-dasar Pemograman", "Senin", "2", "3"));
        dataMatkulArrayList.add(new DataMatkul("SI0002", "Konsep Sistem Informasi", "Selasa", "1", "3"));
        dataMatkulArrayList.add(new DataMatkul("SI0003", "Pengantar Sistem Informasi", "Rabu", "3", "3"));

    }

    public void Kelola(View view) {
        Intent intent = new Intent(MataKuliah.this,CrudMakul.class);
        startActivity(intent);
    }
}
