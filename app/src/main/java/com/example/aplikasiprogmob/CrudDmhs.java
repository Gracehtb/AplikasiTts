package com.example.aplikasiprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrudDmhs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dmhs);

        this.setTitle("SI KRS - Hai Grace Hutabarat");

        Button SaveBtn = (Button) findViewById(R.id.btnSave);
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
}
