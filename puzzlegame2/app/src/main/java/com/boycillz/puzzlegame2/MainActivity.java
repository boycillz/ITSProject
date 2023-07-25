package com.boycillz.puzzlegame2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons = new Button[3][3];
    private int emptyRow, emptyCol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Button(this);
                buttons[row][col].setText((row * 3 + col + 1) + "");
                buttons[row][col].setPadding(8, 8, 8, 8);
                buttons[row][col].setTextSize(24);
                buttons[row][col].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int clickedRow = -1, clickedCol = -1;
                        // Cari tahu button mana yang diklik
                        for (int row = 0; row < 3; row++) {
                            for (int col = 0; col < 3; col++) {
                                if (buttons[row][col] == v) {
                                    clickedRow = row;
                                    clickedCol = col;
                                    break;
                                }
                            }
                        }
                        // Periksa apakah kotak yang diklik adalah tetangga yang valid dari kotak kosong
                        if ((clickedRow == emptyRow && Math.abs(clickedCol - emptyCol) == 1) ||
                                (clickedCol == emptyCol && Math.abs(clickedRow - emptyRow) == 1)) {
                            // Tukar teks dan posisi button yang diklik dengan button kosong
                            buttons[emptyRow][emptyCol].setText(buttons[clickedRow][clickedCol].getText());
                            buttons[clickedRow][clickedCol].setText("");
                            emptyRow = clickedRow;
                            emptyCol = clickedCol;
                            // Periksa apakah puzzle telah terpecahkan
                            checkIfSolved();
                        }
                    }
                });
                gridLayout.addView(buttons[row][col]);
            }
        }

        emptyRow = 2;
        emptyCol = 2;
        buttons[emptyRow][emptyCol].setVisibility(View.INVISIBLE);
    }

    private void checkIfSolved() {
        boolean solved = true;
        int count = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (!buttons[row][col].getText().toString().equals(count + "")) {
                    solved = false;
                    break;
                }
                count++;
            }
        }
        if (solved) {
            // Puzzle terpecahkan, tambahkan logika atau tindakan yang sesuai di sini
        }
    }
}