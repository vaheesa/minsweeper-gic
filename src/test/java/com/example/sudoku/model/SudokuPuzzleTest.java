package com.example.sudoku.model;

import com.example.Assert;

public final class SudokuPuzzleTest {
    public void getters_returnSameReferences() {
        int[][] puzzle = new int[9][9];
        int[][] solution = new int[9][9];
        boolean[][] fixed = new boolean[9][9];

        SudokuPuzzle p = new SudokuPuzzle(puzzle, solution, fixed);
        Assert.isTrue(p.puzzle() == puzzle);
        Assert.isTrue(p.solution() == solution);
        Assert.isTrue(p.fixed() == fixed);
    }
}
