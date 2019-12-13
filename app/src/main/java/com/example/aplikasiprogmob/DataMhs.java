package com.example.aplikasiprogmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import  retrofit2.Response;

import com.example.aplikasiprogmob.Network.DefaultResult;
import  com.example.aplikasiprogmob.Network.GetDataService;
import  com.example.aplikasiprogmob.Network.RetrofitClientInstance;
import  android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mhs);
        //addData();
        this.setTitle("SI Daftar Mahasiswa - Hai Grace Hutabarat");

        recyclerView = findViewById(R.id.rvDataMhs);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<DataMahasiswa>> call = service.getMahasiswaAll("72170171");
        call.enqueue(new Callback<ArrayList<DataMahasiswa>>() {
            @Override
            public void onResponse(Call<ArrayList<DataMahasiswa>> call, Response<ArrayList<DataMahasiswa>> response) {
                progressDialog.dismiss();

                dataMhsArrayList = response.body();
                dataMhsAdapter = new DataMhsAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataMhs.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dataMhsAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<DataMahasiswa>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DataMhs.this, "Login Gagal, Coba Lagi", Toast.LENGTH_LONG
                ).show();
            }
        });
        registerForContextMenu(recyclerView);

        //dataMhsAdapter = new DataMhsAdapter(dataMhsArrayList);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        DataMahasiswa mahasiswa = dataMhsArrayList.get(item.getGroupId());
        if (item.getTitle() == "Ubah Data Mahasiswa") {
            Intent intent = new Intent(DataMhs.this, CrudDmhs.class);
            intent.putExtra("namamhs", mahasiswa.getNamamhs());
            intent.putExtra("nim", mahasiswa.getNim());
            intent.putExtra("alamatmhs", mahasiswa.getAlamatmhs());
            intent.putExtra("emailmhs", mahasiswa.getEmailmhs());
            intent.putExtra("fotomhs", mahasiswa.getFotomhs());
            intent.putExtra("is_update", true);
            startActivity(intent);
        } else if (item.getTitle() == "Hapus Data Mahasiswa") {
            progressDialog = new ProgressDialog(DataMhs.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_mahasiswa(mahasiswa.getId(), "72170171");
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(DataMhs.this, "Data Dihapus", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataMhs.this, HalamanAdmin.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(DataMhs.this, "Gagal, Coba Lagi", Toast.LENGTH_LONG).show();

                }
            });
        }
        return super.onContextItemSelected(item);
    }

    //item selected option

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.item1){
        Intent intent = new Intent(DataMhs.this, CrudDmhs.class);
        startActivity(intent);
    }
    return  true;

    }


}
   // private void addData(){
     //   dataMhsArrayList = new ArrayList<>();
       // dataMhsArrayList.add(new DataMahasiswa("72170171", "Grace Hutabarat", "grace@si.ukdw.ac.id", "Medan"));
        //dataMhsArrayList.add(new DataMahasiswa("72170143", "Valeriana Tanesha", "Valeriana@si.ukdw.ac.id", "Magelang"));
    //}



