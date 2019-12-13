package com.example.aplikasiprogmob;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasiprogmob.Model.DataDosen;
import com.example.aplikasiprogmob.Network.DefaultResult;
import com.example.aplikasiprogmob.Network.GetDataService;
import com.example.aplikasiprogmob.Network.RetrofitClientInstance;

import java.io.ByteArrayOutputStream;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

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
    String stringImg;
    //private EditText txtNama;
    //private EditText txtNidn;
    //private EditText txtAlamat;
    //private EditText txtEmail;
    //private EditText txtGelar;
    //private EditText txtFoto;
    private Uri uri;
    private ImageView img;

    private static final int FILE_ACCESS_REQUEST_CODE = 58;
    private static final int GALLERY_REQUEST_CODE = 58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);

        //CEK PERMISSION

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }


        this.setTitle("SI KRS - Hai Dosen");

        namadosen = (EditText) findViewById(R.id.namadosen);
        nidn = (EditText) findViewById(R.id.etNidn);
        email = (EditText) findViewById(R.id.etEmail);
        alamat = (EditText) findViewById(R.id.etAlamat);
        gelar = (EditText) findViewById(R.id.etGelar);
        foto = (EditText) findViewById(R.id.etFoto);
        img = (ImageView) findViewById(R.id.imageDosen);




        final Button uploadBtn = (Button) findViewById(R.id.browseDsn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_gambar();
            }
        });

        checkUpdate();
        Button btnSave = (Button) findViewById(R.id.saveDosen);
        if (isUpdate) {
            btnSave.setText("Update");
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
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

    private void requestInsertDosen() {
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog = ProgressDialog.show(CrudDosen.this, null, "Loading...", true, false);

        Call<DefaultResult> call = service.insert_dosen(
                namadosen.getText().toString(),
                nidn.getText().toString(),
                alamat.getText().toString(),
                email.getText().toString(),
                gelar.getText().toString(),
                stringImg,
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
        progressDialog = ProgressDialog.show(CrudDosen.this, null, "Loading....",
                true, false);

        Call<DefaultResult> call = service.update_dosen(
                idDosen,
                namadosen.getText().toString(),
                nidn.getText().toString(),
                alamat.getText().toString(),
                email.getText().toString(),
                gelar.getText().toString(),
                stringImg,
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

    //Menangkap hasil pilihan
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
            Uri selectedImage = data.getData();
            img.setImageURI(selectedImage);

            String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);

                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                String imgDecodableString = cursor.getString(columnIndex);
                foto.setText(imgDecodableString);
                cursor.close();

                    Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();


                    stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                    break;


            }

    }

    public void createData(){

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case FILE_ACCESS_REQUEST_CODE:
            if(grantResults.length>0 && grantResults[0] == PERMISSION_GRANTED){

            }
            break;
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private  void  select_gambar(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeType = {"image/jpeg"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    void checkUpdate(){
            Bundle extrass = getIntent().getExtras();
            if(extrass == null){
                return;
            }
            //get Data Via the Key
            isUpdate = extrass.getBoolean("is_update");
            idDosen = extrass.getString("idDosen");
            namadosen.setText(extrass.getString("namadosen"));
            nidn.setText(extrass.getString("nidn"));
            alamat.setText(extrass.getString("alamat"));
            email.setText(extrass.getString("email"));
            gelar.setText(extrass.getString("gelar"));
            foto.setText(extrass.getString("foto"));
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

   public void createData(){

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