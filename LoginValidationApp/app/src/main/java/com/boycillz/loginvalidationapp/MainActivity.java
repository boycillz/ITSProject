package com.boycillz.loginvalidationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    // Simpan data pengguna dalam bentuk HashMap. Anda dapat mengganti ini dengan penyimpanan yang lebih aman seperti Firebase Authentication.
    private Map<String, String> userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi tampilan
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Inisialisasi data pengguna
        userData = new HashMap<>();
        userData.put("azis", "123");
        userData.put("admin", "admin");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        // Cek apakah username dan password sesuai dengan data pengguna yang tersimpan
        if (userData.containsKey(username) && userData.get(username).equals(password)) {
            // Jika login berhasil, tampilkan pesan selamat datang
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();

            // Lanjutkan ke halaman utama setelah login
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            // Jika login gagal, tampilkan pesan kesalahan
            Toast.makeText(this, "Login gagal. Periksa kembali username dan password.", Toast.LENGTH_SHORT).show();
        }
    }
}