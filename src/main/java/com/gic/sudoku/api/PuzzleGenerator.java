package com.gic.sudoku.api;

import com.gic.sudoku.model.SudokuPuzzle;

import java.util.Random;

public interface PuzzleGenerator {
    SudokuPuzzle generate(int clues, Random random);
}
