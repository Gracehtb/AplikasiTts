package com.example.aplikasiprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HalamanAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_admin);

        this.setTitle("SI KRS - Hai Admin");
    }

    public void DataDiri(View view) {
        Intent intent = new Intent(HalamanAdmin.this,CrudDosen.class);
        startActivity(intent);
    }

    public void DaftarDosen(View view) {
        Intent intent = new Intent(HalamanAdmin.this,DaftarDosen.class);
        startActivity(intent);
    }
    public void MataKuliah(View view) {
        Intent intent = new Intent(HalamanAdmin.this,MataKuliah.class);
        startActivity(intent);
    }

    public void KelolaKrs(View view) {
        Intent intent = new Intent(HalamanAdmin.this,DataKrs.class);
        startActivity(intent);
    }

    public void DaftarMhs(View view) {
        Intent intent = new Intent(HalamanAdmin.this,DataMhs.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return  true;

    }

    //item selected option
    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.item1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HalamanAdmin.this);

                builder.setMessage("Apakah anda yakin untuk mereset nilai protein?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HalamanAdmin.this, "Anda Masih Log In", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences prefs = HalamanAdmin.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
                                String statusSign = prefs.getString("isSign", null);
                                SharedPreferences.Editor edit = prefs.edit();
                                edit.putString("isSign", null);
                                edit.commit();
                                Intent intent = new Intent(HalamanAdmin.this, HalamanLogin.class);
                                startActivity(intent);

                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            return true;
            };
        }
