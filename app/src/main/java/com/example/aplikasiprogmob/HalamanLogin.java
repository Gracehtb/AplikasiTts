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

        email = findViewById(R.id.txtEmail);

        Button btnLogin = (Button)findViewById(R.id.btnSign);
        btnLogin.setOnClickListener(myBtnLoginClick);

    }
    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = HalamanLogin.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin",null);
            SharedPreferences.Editor edit = prefs.edit();
            if (email.getText().toString().contains("@staff.ukdw.ac.id")) {
                edit.putString("isLogin", "Admin");
                edit.commit();
                Intent intent = new Intent(HalamanLogin.this, HalamanAdmin.class);
                startActivity(intent);
            }else if (email.getText().toString().contains("@si.ukdw.ac.id")){
                edit.putString("isLogin", "Mhs");
                edit.commit();
                Intent intent = new Intent(HalamanLogin.this,HalamanMhs.class);
                startActivity(intent);

            }else{
                Toast.makeText(HalamanLogin.this, "Email yang dimasukkan tidak Valid !!", Toast.LENGTH_SHORT).show();
                   }

             }
    };

}
