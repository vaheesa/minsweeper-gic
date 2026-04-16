package com.gic.sudoku.impl;

import com.gic.Assert;
import com.gic.sudoku.model.SudokuPuzzle;

import java.util.Random;

public final class RandomPuzzleGeneratorTest {
    public void generate_with30Clues_createsPuzzleWithAbout30FilledCells() {
        StandardGridValidator validator = new StandardGridValidator();
        BacktrackingSolver solver = new BacktrackingSolver(validator);
        RandomPuzzleGenerator generator = new RandomPuzzleGenerator(validator, solver);

        SudokuPuzzle puzzle = generator.generate(30, new Random(123));

        int[][] grid = puzzle.puzzle();
        int filled = 0;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (grid[r][c] != 0) {
                    filled++;
                }
            }
        }

        Assert.isTrue(filled >= 30);
        Assert.isTrue(filled <= 81);

        Assert.isTrue(validator.isValidGrid(puzzle.puzzle()));
        Assert.isTrue(validator.isValidGrid(puzzle.solution()));

        int[][] attempt = solver.copyOf(puzzle.puzzle());
        Assert.isTrue(solver.solve(attempt, new Random(456)));
        Assert.isTrue(validator.isValidGrid(attempt));
    }

    public void generate_invalidClueCount_throws() {
        StandardGridValidator validator = new StandardGridValidator();
        BacktrackingSolver solver = new BacktrackingSolver(validator);
        RandomPuzzleGenerator generator = new RandomPuzzleGenerator(validator, solver);

        Assert.throwsException(IllegalArgumentException.class, () -> generator.generate(10, new Random()));
    }
}
