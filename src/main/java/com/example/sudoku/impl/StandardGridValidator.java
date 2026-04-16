package com.example.sudoku.impl;

import static com.example.sudoku.SudokuConstants.*;

import com.example.sudoku.api.GridValidator;

public final class StandardGridValidator implements GridValidator {

    @Override
    public boolean isValidPlacement(int[][] grid, int row, int col, int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            return false;
        }

        for (int c = 0; c < GRID_SIZE; c++) {
            if (c != col && grid[row][c] == value) {
                return false;
            }
        }

        for (int r = 0; r < GRID_SIZE; r++) {
            if (r != row && grid[r][col] == value) {
                return false;
            }
        }

        int boxRow = (row / BOX_SIZE) * BOX_SIZE;
        int boxCol = (col / BOX_SIZE) * BOX_SIZE;
        for (int r = boxRow; r < boxRow + BOX_SIZE; r++) {
            for (int c = boxCol; c < boxCol + BOX_SIZE; c++) {
                if ((r != row || c != col) && grid[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String firstViolationMessage(int[][] grid) {
        for (int r = 0; r < GRID_SIZE; r++) {
            boolean[] seen = new boolean[MAX_VALUE + 1];
            for (int c = 0; c < GRID_SIZE; c++) {
                int v = grid[r][c];
                if (v == EMPTY_CELL) {
                    continue;
                }
                if (seen[v]) {
                    char rowName = (char) ('A' + r);
                    return String.format(MSG_ROW_VIOLATION, v, rowName);
                }
                seen[v] = true;
            }
        }

        for (int c = 0; c < GRID_SIZE; c++) {
            boolean[] seen = new boolean[MAX_VALUE + 1];
            for (int r = 0; r < GRID_SIZE; r++) {
                int v = grid[r][c];
                if (v == EMPTY_CELL) {
                    continue;
                }
                if (seen[v]) {
                    int colName = c + 1;
                    return String.format(MSG_COL_VIOLATION, v, colName);
                }
                seen[v] = true;
            }
        }

        for (int boxRow = 0; boxRow < GRID_SIZE; boxRow += BOX_SIZE) {
            for (int boxCol = 0; boxCol < GRID_SIZE; boxCol += BOX_SIZE) {
                boolean[] seen = new boolean[MAX_VALUE + 1];
                for (int r = boxRow; r < boxRow + BOX_SIZE; r++) {
                    for (int c = boxCol; c < boxCol + BOX_SIZE; c++) {
                        int v = grid[r][c];
                        if (v == EMPTY_CELL) {
                            continue;
                        }
                        if (seen[v]) {
                            return String.format(MSG_SUBGRID_VIOLATION, v, BOX_SIZE, BOX_SIZE);
                        }
                        seen[v] = true;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public boolean isValidGrid(int[][] grid) {
        for (int r = 0; r < GRID_SIZE; r++) {
            boolean[] seen = new boolean[MAX_VALUE + 1];
            for (int c = 0; c < GRID_SIZE; c++) {
                int v = grid[r][c];
                if (v == EMPTY_CELL) {
                    continue;
                }
                if (seen[v]) {
                    return false;
                }
                seen[v] = true;
            }
        }

        for (int c = 0; c < GRID_SIZE; c++) {
            boolean[] seen = new boolean[MAX_VALUE + 1];
            for (int r = 0; r < GRID_SIZE; r++) {
                int v = grid[r][c];
                if (v == EMPTY_CELL) {
                    continue;
                }
                if (seen[v]) {
                    return false;
                }
                seen[v] = true;
            }
        }

        for (int boxRow = 0; boxRow < GRID_SIZE; boxRow += BOX_SIZE) {
            for (int boxCol = 0; boxCol < GRID_SIZE; boxCol += BOX_SIZE) {
                boolean[] seen = new boolean[MAX_VALUE + 1];
                for (int r = boxRow; r < boxRow + BOX_SIZE; r++) {
                    for (int c = boxCol; c < boxCol + BOX_SIZE; c++) {
                        int v = grid[r][c];
                        if (v == EMPTY_CELL) {
                            continue;
                        }
                        if (seen[v]) {
                            return false;
                        }
                        seen[v] = true;
                    }
                }
            }
        }

        return true;
    }
}
