package com.boycillz.proyek2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import androidx.appcompat.widget.Toolbar;

public class RegisterActivity extends AppCompatActivity {

    EditText EditUsername, EditPassword, EditEmail, EditNamaLengkap, EditAsalSekolah, EditAlamat;
    Button BtnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Register");

        EditUsername = findViewById(R.id.editUsername);
        EditPassword = findViewById(R.id.editPassword);
        EditEmail = findViewById(R.id.editEmail);
        EditNamaLengkap = findViewById(R.id.editNamaLengkap);
        EditAsalSekolah = findViewById(R.id.editNamaLengkap);
        EditAlamat = findViewById(R.id.editAlamat);
        BtnSimpan = findViewById(R.id.btnSimpan);
        BtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()){
                    simpanFileData();
                }else {
                    Toast.makeText(RegisterActivity.this, "Mohon Lengkapi Seluruh Data",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation(){
        if (EditUsername.getText().toString().equals("")||
        EditPassword.getText().toString().equals("") ||
        EditEmail.getText().toString().equals("")||
        EditNamaLengkap.getText().toString().equals("")||
        EditAsalSekolah.getText().toString().equals("")||
        EditAlamat.getText().toString().equals("")){
            return false;
        } else {
            return true;
        }
    }

    void simpanFileData(){
        String isiFile = EditUsername.getText().toString() + ";" +
                EditPassword.getText().toString() + ";" +
                EditEmail.getText().toString() + ";" +
                EditNamaLengkap.getText().toString() + ";" +
                EditAsalSekolah.getText().toString() + ";" +
                EditAlamat.getText().toString();
        File file = new File(getFilesDir(),
                EditUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}