package com.example.sudoku.impl;

import com.example.Assert;

public final class StandardGridValidatorTest {
    public void isValidGrid_withDuplicatesInRow_returnsFalse() {
        StandardGridValidator v = new StandardGridValidator();
        int[][] grid = new int[9][9];
        grid[0][0] = 5;
        grid[0][3] = 5;
        Assert.isFalse(v.isValidGrid(grid));
    }

    public void firstViolationMessage_rowDuplicate_returnsReadableMessage() {
        StandardGridValidator v = new StandardGridValidator();
        int[][] grid = new int[9][9];
        grid[0][0] = 3;
        grid[0][8] = 3;
        Assert.equalsString("Number 3 already exists in Row A.", v.firstViolationMessage(grid));
    }

    public void isValidGrid_withDuplicatesInColumn_returnsFalse() {
        StandardGridValidator v = new StandardGridValidator();
        int[][] grid = new int[9][9];
        grid[0][0] = 7;
        grid[8][0] = 7;
        Assert.isFalse(v.isValidGrid(grid));
    }

    public void isValidGrid_withDuplicatesInBox_returnsFalse() {
        StandardGridValidator v = new StandardGridValidator();
        int[][] grid = new int[9][9];
        grid[0][0] = 9;
        grid[1][1] = 9;
        Assert.isFalse(v.isValidGrid(grid));
    }

    public void isValidPlacement_conflictingRow_returnsFalse() {
        StandardGridValidator v = new StandardGridValidator();
        int[][] grid = new int[9][9];
        grid[2][1] = 4;
        Assert.isFalse(v.isValidPlacement(grid, 2, 8, 4));
    }

    public void isValidPlacement_nonConflicting_returnsTrue() {
        StandardGridValidator v = new StandardGridValidator();
        int[][] grid = new int[9][9];
        grid[2][1] = 4;
        Assert.isTrue(v.isValidPlacement(grid, 2, 8, 3));
    }
}
