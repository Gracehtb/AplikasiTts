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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CrudDmhs extends AppCompatActivity
{
    EditText namamhs, nim, alamatmhs, emailmhs, fotomhs;
    String idMhs;
    Boolean isUpdate = false;
    ProgressDialog progressDialog;
    GetDataService service;
    String stringImg;
    //private  EditText txtNamaMhs;
    //private EditText txtNim;
    //private EditText txtAlamatMhs;
    //private EditText txtEmailMhs;
   // private EditText txtFotoMhs;
    private Uri uri;
    private  ImageView img;

    private static final int FILE_ACCESS_REQUEST_CODE = 58;
    private static final int GALLERY_REQUEST_CODE = 58;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dmhs);

        this.setTitle("SI KRS - Hai Grace Hutabarat");

        //Cek Permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }

        namamhs = (EditText) findViewById(R.id.editNama);
        nim = (EditText) findViewById(R.id.nim);
        emailmhs = (EditText) findViewById(R.id.editEmail);
        alamatmhs = (EditText) findViewById(R.id.editAlamat);
        fotomhs = (EditText) findViewById(R.id.editFoto);
        img = (ImageView) findViewById(R.id.imgMhs);

        Button uploadBtn = (Button) findViewById(R.id.btnBrowseMhs);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_gambar();
            }
        });

        checkUpdate();
        Button SaveBtn = (Button) findViewById(R.id.saveMhs);
        if (isUpdate) {
            SaveBtn.setText("Update");
        }
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValid = true;

                if (namamhs.getText().toString().matches("")) {
                    namamhs.setError("Silahkan isi nama Mahasiswa");
                    isValid = false;
                }
                if (nim.getText().toString().matches("")) {
                    nim.setError("Silahkan isi nim Mahasiswa");
                    isValid = false;
                }
                if (alamatmhs.getText().toString().matches("")) {
                    alamatmhs.setError("Silahkan isi alamatnya");
                    isValid = false;
                }
                if (emailmhs.getText().toString().matches("")) {
                    emailmhs.setError("Silahkan isi email Mahasiswa");
                    isValid = false;
                }

                if (!isUpdate) {
                    if (isValid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CrudDmhs.this);

                        builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        Toast.makeText(CrudDmhs.this, "Batal Disimpan", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        requestInsetMahasiswa();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Toast.makeText(CrudDmhs.this, "Silahkan isi Data", Toast.LENGTH_SHORT).show();
                    }
                } else if (isUpdate) {
                    if (isValid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CrudDmhs.this);

                        builder.setMessage("Apakah anda yakin untuk update?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        Toast.makeText(CrudDmhs.this, "Batal update", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        requestUpdateMahasiswa();
                                    }
                                });
                        AlertDialog dialog = builder.show();
                        dialog.show();
                    } else {
                        Toast.makeText(CrudDmhs.this, "Silahkan isi Data", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private void requestInsetMahasiswa() {
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog = ProgressDialog.show(CrudDmhs.this, null, "Loading...", true, false);

        Call<DefaultResult> call = service.insert_mahasiswa(
                namamhs.getText().toString(),
                nim.getText().toString(),
                alamatmhs.getText().toString(),
                emailmhs.getText().toString(),
                stringImg,
                "72170171");

        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CrudDmhs.this, "Data Disimpan", Toast.LENGTH_LONG).show();
                Intent reinsert = new Intent(CrudDmhs.this, DataMhs.class);
                startActivity(reinsert);
                finish();
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CrudDmhs.this, "Error, Check again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestUpdateMahasiswa() {
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog = ProgressDialog.show(CrudDmhs.this, null, "Loading....", true, false);

        Call<DefaultResult> call = service.update_mahasiswa(
                idMhs,
                namamhs.getText().toString(),
                nim.getText().toString(),
                alamatmhs.getText().toString(),
                emailmhs.getText().toString(),
                stringImg,
                "72170171");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CrudDmhs.this, "Update Berhasil", Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(CrudDmhs.this, DataMhs.class);
                startActivity(refresh);
                finish();
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CrudDmhs.this, "Error, check your input", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Menangkap Hasil Pilihan
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
                    fotomhs.setText(imgDecodableString);
                    cursor.close();

                    Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();


                    stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                    break;


            }

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
        idMhs = extrass.getString("idMhs");
        namamhs.setText(extrass.getString("namamhs"));
        nim.setText(extrass.getString("nim"));
        alamatmhs.setText(extrass.getString("alamatmhs"));
        emailmhs.setText(extrass.getString("emailmhs"));
        fotomhs.setText(extrass.getString("fotomhs"));
        isUpdate = true;
    }

}


   /* Button SaveBtn = (Button) findViewById(R.id.btnSave);
        SaveBtn.setOnClickListener(layoutButtonListener);
    }

    private View.OnClickListener layoutButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudDmhs.this);

            builder.setMessage("Apakah anda yakin untuk Menyimpann?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudDmhs.this, "Tidak jadi menyimpan", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudDmhs.this, "Anda Melakukan Penyimpanan !!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrudDmhs.this,DataMhs.class);
                            startActivity(intent);
                        }
                    });

            AlertDialog dialog = builder.create(); dialog.show();
        }
    };
} */
