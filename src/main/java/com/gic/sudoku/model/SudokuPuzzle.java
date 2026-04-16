package com.gic.sudoku.model;

public record SudokuPuzzle(int[][] puzzle, int[][] solution, boolean[][] fixed) {
    public SudokuPuzzle {
        if (puzzle == null || solution == null || fixed == null) {
            throw new IllegalArgumentException("puzzle, solution, and fixed must not be null");
        }
    }
}
