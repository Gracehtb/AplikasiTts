package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Halaman1 extends AppCompatActivity {

    TextView Splassh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman1);
        Splassh = (TextView) findViewById(R.id.screensplassh);


        SharedPreferences prefs = Halaman1.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusSign = prefs.getString("isSign",null);
        if (statusSign != null) {
            if (statusSign.equals("Admin")) {
                Intent intent = new Intent(Halaman1.this, HalamanAdmin.class);
                startActivity(intent);
            } else if (statusSign.equals("Mhs")) {
                Intent intent = new Intent(Halaman1.this, HalamanMhs.class);
                startActivity(intent);
            }
        }
        else {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }

                }, 900L);
            }
        }
    }


