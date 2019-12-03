package com.example.aplikasiprogmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import retrofit2.Call;
import  retrofit2.Callback;
import retrofit2.Response;

import com.example.aplikasiprogmob.Network.DefaultResult;
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

        recyclerView = findViewById(R.id.rvDafDosen);

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


                dataDosenArrayList = response.body();
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
        registerForContextMenu(recyclerView);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        DataDosen dosen = dataDosenArrayList.get(item.getGroupId());
        if(item.getTitle() == "Ubah Data Dosen"){
            Intent intent = new Intent(DaftarDosen.this, CrudDosen.class);
            intent.putExtra("nama_dosen",dosen.getNamadosen());
            intent.putExtra("nidn",dosen.getNidn());
            intent.putExtra("alamat",dosen.getAlamat());
            intent.putExtra("email",dosen.getEmail());
            intent.putExtra("gelar",dosen.getGelar());
            intent.putExtra("foto",dosen.getFoto());
            intent.putExtra("is_update",true);
            startActivity(intent);
        } else if(item.getTitle() == "Hapus Data Dosen"){
            progressDialog = new ProgressDialog(DaftarDosen.this);
            progressDialog.show();


            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(dosen.getId(), "72170171");
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(DaftarDosen.this, "Data Dihapus !!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DaftarDosen.this,HalamanAdmin.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(DaftarDosen.this, "Gagal, Coba Lagi",Toast.LENGTH_LONG).show();

                }
            });
        }
        return super.onContextItemSelected(item);
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
