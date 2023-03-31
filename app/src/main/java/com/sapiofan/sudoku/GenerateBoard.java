package com.sapiofan.sudoku;

public class GenerateBoard {

    private static int[][] board;
    private static int SIZE;
    private static int SQUARE_ROOT;

    public static Cell[][] generate(int size, int missing) {
        board = new int[size][size];
        SIZE = size;
        Double v = Math.sqrt(size);
        SQUARE_ROOT = v.intValue();
        System.out.println("Generater");
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SQUARE_ROOT);

        // Remove Randomly K digits to make game
        removeKDigits(missing);

        Cell[][] cells = new Cell[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if(board[r][c] != 0) {
                    cells[r][c] = new Cell(board[r][c], true);
                } else {
                    cells[r][c] = new Cell(0, false);
                }
            }
        }

        return cells;
    }

    private static void fillDiagonal() {

        for (int i = 0; i < SIZE; i = i + SQUARE_ROOT)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    private static boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SQUARE_ROOT; i++)
            for (int j = 0; j < SQUARE_ROOT; j++)
                if (board[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    private static void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SQUARE_ROOT; i++) {
            for (int j = 0; j < SQUARE_ROOT; j++) {
                do {
                    num = randomGenerator(SIZE);
                }
                while (!unUsedInBox(row, col, num));

                board[row + i][col + j] = num;
            }
        }
    }

    // Random generator
    private static int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    private static boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SQUARE_ROOT, j - j % SQUARE_ROOT, num));
    }

    // check in the row for existence
    private static boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < SIZE; j++)
            if (board[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    private static boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < SIZE; i++)
            if (board[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    private static boolean fillRemaining(int i, int j) {
        //  System.out.println(i+" "+j);
        if (j >= SIZE && i < SIZE - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= SIZE && j >= SIZE)
            return true;

        if (i < SQUARE_ROOT) {
            if (j < SQUARE_ROOT)
                j = SQUARE_ROOT;
        } else if (i < SIZE - SQUARE_ROOT) {
            if (j == (int) (i / SQUARE_ROOT) * SQUARE_ROOT)
                j = j + SQUARE_ROOT;
        } else {
            if (j == SIZE - SQUARE_ROOT) {
                i = i + 1;
                j = 0;
                if (i >= SIZE)
                    return true;
            }
        }

        for (int num = 1; num <= SIZE; num++) {
            if (CheckIfSafe(i, j, num)) {
                board[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;

                board[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    private static void removeKDigits(int missing) {
        int count = missing;
        while (count != 0) {
            int cellId = randomGenerator(SIZE * SIZE) - 1;

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId / SIZE);
            int j = cellId % 9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (board[i][j] != 0) {
                count--;
                board[i][j] = 0;
            }
        }
    }
}
