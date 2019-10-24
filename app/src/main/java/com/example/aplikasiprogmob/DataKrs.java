package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aplikasiprogmob.Adapter.DataKrsAdapter;
import com.example.aplikasiprogmob.Adapter.DataMhsAdapter;
import com.example.aplikasiprogmob.Model.DaftarKrs;
import com.example.aplikasiprogmob.Model.DataMahasiswa;

import java.util.ArrayList;

public class DataKrs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataKrsAdapter dataKrsAdapter;
    private ArrayList<DaftarKrs> daftarKrsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_krs);

        addData();

        this.setTitle("SI KRS - Hai Kaprodi");

        recyclerView = findViewById(R.id.rvKrs);
        dataKrsAdapter = new DataKrsAdapter(daftarKrsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataKrs.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataKrsAdapter);
    }

    private void addData() {
        daftarKrsArrayList = new ArrayList<>();
        daftarKrsArrayList.add(new DaftarKrs("SI0001", "Dasar-dasar Pemograman", "Senin", "2", "3",
                "Katon Wijana", "30 Mhs"));
        daftarKrsArrayList.add(new DaftarKrs("SI0002", "Konsep Sistem Informasi", "Selasa", "1", "3",
                "Umi Proboyekti", "35 Mhs"));
        daftarKrsArrayList.add(new DaftarKrs("SI0003", "Pengantar Sistem Infromasi", "Rabu", "3", "3",
                "Yetli Oslan", "31 Mhs"));
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return  true;

    }

    //item selected option
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.item1) {
            Intent intent = new Intent(DataKrs.this,CrudKrs.class);
            startActivity(intent);
        } if(item.getItemId()==R.id.item2) {
            Toast.makeText(getApplicationContext(), "menu Update terpilih", Toast.LENGTH_SHORT).show();
        } if (item.getItemId()==R.id.item3) {
            Toast.makeText(getApplicationContext(), "menu Delete terpilih", Toast.LENGTH_SHORT).show();
        }
        return  true;
    }

}
