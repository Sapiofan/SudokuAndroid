package com.sapiofan.sudoku;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SudokuBoard extends View {

    private final int boardColor;
    private final int cellFillColor;
    private final int cellHighlightColor;
    private final int numberColor;
    private final int userNumberColor;

    private final Paint boardColorPaint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellHighlightColorPaint = new Paint();
    private final Paint numberColorPaint = new Paint();
    private final Rect numberBounds = new Rect();

    private int cellSize;

    private final Game game = new Game();

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SudokuBoard, 0, 0);

        try {
            boardColor = array.getInteger(R.styleable.SudokuBoard_boardColor, 0);
            cellFillColor = array.getInteger(R.styleable.SudokuBoard_cellFillColor, 0);
            cellHighlightColor = array.getInteger(R.styleable.SudokuBoard_cellHighlightColor, 0);
            numberColor = array.getInteger(R.styleable.SudokuBoard_numberColor, 0);
            userNumberColor = array.getInteger(R.styleable.SudokuBoard_userNumberColor, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int length) {
        super.onMeasure(width, length);
        int dimension = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        setMeasuredDimension(dimension, dimension);
        cellSize = dimension / 9;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            game.setSelectedRow((int) (Math.ceil(y) / cellSize) + 1);
            game.setSelectedColumn((int) (Math.ceil(x) / cellSize) + 1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        cellFillColorPaint.setStyle(Paint.Style.FILL);
        cellFillColorPaint.setAntiAlias(true);
        cellFillColorPaint.setColor(cellFillColor);

        cellHighlightColorPaint.setStyle(Paint.Style.FILL);
        cellHighlightColorPaint.setAntiAlias(true);
        cellHighlightColorPaint.setColor(cellHighlightColor);

        numberColorPaint.setStyle(Paint.Style.FILL);
        numberColorPaint.setAntiAlias(true);
        numberColorPaint.setColor(numberColor);

        colorCell(canvas, game.getSelectedRow(), game.getSelectedColumn());
        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawBoard(canvas);
        drawNumbers(canvas);
    }

    private void colorCell(Canvas canvas, int r, int c) {
        if (game.getSelectedRow() != -1 && game.getSelectedColumn() != -1) {
            canvas.drawRect((c - 1) * cellSize, 0, c * cellSize, cellSize * 9, cellHighlightColorPaint);
            canvas.drawRect(0, (r - 1) * cellSize, cellSize * 9, r * cellSize, cellHighlightColorPaint);
            canvas.drawRect((c - 1) * cellSize, (r - 1) * cellSize, c * cellSize, r * cellSize, cellHighlightColorPaint);
        }

        invalidate();
    }

    private void drawThinLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(4);
        boardColorPaint.setColor(boardColor);
    }

    private void drawThickLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(10);
        boardColorPaint.setColor(boardColor);
    }

    private void drawBoard(Canvas canvas) {
        for (int c = 0; c < 10; c++) {
            if (c % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(cellSize * c, 0, cellSize * c, getWidth(), boardColorPaint);
        }

        for (int r = 0; r < 10; r++) {
            if (r % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(0, cellSize * r, getHeight(), cellSize * r, boardColorPaint);
        }
    }

    private void drawNumbers(Canvas canvas) {
        numberColorPaint.setTextSize(cellSize);

        int[][] board = game.getBoard();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] != 0) {
                    String text = Integer.toString(board[r][c]);

                    float width, height;
                    numberColorPaint.getTextBounds(text, 0, text.length(), numberBounds);

                    width = numberColorPaint.measureText(text);
                    height = numberBounds.height();

                    canvas.drawText(text, c * cellSize + (cellSize - width) / 2,
                            (r * cellSize + cellSize) - (cellSize - height) / 2, numberColorPaint);
                }
            }
        }

        numberColorPaint.setColor(userNumberColor);

        for (ArrayList<Integer> emptyBox : game.getEmptyBoxes()) {
            int r = emptyBox.get(0);
            int c = emptyBox.get(1);

            String text = Integer.toString(board[r][c]);

            float width, height;
            numberColorPaint.getTextBounds(text, 0, text.length(), numberBounds);

            width = numberColorPaint.measureText(text);
            height = numberBounds.height();

            canvas.drawText(text, c * cellSize + (cellSize - width) / 2,
                    (r * cellSize + cellSize) - (cellSize - height) / 2, numberColorPaint);
        }
    }

    public Game getGame() {
        return game;
    }
}
