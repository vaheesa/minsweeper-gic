package com.example.sudoku.api;

import com.example.sudoku.model.SudokuPuzzle;

import java.util.Random;

public interface PuzzleGenerator {
    SudokuPuzzle generate(int clues, Random random);
}
