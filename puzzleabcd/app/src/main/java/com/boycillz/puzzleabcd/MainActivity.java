package com.boycillz.puzzleabcd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    private String[] puzzleWords = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    private Button[] buttons = new Button[puzzleWords.length];
    private boolean[] selected = new boolean[puzzleWords.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < puzzleWords.length; i++) {
            buttons[i] = new Button(this);
            buttons[i].setText(puzzleWords[i]);
            buttons[i].setPadding(8, 8, 8, 8);
            buttons[i].setTextSize(24);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = gridLayout.indexOfChild(v);
                    selected[index] = !selected[index];
                    v.setBackgroundColor(selected[index] ? Color.RED : Color.WHITE);
                }
            });
            gridLayout.addView(buttons[i]);
        }

    }
}