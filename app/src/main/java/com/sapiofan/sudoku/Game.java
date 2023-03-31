package com.sapiofan.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private final Cell[][] initBoard;
    private final ArrayList<ArrayList<Integer>> emptyCells = new ArrayList<>();
    private final int SIZE;

    private int selectedRow = -1;
    private int selectedColumn = -1;

    public Game(int size) {
        SIZE = size;
        initBoard = GenerateBoard.generate(size, 40);
    }

    public ArrayList<ArrayList<Integer>> getEmptyBoxes() {
        for (int r = 0; r < initBoard.length; r++) {
            for (int c = 0; c < initBoard.length; c++) {
                if(initBoard[r][c].getValue() == 0) {
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
        if (selectedRow != -1 && selectedColumn != -1 &&
                !initBoard[selectedRow - 1][selectedColumn - 1].isFixed()) {
            if (initBoard[selectedRow - 1][selectedColumn - 1].getValue() == num) {
                initBoard[selectedRow - 1][selectedColumn - 1].setValue(0);
                return;
            }
            initBoard[selectedRow - 1][selectedColumn - 1].setValue(num);
        }
    }

    public Cell[][] getInitBoard() {
        return initBoard;
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
