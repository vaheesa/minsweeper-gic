package com.example.sudoku.api;

public interface GridValidator {
    boolean isValidPlacement(int[][] grid, int row, int col, int value);
    boolean isValidGrid(int[][] grid);
    String firstViolationMessage(int[][] grid);
}
