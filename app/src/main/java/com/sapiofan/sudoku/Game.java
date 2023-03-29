package com.sapiofan.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private final int[][] board;
    private final ArrayList<ArrayList<Integer>> emptyCells = new ArrayList<>();

    private int selectedRow = -1;
    private int selectedColumn = -1;

    public Game() {
        board = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getEmptyBoxes() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if(board[r][c] == 0) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(r);
                    arrayList.add(c);
                    emptyCells.add(arrayList);
                }
            }
        }

        return emptyCells;
    }

    public void setNumberPosition(int num) {
        if (selectedRow != -1 && selectedColumn != -1) {
            if (board[selectedRow - 1][selectedColumn - 1] == num) {
                board[selectedRow - 1][selectedColumn - 1] = 0;
                return;
            }
            board[selectedRow][selectedColumn] = num;
        }
    }

    public int[][] getBoard() {
        return Arrays.copyOf(board, board.length);
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }
}
