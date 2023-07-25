package com.boycillz.puzzleabcdef;

import android.os.Bundle;
import androidx.transition.ChangeBounds;
import androidx.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.TransitionManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[4][4];
    private String[][] buttonValues = new String[4][4];
    private int emptyRow, emptyCol;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);

        initializeButtons();
        shuffleButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String buttonID = "button" + (i + 1) + (j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                buttonValues[i][j] = String.valueOf((i * 4) + (j + 1));
            }
        }
        emptyRow = 3;
        emptyCol = 3;
        buttons[emptyRow][emptyCol].setVisibility(View.INVISIBLE);
    }

    private void shuffleButtons() {
        // Reset button values
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttonValues[i][j] = String.valueOf((i * 4) + (j + 1));
            }
        }

        // Randomly swap button positions
        for (int i = 0; i < 1000; i++) {
            int randomDirection = (int) (Math.random() * 4);
            switch (randomDirection) {
                case 0: // Up
                    if (emptyRow > 0) {
                        swapButtons(emptyRow, emptyCol, emptyRow - 1, emptyCol);
                        emptyRow--;
                    }
                    break;
                case 1: // Down
                    if (emptyRow < 3) {
                        swapButtons(emptyRow, emptyCol, emptyRow + 1, emptyCol);
                        emptyRow++;
                    }
                    break;
                case 2: // Left
                    if (emptyCol > 0) {
                        swapButtons(emptyRow, emptyCol, emptyRow, emptyCol - 1);
                        emptyCol--;
                    }
                    break;
                case 3: // Right
                    if (emptyCol < 3) {
                        swapButtons(emptyRow, emptyCol, emptyRow, emptyCol + 1);
                        emptyCol++;
                    }
                    break;
            }
        }
    }

    private void swapButtons(int row1, int col1, int row2, int col2) {
        String tempValue = buttonValues[row1][col1];
        buttonValues[row1][col1] = buttonValues[row2][col2];
        buttonValues[row2][col2] = tempValue;

        buttons[row1][col1].setText(buttonValues[row1][col1]);
        buttons[row2][col2].setText(buttonValues[row2][col2]);

        buttons[row1][col1].setVisibility(View.VISIBLE);
        buttons[row2][col2].setVisibility(View.INVISIBLE);
    }

    private boolean isSolved() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!buttonValues[i][j].equals(String.valueOf(count))) {
                    return false;
                }
                count++;
            }
        }
        return true;
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
        return ((row == emptyRow && Math.abs(col - emptyCol) == 1)
                || (col == emptyCol && Math.abs(row - emptyRow) == 1));
    }
}