package com.boycillz.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] puzzleWords;
    private GridView gridView;
    private ArrayAdapter<String> adapter;
    private List<String> selectedWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        puzzleWords = getResources().getStringArray(R.array.puzzle_words);
        gridView = findViewById(R.id.gridView);
        adapter = new ArrayAdapter<>(this, R.layout.activity_main, puzzleWords);
        gridView.setAdapter(adapter);

        selectedWords = new ArrayList<>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedWord = adapter.getItem(position);
                if (selectedWords.contains(selectedWord)) {
                    selectedWords.remove(selectedWord);
                    view.setBackgroundResource(R.drawable.tile_background);
                } else {
                    selectedWords.add(selectedWord);
                    view.setBackgroundResource(R.drawable.tile_background_selected);
                }
            }
        });
    }
}