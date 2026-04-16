package com.gic.sudoku.impl;

import static com.gic.sudoku.SudokuConstants.*;

import com.gic.sudoku.api.GridValidator;
import com.gic.sudoku.api.PuzzleGenerator;
import com.gic.sudoku.api.PuzzleSolver;
import com.gic.sudoku.model.SudokuPuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RandomPuzzleGenerator implements PuzzleGenerator {
    private final GridValidator validator;
    private final PuzzleSolver solver;

    public RandomPuzzleGenerator(GridValidator validator, PuzzleSolver solver) {
        this.validator = validator;
        this.solver = solver;
    }

    @Override
    public SudokuPuzzle generate(int clues, Random random) {
        if (clues < 17 || clues > TOTAL_CELLS) {
            throw new IllegalArgumentException("clues must be between 17 and 81");
        }

        int[][] solution = new int[GRID_SIZE][GRID_SIZE];
        if (!solver.solve(solution, random)) {
            throw new IllegalStateException("failed to generate solution");
        }

        int[][] puzzle = solver.copyOf(solution);

        List<int[]> cells = new ArrayList<>();
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                cells.add(new int[] { r, c });
            }
        }

        for (int i = cells.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] tmp = cells.get(i);
            cells.set(i, cells.get(j));
            cells.set(j, tmp);
        }

        int filled = TOTAL_CELLS;
        for (int[] cell : cells) {
            if (filled <= clues) {
                break;
            }

            int r = cell[0];
            int c = cell[1];
            int backup = puzzle[r][c];
            puzzle[r][c] = EMPTY_CELL;

            int[][] attempt = solver.copyOf(puzzle);
            if (!validator.isValidGrid(attempt) || !solver.solve(attempt, new Random(random.nextLong()))) {
                puzzle[r][c] = backup;
                continue;
            }

            filled--;
        }

        boolean[][] fixed = new boolean[GRID_SIZE][GRID_SIZE];
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                fixed[r][c] = puzzle[r][c] != EMPTY_CELL;
            }
        }

        return new SudokuPuzzle(puzzle, solution, fixed);
    }
}
