package com.example.aplikasiprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasiprogmob.Network.DefaultResult;
import com.example.aplikasiprogmob.Network.GetDataService;
import com.example.aplikasiprogmob.Network.RetrofitClientInstance;

//import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudDosen extends AppCompatActivity {

    EditText namadosen, nidn, alamat, email, gelar;
    ProgressDialog progressDialog;
    GetDataService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);

        this.setTitle("SI KRS - Hai Dosen");

        Button SaveBtn = (Button) findViewById(R.id.saveDosen);
        SaveBtn.setOnClickListener(layoutButtonListener);
    }

    private View.OnClickListener layoutButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosen.this);

            builder.setMessage("Apakah anda yakin untuk Menyimpan?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudDosen.this, "Tidak jadi Menyimpan", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            createData();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    public void createData(){

        namadosen = (EditText)findViewById(R.id.namadosen);
        nidn = (EditText)findViewById(R.id.etNidn);
        alamat = (EditText)findViewById(R.id.etAlamat);
        email = (EditText)findViewById(R.id.etEmail);
        gelar = (EditText)findViewById(R.id.etGelar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call = service.insert_dosen(namadosen.getText().toString(), nidn.getText().toString(), alamat.getText().toString(),
                email.getText().toString(), gelar.getText().toString(), "https://source.unsplash.com/random", "72170171");
        call.enqueue(new Callback<DefaultResult>() {
                         @Override
                         public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                             progressDialog.dismiss();
                             Toast.makeText(CrudDosen.this, "Data Disimpan !!", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(CrudDosen.this,DaftarDosen.class);
                             startActivity(intent);
                             finish();

                         }

                         @Override
                         public void onFailure(Call<DefaultResult> call, Throwable t) {
                             progressDialog.dismiss();
                             Toast.makeText(CrudDosen.this, "Gagal, Coba Lagi",Toast.LENGTH_LONG).show();

                         }
                     }
        );
    }


}
