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

public class CrudKrs extends AppCompatActivity {

    String[] dosen={"Katon Wijana", "Umii Pribiyekti", "Yetli Oslan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_krs);

        this.setTitle("SI KRS - Hai Kaprodi");

        Button SaveBtn = (Button) findViewById(R.id.btnSave);
        SaveBtn.setOnClickListener(layoutButtonListener);


        Spinner spinner = findViewById(R.id.spinKrs);
        spinner.setAdapter(new ArrayAdapter<String>(CrudKrs.this, android.R.layout.simple_spinner_item, dosen));
        ArrayAdapter<String> aa = new ArrayAdapter<String>(CrudKrs.this, android.R.layout.simple_spinner_item, dosen);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CrudKrs.this, "Anda Memilih = " + dosen[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(CrudKrs.this, "Anda Tidak Memilih = " , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View.OnClickListener layoutButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudKrs.this);

            builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudKrs.this, "Tidak jadi disimpan", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudKrs.this, "Melakukan Penyimpanan !!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrudKrs.this, HalamanAdmin.class);
                            startActivity(intent);
                        }
                    });

            AlertDialog dialog = builder.create(); dialog.show();
        }
    };
}
