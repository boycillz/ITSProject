package com.boycillz.proyek2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText EditUserame, EditPassword, EditEmail, EditNamaLengkap,
            EditAsalSekolah, EditAlamat;
    Button BtnSimpan;
    TextView textViewPassword;

    public static final String FILENAME = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Halaman Depan");

        EditUserame = findViewById(R.id.editUsername);a
        EditPassword = findViewById(R.id.editPassword);
    }
}