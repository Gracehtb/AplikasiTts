package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            Intent intent = new Intent(CrudDmhs.this, HalamanMhs.class);
            startActivity(intent);
        }
    };
}
