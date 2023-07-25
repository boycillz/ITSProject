package com.boycillz.proyek2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    public static final String FILENAME = "login";

    EditText EditUsername, EditPassword;
    Button BtnLogin, BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditUsername = findViewById(R.id.editUsername);
        EditPassword = findViewById(R.id.editPassword);
        BtnLogin = findViewById(R.id.action_login);
        BtnRegister = findViewById(R.id.action_register);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    void simpanFileLogin() {
        String isiFile = EditUsername.getText().toString() + ";" + EditPassword.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream fileOutputStream = null;
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream.write(isiFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Login Berhasil",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    void login() {
        File sdcard = getFilesDir();
        File file = new File(sdcard,
                EditUsername.getText().toString());
        if (file.exists()) {
            StringBuilder builder = new StringBuilder();
            try {
                BufferedReader reader =
                        new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            String data = builder.toString();
            String[] dataUser = data.split(";");

            if (dataUser[1].equals(EditPassword.getText().toString())) {
                simpanFileLogin();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Password Tidak Sesuai",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "User Tidak Ditemukan",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
