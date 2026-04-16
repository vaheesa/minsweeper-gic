package com.gic.sudoku.impl;

import com.gic.Assert;

public final class BacktrackingSolverTest {
    public void solve_emptyGrid_producesValidSolution() {
        StandardGridValidator validator = new StandardGridValidator();
        BacktrackingSolver solver = new BacktrackingSolver(validator);

        int[][] grid = new int[9][9];
        Assert.isTrue(solver.solve(grid));
        Assert.isTrue(validator.isValidGrid(grid));

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Assert.isTrue(grid[r][c] >= 1 && grid[r][c] <= 9);
            }
        }
    }

    public void copyOf_createsDeepCopy() {
        StandardGridValidator validator = new StandardGridValidator();
        BacktrackingSolver solver = new BacktrackingSolver(validator);

        int[][] grid = new int[9][9];
        grid[0][0] = 1;
        int[][] copy = solver.copyOf(grid);
        copy[0][0] = 9;

        Assert.equalsInt(1, grid[0][0]);
        Assert.equalsInt(9, copy[0][0]);
    }
}
