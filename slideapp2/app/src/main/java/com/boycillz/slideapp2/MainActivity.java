package com.boycillz.slideapp2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.boycillz.slideapp2.model.Board;
import com.boycillz.slideapp2.model.Place;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainView;

    /**
     * The game board.
     */
    private Board board;

    /**
     * The board view that generates the tiles and lines using 2-D graphics.
     */
    private BoardView boardView;

    /**
     * Text view to show the user the number of movements.
     */
    private TextView moves;

    /**
     * The board size. Default value is an 4x4 game.
     */
    private int boardSize = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = (ViewGroup) findViewById(R.id.main_layout);
        moves = (TextView) findViewById(R.id.moves);
        moves.setTextColor(Color.WHITE);
        moves.setTextSize(20);
        this.newGame();
    }

    private void newGame() {
        this.board = new Board(this.boardSize);
        this.board.addBoardChangeListener(boardChangeListener);
        this.board.rearrange();
        this.mainView.removeView(boardView);
        this.boardView = new BoardView(this, board);
        this.mainView.addView(boardView);
        this.moves.setText("Number of movements: 0");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void changeSize(int newSize) {
        if (newSize != this.boardSize) {
            this.boardSize = newSize;
            this.newGame();
            boardView.invalidate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                FragmentManager fragmentManager = getSupportFragmentManager();
                SettingsDialogFragment fragment = new SettingsDialogFragment(this.boardSize);
                fragment.show(fragmentManager, "Fragment Setting");
                break;
            case R.id.action_new_game:
                new AlertDialog.Builder(this)
                        .setTitle("Game Baru")
                        .setMessage("Apakah kamu yakin memulai game baru?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                board.rearrange();
                                moves.setText("Perpindahan Nomor : 0");
                                boardView.invalidate();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //tidak ada perintah
                            }
                        }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
            case R.id.action_help:
                new AlertDialog.Builder(this)
                        .setTitle("Intruksi")
                        .setMessage("Susun puzzle yang acak hingga tersusun rapih")
                        .setPositiveButton("Mengerti, mari bermain!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Board.BoardChangeListener boardChangeListener = new Board.BoardChangeListener() {
        @Override
        public void tileSlid(Place from, Place to, int numOfMoves) {
            moves.setText("Perpindahan Nomor : "
            + Integer.toString(numOfMoves));
        }

        @Override
        public void solved(int numOfMoves) {
            moves.setText("Berhasil dalam " + Integer.toString(numOfMoves) + " Perpindahan");
            Toast.makeText(getApplicationContext(), "Kamu menang!", Toast.LENGTH_LONG).show();
        }
    };

    public class SettingsDialogFragment extends DialogFragment {

        /**
         * The size.
         */
        private int size;

        /**
         * Instantiates a new settings dialog fragment.
         *
         * @param size the size
         */
        public SettingsDialogFragment(int size) {
            this.size = size;
        }

        /**
         * Sets the size.
         *
         * @param size the new size
         */
        void setSize(int size) {
            this.size = size;
        }

        /**
         * Gets the size.
         *
         * @return the size
         */
        int getSize() {
            return this.size;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle
         * )
         */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the dialog title
            builder.setTitle("Define the size of the puzzle")
                    .setSingleChoiceItems(R.array.size_options, this.size - 2,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    setSize(which + 2);

                                }

                            })
                    .setPositiveButton("Change",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    ((MainActivity) getActivity())
                                            .changeSize(getSize());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });

            return builder.create();
        }
    }

}