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

import com.example.aplikasiprogmob.Adapter.DataMhsAdapter;
import com.example.aplikasiprogmob.Model.DataMahasiswa;

import java.util.ArrayList;

public class DataMhs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataMhsAdapter dataMhsAdapter;
    private ArrayList<DataMahasiswa> dataMhsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mhs);

        addData();

        this.setTitle("SI KRS - Hai Grace Hutabarat");

        recyclerView = findViewById(R.id.rvDataMhs);
        dataMhsAdapter = new DataMhsAdapter(dataMhsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataMhs.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataMhsAdapter);
    }

    private void addData(){
        dataMhsArrayList = new ArrayList<>();
        dataMhsArrayList.add(new DataMahasiswa("72170171", "Grace Hutabarat", "grace@si.ukdw.ac.id", "Medan"));
        dataMhsArrayList.add(new DataMahasiswa("72170143", "Valeriana Tanesha", "Valeriana@si.ukdw.ac.id", "Magelang"));
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
            Intent intent = new Intent(DataMhs.this,CrudDmhs.class);
            startActivity(intent);
        } if(item.getItemId()==R.id.item2) {
            Toast.makeText(getApplicationContext(), "menu Update terpilih", Toast.LENGTH_SHORT).show();
        } if (item.getItemId()==R.id.item3) {
            Toast.makeText(getApplicationContext(), "menu Delete terpilih", Toast.LENGTH_SHORT).show();
        }
        return  true;
    }
}
