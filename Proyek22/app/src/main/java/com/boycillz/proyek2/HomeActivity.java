package com.boycillz.proyek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inisialisasi tampilan
        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Ambil data username yang diteruskan dari halaman login
        String username = getIntent().getStringExtra("username");

        // Tampilkan pesan selamat datang dengan username
        textViewWelcome.setText("Selamat Datang, " + username + "!");

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void logout() {
        // Kembali ke halaman login setelah logout
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}