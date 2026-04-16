package com.example.sudoku.cli;

import static com.example.sudoku.SudokuConstants.*;

import com.example.sudoku.cli.CommandParser.Command;
import com.example.sudoku.cli.CommandParser.CommandType;
import com.example.sudoku.api.GridValidator;
import com.example.sudoku.api.PuzzleGenerator;
import com.example.sudoku.api.PuzzleSolver;
import com.example.sudoku.impl.BacktrackingSolver;
import com.example.sudoku.impl.RandomPuzzleGenerator;
import com.example.sudoku.impl.StandardGridValidator;
import com.example.sudoku.model.SudokuPuzzle;

import java.util.Random;
import java.util.Scanner;

public final class SudokuCli {
    private final GridValidator validator;
    private final PuzzleSolver solver;
    private final PuzzleGenerator generator;
    private final GridRenderer renderer;
    private final CommandParser parser;

    public SudokuCli(GridValidator validator, PuzzleSolver solver, PuzzleGenerator generator,
                     GridRenderer renderer, CommandParser parser) {
        this.validator = validator;
        this.solver = solver;
        this.generator = generator;
        this.renderer = renderer;
        this.parser = parser;
    }

    public SudokuCli() {
        this.validator = new StandardGridValidator();
        this.solver = new BacktrackingSolver(validator);
        this.generator = new RandomPuzzleGenerator(validator, solver);
        this.renderer = new GridRenderer();
        this.parser = new CommandParser();
    }

    public void run() {
        SudokuPuzzle puzzle = generator.generate(30, new Random());

        int[][] grid = solver.copyOf(puzzle.puzzle());
        int[][] solution = puzzle.solution();
        boolean[][] fixed = copyOf(puzzle.fixed());

        Scanner scanner = new Scanner(System.in);
        System.out.println(MSG_WELCOME);
        System.out.println();
        System.out.println(MSG_PUZZLE_INTRO);
        System.out.println(renderer.render(grid));

        while (true) {
            if (isSolved(grid, solution)) {
                System.out.println(MSG_GAME_COMPLETE);
                System.out.println(MSG_PLAY_AGAIN);
                scanner.nextLine();
                return;
            }

            System.out.print(MSG_ENTER_COMMAND);
            if (!scanner.hasNextLine()) {
                return;
            }
            String line = scanner.nextLine().trim();

            Command cmd = parser.parse(line);

            switch (cmd.type()) {
                case EMPTY -> { }
                case QUIT -> {
                    System.out.println(MSG_GOODBYE);
                    return;
                }
                case CHECK -> {
                    String violation = validator.firstViolationMessage(grid);
                    System.out.println(violation == null ? MSG_NO_VIOLATIONS : violation);
                }
                case HINT -> {
                    String hintMsg = applyHintWithMessage(grid, solution, fixed, new Random());
                    System.out.println(hintMsg != null ? hintMsg : MSG_NO_HINT);
                    System.out.println();
                }
                case INVALID -> System.out.println(MSG_INVALID_COMMAND);
                case INVALID_CELL -> System.out.println(MSG_INVALID_CELL);
                case INVALID_VALUE -> System.out.println(MSG_INVALID_VALUE);
                case CLEAR -> {
                    if (fixed[cmd.row()][cmd.col()]) {
                        printPrefilledError(cmd.row(), cmd.col(), grid);
                    } else {
                        grid[cmd.row()][cmd.col()] = EMPTY_CELL;
                        printMoveAccepted(grid);
                    }
                }
                case SET -> {
                    if (fixed[cmd.row()][cmd.col()]) {
                        printPrefilledError(cmd.row(), cmd.col(), grid);
                    } else {
                        grid[cmd.row()][cmd.col()] = cmd.value();
                        printMoveAccepted(grid);
                    }
                }
            }
        }
    }

    private void printPrefilledError(int row, int col, int[][] grid) {
        char rowName = (char) ('A' + row);
        int colName = col + 1;
        System.out.println();
        System.out.println(String.format(MSG_CELL_PREFILLED, rowName, colName));
        System.out.println();
        System.out.println(MSG_CURRENT_GRID);
        System.out.println(renderer.render(grid));
    }

    private void printMoveAccepted(int[][] grid) {
        System.out.println();
        System.out.println(MSG_MOVE_ACCEPTED);
        System.out.println();
        System.out.println(MSG_CURRENT_GRID);
        System.out.println(renderer.render(grid));
    }

    public static void main(String[] args) {
        new SudokuCli().run();
    }

    private static boolean isSolved(int[][] grid, int[][] solution) {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r][c] != solution[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String applyHintWithMessage(int[][] grid, int[][] solution, boolean[][] fixed, Random random) {
        int[] candidatesR = new int[TOTAL_CELLS];
        int[] candidatesC = new int[TOTAL_CELLS];
        int count = 0;

        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r][c] == EMPTY_CELL) {
                    candidatesR[count] = r;
                    candidatesC[count] = c;
                    count++;
                }
            }
        }

        if (count == 0) {
            return null;
        }

        int pick = random.nextInt(count);
        int r = candidatesR[pick];
        int c = candidatesC[pick];
        int value = solution[r][c];
        grid[r][c] = value;
        fixed[r][c] = true;

        char rowName = (char) ('A' + r);
        int colName = c + 1;
        return String.format(MSG_HINT_FORMAT, rowName, colName, value);
    }

    private static boolean[][] copyOf(boolean[][] fixed) {
        boolean[][] copy = new boolean[GRID_SIZE][GRID_SIZE];
        for (int r = 0; r < GRID_SIZE; r++) {
            System.arraycopy(fixed[r], 0, copy[r], 0, GRID_SIZE);
        }
        return copy;
    }

}
