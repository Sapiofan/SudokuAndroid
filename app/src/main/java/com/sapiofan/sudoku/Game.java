package com.sapiofan.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final Cell[][] initBoard;
    private final ArrayList<ArrayList<Integer>> emptyCells = new ArrayList<>();
    private final int SIZE;

    private int selectedRow = -1;
    private int selectedColumn = -1;

    public Game(int size, int missing) {
        SIZE = size;
        initBoard = GenerateBoard.generate(size, missing);
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

    public boolean setNumberPosition(int num) {
        if (selectedRow != -1 && selectedColumn != -1 &&
                !initBoard[selectedRow - 1][selectedColumn - 1].isFixed()) {
            if (initBoard[selectedRow - 1][selectedColumn - 1].getValue() == num) {
                initBoard[selectedRow - 1][selectedColumn - 1].setValue(0);
                return true;
            }
            if(checkPlaceForNumber(num)){
                initBoard[selectedRow - 1][selectedColumn - 1].setValue(num);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean checkPlaceForNumber(int num) {
        for (int i = 0; i < SIZE; i++) {
            if(initBoard[selectedRow - 1][i].getValue() == num) {
                return false;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if(initBoard[i][selectedColumn - 1].getValue() == num) {
                return false;
            }
        }

        List<Integer> group = defineGroupNumber();
        for (int i = -1; i <= 1; i++) { // not versatile, 3*3 only
            for (int j = -1; j <= 1; j++) {
                if(initBoard[group.get(0) + i][group.get(1) + j].getValue() == num) {
                    return false;
                }
            }
        }


        return true;
    }

    private List<Integer> defineGroupNumber() {
        List<Integer> groupCenter = new ArrayList<>();
        if((selectedRow - 1) % 9 >= 6) {
            groupCenter.add(7);
            if((selectedColumn - 1) % 9 >= 6) {
                groupCenter.add(7);
            } else if((selectedColumn - 1) % 9 >= 3) {
                groupCenter.add(4);
            } else if((selectedColumn - 1) % 9 >= 0) {
                groupCenter.add(1);
            }
        } else if((selectedRow - 1) % 9 >= 3) {
            groupCenter.add(4);
            if((selectedColumn - 1) % 9 >= 6) {
                groupCenter.add(7);
            } else if((selectedColumn - 1) % 9 >= 3) {
                groupCenter.add(4);
            } else if((selectedColumn - 1) % 9 >= 0) {
                groupCenter.add(1);
            }
        } else if((selectedRow - 1) % 9 >= 0) {
            groupCenter.add(1);
            if((selectedColumn - 1) % 9 >= 6) {
                groupCenter.add(7);
            } else if((selectedColumn - 1) % 9 >= 3) {
                groupCenter.add(4);
            } else if((selectedColumn - 1) % 9 >= 0) {
                groupCenter.add(1);
            }
        }
        return groupCenter;
    }

    public Cell[][] getInitBoard() {
        return initBoard;
    }

    public boolean checkIfUserWon() {
        Cell[][] board = getInitBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j].getValue() == 0) {
                    return false;
                }
            }
        }

        return true;
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
