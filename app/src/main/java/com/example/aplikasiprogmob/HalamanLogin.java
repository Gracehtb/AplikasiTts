package com.example.aplikasiprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HalamanLogin extends AppCompatActivity {

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);
        email = findViewById(R.id.emailMhs);

        Button btnLogin = (Button) findViewById(R.id.btnSign);
        btnLogin.setOnClickListener(myBtnSignInClick);

    }
    private View.OnClickListener myBtnSignInClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = HalamanLogin.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
            String statusSign = prefs.getString("isSign",null);
            SharedPreferences.Editor edit = prefs.edit();
            if (email.getText().toString().contains("@staff.ukdw.ac.id"))
            {
                edit.putString("isSign", "Admin");
                edit.commit();
                Intent intent = new Intent(HalamanLogin.this, HalamanAdmin.class);
                startActivity(intent);
            }
            else if (email.getText().toString().contains("@si.ukdw.ac.id"))
            {
                edit.putString("isSign", "Mhs");
                edit.commit();
                Intent intent = new Intent(HalamanLogin.this, HalamanMhs.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText( HalamanLogin.this,"Email tidak Valid", Toast.LENGTH_LONG ).show();
            }
             }
    };

}
