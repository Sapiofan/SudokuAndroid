package com.sapiofan.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
        game.setNumberPosition(1);
        sudokuBoard.invalidate();
    }

    public void BTNTwoUpdate(View view) {
        game.setNumberPosition(2);
        sudokuBoard.invalidate();
    }

    public void BTNThreeUpdate(View view) {
        game.setNumberPosition(3);
        sudokuBoard.invalidate();
    }

    public void BTNFourUpdate(View view) {
        game.setNumberPosition(4);
        sudokuBoard.invalidate();
    }

    public void BTNFiveUpdate(View view) {
        game.setNumberPosition(5);
        sudokuBoard.invalidate();
    }

    public void BTNSixUpdate(View view) {
        game.setNumberPosition(6);
        sudokuBoard.invalidate();
    }

    public void BTNSevenUpdate(View view) {
        game.setNumberPosition(7);
        sudokuBoard.invalidate();
    }

    public void BTNEightUpdate(View view) {
        game.setNumberPosition(8);
        sudokuBoard.invalidate();
    }

    public void BTNNineUpdate(View view) {
        game.setNumberPosition(9);
        sudokuBoard.invalidate();
    }
}