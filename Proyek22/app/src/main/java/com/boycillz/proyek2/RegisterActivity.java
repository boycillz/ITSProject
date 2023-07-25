package com.boycillz.proyek2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonRegister;

    // Simpan data pengguna dalam bentuk HashMap. Anda dapat mengganti ini dengan penyimpanan yang lebih aman seperti Firebase Authentication.
    private Map<String, String> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Inisialisasi tampilan
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Inisialisasi data pengguna
        userData = new HashMap<>();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        // Periksa apakah username sudah ada dalam data pengguna
        if (userData.containsKey(username)) {
            // Jika username sudah ada, tampilkan pesan kesalahan
            Toast.makeText(this, "Username sudah terdaftar. Silakan gunakan username lain.", Toast.LENGTH_SHORT).show();
        } else {
            // Jika username belum terdaftar, tambahkan data pengguna baru ke dalam HashMap
            userData.put(username, password);

            // Tampilkan pesan sukses
            Toast.makeText(this, "Registrasi berhasil! Silakan login dengan akun baru Anda.", Toast.LENGTH_SHORT).show();
            // Setelah berhasil registrasi, Anda dapat kembali ke halaman login atau langsung login dengan akun baru.
            // Contoh:
            finish(); // Menutup halaman registrasi
        }
    }
}