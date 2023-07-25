package com.boycillz.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String[] namaProvinsi = new String[]{
      "Jawa Barat","Jawa Tengah","Jawa Timur","DKI Jakarta"
      ,"Banten","Aceh Darussalam","Sumatera Utara","Sumatera Barat"
      ,"Riau","Jambi"
    };

    private ListView LvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("List Provinsi");

        LvItem = (ListView) findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, namaProvinsi);

        LvItem.setAdapter(adapter);

        LvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Provinsi : " +namaProvinsi[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}