package com.boycillz.puzzleabc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private String[][] buttonValues = new String[3][3];
    private Button resetButton;
    private int emptyRow, emptyCol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButtons();
        shuffleButtons();

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffleButtons();
            }
        });
    }
    private void initializeButtons() {
        buttons[0][0] = findViewById(R.id.button1);
        buttons[0][1] = findViewById(R.id.button2);
        buttons[0][2] = findViewById(R.id.button3);
        buttons[1][0] = findViewById(R.id.button4);
        buttons[1][1] = findViewById(R.id.button5);
        buttons[1][2] = findViewById(R.id.button6);
        buttons[2][0] = findViewById(R.id.button7);
        buttons[2][1] = findViewById(R.id.button8);
        buttons[2][2] = findViewById(R.id.button9);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setOnClickListener(this);
            }
        }
    }
    private void shuffleButtons() {
        List<String> letters = Arrays.asList("A", "B", "C",
                "D", "E", "F", "G", "H", "");
        Collections.shuffle(letters);

        int index = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(letters.get(index));
                buttonValues[row][col] = letters.get(index);
                if (letters.get(index).equals("")) {
                    emptyRow = row;
                    emptyCol = col;
                }
                index++;
            }
        }
    }
    private boolean isValidMove(int row, int col) {
        return (row == emptyRow && Math.abs(col - emptyCol) == 1) ||
                (col == emptyCol && Math.abs(row - emptyRow) == 1);
    }
    private void swapButtons(int row, int col) {
        Button clickedButton = buttons[row][col];
        Button emptyButton = buttons[emptyRow][emptyCol];

        emptyButton.setText(clickedButton.getText());
        clickedButton.setText("");

        buttonValues[emptyRow][emptyCol] = buttonValues[row][col];
        buttonValues[row][col] = "";

        emptyRow = row;
        emptyCol = col;
    }
    private boolean isSolved() {
        int index = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (row == 2 && col == 2) {
                    break;
                }
                if (!buttonValues[row][col].equals(String.valueOf(index))) {
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (v == buttons[row][col]) {
                    if (isValidMove(row, col)) {
                        swapButtons(row, col);

                        if (isSolved()) {
                            Toast.makeText(this, "Puzzle Solved!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    return;
                }
            }
        }
    }
}