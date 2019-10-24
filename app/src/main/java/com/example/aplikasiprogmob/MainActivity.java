package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);

        Button SignButton = (Button) findViewById(R.id.btnSign);
        SignButton.setOnClickListener(layoutButtonListener);
    }

    private View.OnClickListener layoutButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,HalamanAdmin.class);
            startActivity(intent);
        }
    };
}
