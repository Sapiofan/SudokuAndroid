package com.sapiofan.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SudokuBoard sudokuBoard;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuBoard = findViewById(R.id.SudokuBoard);
        game = sudokuBoard.getGame();
    }

    public void BTNOneUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(1));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNTwoUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(2));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNThreeUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(3));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNFourUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(4));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNFiveUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(5));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNSixUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(6));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNSevenUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(7));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNEightUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(8));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    public void BTNNineUpdate(View view) {
        checkWrongNumberPlace(game.setNumberPosition(9));
        checkIfUserWon(game.checkIfUserWon());
        sudokuBoard.invalidate();
    }

    private void checkIfUserWon(boolean checkIfUserWon) {
        TextView textView = findViewById(R.id.error);
        textView.setText("Congratulations. You won");
    }

    private void checkWrongNumberPlace(boolean error) {
        TextView textView = findViewById(R.id.error);
        if(!error) {
            textView.setText("Number can't be set here, because the same number exists in row, column or group");
        } else {
            textView.setText("");
        }
    }


}