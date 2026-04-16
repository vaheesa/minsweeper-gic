package com.example.sudoku.cli;

import static com.example.sudoku.SudokuConstants.*;

public class GridRenderer {

    public String render(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int c = 1; c <= GRID_SIZE; c++) {
            sb.append(c);
            if (c < GRID_SIZE) sb.append(' ');
        }
        sb.append('\n');
        for (int r = 0; r < GRID_SIZE; r++) {
            sb.append((char) ('A' + r)).append("  ");
            for (int c = 0; c < GRID_SIZE; c++) {
                int v = grid[r][c];
                sb.append(v == EMPTY_CELL ? '_' : (char) ('0' + v));
                if (c < GRID_SIZE - 1) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
