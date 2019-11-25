package com.example.aplikasiprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplikasiprogmob.Model.DaftarKrs;

public class HalamanMhs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_mhs);

        this.setTitle("SI KRS - Hai Grace Hutabarat");
    }

    public void DataDiri(View view) {
        Intent intent = new Intent(HalamanMhs.this, CrudDmhs.class);
        startActivity(intent);
    }

    public void DaftarKrs(View view) {
        Intent intent = new Intent(HalamanMhs.this, DataKrs.class);
        startActivity(intent);
    }

    public void LihatKelas(View view) {
        Intent intent = new Intent(HalamanMhs.this, LihatKelas.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;

    }

    //item selected option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HalamanMhs.this);

            builder.setMessage("Apakah anda yakin untuk mereset nilai protein?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(HalamanMhs.this, "Anda Masih Log In", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = HalamanMhs.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
                            String statusSign = prefs.getString("isSign",null);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("isSign", null);
                            edit.commit();
                            Intent intent = new Intent(HalamanMhs.this, HalamanLogin.class);
                            startActivity(intent);
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        };
        return true;
    }

}


