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

import com.example.aplikasiprogmob.Model.DataDosen;
import com.example.aplikasiprogmob.Network.DefaultResult;
import com.example.aplikasiprogmob.Network.GetDataService;
import com.example.aplikasiprogmob.Network.RetrofitClientInstance;

//import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudDosen extends AppCompatActivity {

    EditText namadosen, nidn, alamat, email, gelar, foto;
    String idDosen;
    Boolean isUpdate = false;
    ProgressDialog progressDialog;
    GetDataService service;
    private EditText txtNama;
    private EditText txtNidn;
    private EditText txtAlamat;
    private EditText txtEmail;
    private EditText txtGelar;
    private EditText txtFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);


        this.setTitle("SI KRS - Hai Dosen");

        txtNama = (EditText)findViewById(R.id.namadosen);
        txtNidn = (EditText)findViewById(R.id.etNidn);
        txtEmail = (EditText)findViewById(R.id.etEmail);
        txtAlamat = (EditText)findViewById(R.id.etAlamat);
        txtGelar = (EditText)findViewById(R.id.etGelar);
        txtFoto = (EditText)findViewById(R.id.etFoto);

        checkUpdate();
        Button SaveBtn = (Button) findViewById(R.id.saveDosen);
        if(isUpdate){
            SaveBtn.setText("Update");
        }
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValid = true;

                if (namadosen.getText().toString().matches("")) {
                    namadosen.setError("Silahkan isi nama Dosen");
                    isValid = false;
                }
                if (nidn.getText().toString().matches("")) {
                    nidn.setError("Silahkan isi nidn Dosen");
                    isValid = false;
                }
                if (alamat.getText().toString().matches("")) {
                    alamat.setError("Silahkan isi alamatnya");
                    isValid = false;
                }
                if (email.getText().toString().matches("")) {
                    email.setError("Silahkan isi email Dosen");
                    isValid = false;
                }
                if (gelar.getText().toString().matches("")) {
                    gelar.setError("Silahkan isi gelar Dosen");
                }

                if (!isUpdate) {
                    if (isValid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosen.this);

                        builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        Toast.makeText(CrudDosen.this, "Batal Disimpan", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        requestInsertDosen();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Toast.makeText(CrudDosen.this, "Silahkan isi Data", Toast.LENGTH_SHORT).show();
                    }
                } else if (isUpdate) {
                    if (isValid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosen.this);

                        builder.setMessage("Apakah anda yakin untuk update?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        Toast.makeText(CrudDosen.this, "Batal update", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        requestUpdateDosen();
                                    }
                                });
                        AlertDialog dialog = builder.show();
                        dialog.show();
                    } else {
                        Toast.makeText(CrudDosen.this, "Silahkan idi Data", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
        private  void requestInsertDosen(){
            service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            progressDialog = ProgressDialog.show(CrudDosen.this, null, "Loading...", true, false);

            Call<DefaultResult>call = service.insert_dosen(namadosen.getText().toString(), nidn.getText().toString(),
                    alamat.getText().toString(), email.getText().toString(), gelar.getText().toString(), foto.getText().toString(),
                    "72170171");

            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosen.this, "Data Disimpan", Toast.LENGTH_LONG).show();
                    Intent reinsert = new Intent(CrudDosen.this, DataDosen.class);
                    startActivity(reinsert);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosen.this, "Error, Check again", Toast.LENGTH_SHORT).show();
                }
               });
        }


        private void requestUpdateDosen() {
            service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            progressDialog = ProgressDialog.show(CrudDosen.this, null, "Loading....", true, false);

            Call<DefaultResult> call = service.update_dosen(idDosen, namadosen.getText().toString(), nidn.getText().toString(),
                    alamat.getText().toString(), email.getText().toString(), gelar.getText().toString(), foto.getText().toString(),
                    "72170171");
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosen.this, "Update Berhasil", Toast.LENGTH_LONG).show();
                    Intent refresh = new Intent(CrudDosen.this, DataDosen.class);
                    startActivity(refresh);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosen.this, "Error, check your input", Toast.LENGTH_SHORT).show();
                }
            });
        }


        void checkUpdate(){
            Bundle extrass = getIntent().getExtras();
            if(extrass == null){
                return;
            }
            //get Data Via the Key
            isUpdate = extrass.getBoolean("is_update");
            idDosen = extrass.getString("id_dosen");
            txtNama.setText(extrass.getString("nama_dosen"));
            txtNidn.setText(extrass.getString("nidn"));
            txtAlamat.setText(extrass.getString("alamat"));
            txtEmail.setText(extrass.getString("email"));
            txtGelar.setText(extrass.getString("gelar"));
            txtFoto.setText(extrass.getString("foto"));
            isUpdate = true;
        }

    }
    /* progressDialog = new ProgressDialog(CrudDosen.this);
                progressDialog.show();

                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<DefaultResult> call = service.update_dosen(
                        idDosen,
                        txtNama.getText().toString(),
                        txtNidn.getText().toString(),
                        txtAlamat.getText().toString(),
                        txtEmail.getText().toString(),
                        txtGelar.getText().toString(),
                        txtFoto.getText().toString(),
                        "72170171"
                );
                call.enqueue(new Callback<DefaultResult>() {
                    @Override
                    public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                        progressDialog.dismiss();
                        Toast.makeText(CrudDosen.this, "Berhasil di tambah", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<DefaultResult> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(CrudDosen.this, "Gagal di tambah", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
    }*/

   /* private View.OnClickListener layoutButtonListener = new View.OnClickListener() {
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

   /* public void createData(){

        namadosen = (EditText)findViewById(R.id.namadosen);
        nidn = (EditText)findViewById(R.id.etNidn);
        alamat = (EditText)findViewById(R.id.etAlamat);
        email = (EditText)findViewById(R.id.etEmail);
        gelar = (EditText)findViewById(R.id.etGelar);
        foto = (EditText)findViewById(R.id.etFoto);

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
    */
    /*void checkUpdate(){
        Bundle extrass = getIntent().getExtras();
        if(extrass == null){
            return;
        }
        //get Data Via the Key
        isUpdate = extrass.getBoolean("is_update");
        idDosen = extrass.getString("id_dosen");
        txtNama.setText(extrass.getString("nama_dosen"));
        txtNidn.setText(extrass.getString("nidn"));
        txtAlamat.setText(extrass.getString("alamat"));
        txtEmail.setText(extrass.getString("email"));
        txtGelar.setText(extrass.getString("gelar"));
        txtFoto.setText(extrass.getString("foto"));
                    }
*/