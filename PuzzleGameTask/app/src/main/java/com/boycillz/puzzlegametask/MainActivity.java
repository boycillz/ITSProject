package com.boycillz.puzzlegametask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons;
    private GridLayout gridLayout;
    private int emptyRow, emptyCol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        buttons = new Button[4][4];

        // Mendapatkan referensi untuk setiap tombol
        buttons[0][0] = findViewById(R.id.button1);
        buttons[0][1] = findViewById(R.id.button2);
        buttons[0][2] = findViewById(R.id.button3);
        buttons[0][3] = findViewById(R.id.button4);
        buttons[1][0] = findViewById(R.id.button5);
        buttons[1][1] = findViewById(R.id.button6);
        buttons[1][2] = findViewById(R.id.button7);
        buttons[1][3] = findViewById(R.id.button8);
        buttons[2][0] = findViewById(R.id.button9);
        buttons[2][1] = findViewById(R.id.button10);
        buttons[2][2] = findViewById(R.id.button11);
        buttons[2][3] = findViewById(R.id.button12);
        buttons[3][0] = findViewById(R.id.button13);
        buttons[3][1] = findViewById(R.id.button14);
        buttons[3][2] = findViewById(R.id.button15);
        buttons[3][3] = findViewById(R.id.button16);

        // Mengatur onClickListener untuk setiap tombol
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                buttons[row][col].setOnClickListener(this);
            }
        }

        resetGame();
    }

    @Override
    public void onClick(View v) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (v == buttons[row][col]) {
                    if (isValidMove(row, col)) {
                        animateButton(row, col);
                        swapButtons(row, col, emptyRow, emptyCol);

                        if (isSolved()) {
                            Toast.makeText(this, "Puzzle Solved!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    private void animateButton(int row, int col) {
        TransitionManager.beginDelayedTransition((ViewGroup) gridLayout, createTransition());
        GridLayout.LayoutParams params = (GridLayout.LayoutParams) buttons[row][col].getLayoutParams();
        params.rowSpec = GridLayout.spec(emptyRow);
        params.columnSpec = GridLayout.spec(emptyCol);
        buttons[row][col].setLayoutParams(params);
    }

    private Transition createTransition() {
        ChangeBounds transition = new ChangeBounds();
        transition.setDuration(200);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        return transition;
    }

    private boolean isValidMove(int row, int col) {
        return ((row == emptyRow && Math.abs(col - emptyCol) == 1) ||
                (col == emptyCol && Math.abs(row - emptyRow) == 1));
    }

    private void swapButtons(int row1, int col1, int row2, int col2) {
        Button tempButton = buttons[row1][col1];
        buttons[row1][col1] = buttons[row2][col2];
        buttons[row2][col2] = tempButton;

        emptyRow = row1;
        emptyCol = col1;
    }

    private boolean isSolved() {
        int count = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (buttons[row][col].getText().toString().equals(String.valueOf(count))) {
                    count++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void onResetClick(View view) {
        resetGame();
    }

    private void resetGame() {
        int count = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                buttons[row][col].setText(String.valueOf(count));
                count++;
            }
        }
        emptyRow = 3;
        emptyCol = 3;
        buttons[emptyRow][emptyCol].setVisibility(View.INVISIBLE);
    }

}