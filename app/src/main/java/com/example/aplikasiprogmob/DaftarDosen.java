package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasiprogmob.Adapter.DataDosenAdapter;
import com.example.aplikasiprogmob.Adapter.DataKrsAdapter;
import com.example.aplikasiprogmob.Model.DaftarKrs;
import com.example.aplikasiprogmob.Model.DataDosen;

import java.util.ArrayList;

public class DaftarDosen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataDosenAdapter dataDosenAdapter;
    private ArrayList<DataDosen> dataDosenArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_dosen);

        addData();

        this.setTitle("SI KRS - Hai Dosen");

        recyclerView = findViewById(R.id.rvDafDosen);
        dataDosenAdapter = new DataDosenAdapter(dataDosenArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarDosen.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataDosenAdapter);
    }

    private void addData()
    {
        dataDosenArrayList = new ArrayList<>();
        dataDosenArrayList.add(new DataDosen("01020304", "Katon Wijana", "S.Kom, M.T.",
                "katonw@sfatt.ukdw.ac.id", "Condongcatur"));
        dataDosenArrayList.add(new DataDosen("01020305", "Umi Proboyekti", "S.Kom, M.Sc",
                "Umip@sfatt.ukdw.ac.id", "Kaliurang"));
        dataDosenArrayList.add(new DataDosen("01020306", "Yetli Oslan", "S.Kom, M.T.",
                "yetlio@sfatt.ukdw.ac.id", "Gejayan"));

    }
        @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return  true;

    }

    //item selected option
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.item1) {
            Intent intent = new Intent(DaftarDosen.this,CrudDosen.class);
            startActivity(intent);
        }
        return  true;
    }
}
