package com.gic.sudoku.api;

import java.util.Random;

public interface PuzzleSolver {
    boolean solve(int[][] grid, Random random);
    boolean solve(int[][] grid);
    int[][] copyOf(int[][] grid);
}
