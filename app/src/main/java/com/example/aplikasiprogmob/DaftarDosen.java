package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import retrofit2.Call;
import  retrofit2.Callback;
import retrofit2.Response;
import com.example.aplikasiprogmob.Network.GetDataService;
import com.example.aplikasiprogmob.Network.RetrofitClientInstance;
import android.app.ProgressDialog;
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
import java.util.List;

public class DaftarDosen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataDosenAdapter dataDosenAdapter;
    private ArrayList<DataDosen> dataDosenArrayList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_dosen);

        //addData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<DataDosen>> call = service.getDosenAll("72170171");
        call.enqueue(new Callback<ArrayList<DataDosen>>() {
            @Override
            public void onResponse(Call<ArrayList<DataDosen>> call, Response<ArrayList<DataDosen>> response) {
                progressDialog.dismiss();

                recyclerView = findViewById(R.id.rvDafDosen);
                dataDosenAdapter = new DataDosenAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarDosen.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dataDosenAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<DataDosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DaftarDosen.this, "Login Gagal, Coba Lagi",Toast.LENGTH_LONG).show();

            }
        });

        this.setTitle("SI KRS - Hai Dosen");
    }

    /*private void addData()
    {
        dataDosenArrayList = new ArrayList<>();
        dataDosenArrayList.add(new DataDosen("1", "01020304", "Katon Wijana", "S.Kom, M.T.",
                "katonw@sfatt.ukdw.ac.id", "Condongcatur", "logo.png"));
        dataDosenArrayList.add(new DataDosen("2", "01020305", "Umi Proboyekti", "S.Kom, M.Sc",
                "Umip@sfatt.ukdw.ac.id", "Kaliurang", "logo.png"));
        dataDosenArrayList.add(new DataDosen("3", "01020306", "Yetli Oslan", "S.Kom, M.T.",
                "yetlio@sfatt.ukdw.ac.id", "Gejayan", "logo.png"));

    }*/
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
