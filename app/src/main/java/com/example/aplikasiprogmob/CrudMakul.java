package com.example.aplikasiprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aplikasiprogmob.Model.DaftarKrs;

public class CrudMakul extends AppCompatActivity {
    String[] hari={"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};
    String[] sesi={"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_makul);

        this.setTitle("SI KRS - Hai Kaprodi");

        Button SaveBtn = (Button) findViewById(R.id.btnSave);
        SaveBtn.setOnClickListener(layoutButtonListener);

        Spinner spinnerHari = findViewById(R.id.spinHari);
        spinnerHari.setAdapter(new ArrayAdapter<String>(CrudMakul.this, android.R.layout.simple_spinner_item, hari));
        ArrayAdapter<String> aa = new ArrayAdapter<String>(CrudMakul.this, android.R.layout.simple_spinner_item, hari);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerHari.setAdapter(aa);

        spinnerHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CrudMakul.this, "Anda Memilih = " + hari[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(CrudMakul.this, "Anda Tidak Memilih = " , Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinnerSesi = findViewById(R.id.spinSesi);
        spinnerSesi.setAdapter(new ArrayAdapter<String>(CrudMakul.this, android.R.layout.simple_spinner_item, sesi));
        ArrayAdapter<String> bb = new ArrayAdapter<String>(CrudMakul.this, android.R.layout.simple_spinner_item, sesi);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSesi.setAdapter(bb);

        spinnerSesi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CrudMakul.this, "Anda Memilih = " + sesi[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(CrudMakul.this, "Anda Tidak Memilih = " , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View.OnClickListener layoutButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudMakul.this);

            builder.setMessage("Apakah anda yakin untuk Menyimpan?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudMakul.this, "Tidak jadi reset", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudMakul.this, "Melakukan RESET !!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrudMakul.this, MataKuliah.class);
                            startActivity(intent);
                        }
                    });

            AlertDialog dialog = builder.create(); dialog.show();
        }
    };
}
