package com.gic.sudoku.impl;

import static com.gic.sudoku.SudokuConstants.*;

import com.gic.sudoku.api.GridValidator;
import com.gic.sudoku.api.PuzzleSolver;

import java.util.Random;

public final class BacktrackingSolver implements PuzzleSolver {
    private final GridValidator validator;

    public BacktrackingSolver(GridValidator validator) {
        this.validator = validator;
    }

    @Override
    public boolean solve(int[][] grid, Random random) {
        int[] cell = findEmptyCell(grid);
        if (cell == null) {
            return true;
        }

        int row = cell[0];
        int col = cell[1];

        int[] candidates = shuffledCandidates(random);
        for (int value : candidates) {
            if (!validator.isValidPlacement(grid, row, col, value)) {
                continue;
            }
            grid[row][col] = value;
            if (solve(grid, random)) {
                return true;
            }
            grid[row][col] = EMPTY_CELL;
        }

        return false;
    }

    @Override
    public boolean solve(int[][] grid) {
        return solve(grid, new Random());
    }

    @Override
    public int[][] copyOf(int[][] grid) {
        int[][] copy = new int[GRID_SIZE][GRID_SIZE];
        for (int r = 0; r < GRID_SIZE; r++) {
            System.arraycopy(grid[r], 0, copy[r], 0, GRID_SIZE);
        }
        return copy;
    }

    private static int[] findEmptyCell(int[][] grid) {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r][c] == EMPTY_CELL) {
                    return new int[] { r, c };
                }
            }
        }
        return null;
    }

    private static int[] shuffledCandidates(Random random) {
        int[] candidates = new int[MAX_VALUE];
        for (int i = 0; i < MAX_VALUE; i++) {
            candidates[i] = i + MIN_VALUE;
        }
        for (int i = candidates.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = candidates[i];
            candidates[i] = candidates[j];
            candidates[j] = tmp;
        }
        return candidates;
    }
}
