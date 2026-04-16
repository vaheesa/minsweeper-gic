package com.example.sudoku;

public final class SudokuConstants {
    // Grid configuration
    public static final int GRID_SIZE = 9;
    public static final int BOX_SIZE = 3;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 9;
    public static final int EMPTY_CELL = 0;
    public static final int TOTAL_CELLS = GRID_SIZE * GRID_SIZE;

    // Welcome and game flow messages
    public static final String MSG_WELCOME = "Welcome to Sudoku!";
    public static final String MSG_PUZZLE_INTRO = "Here is your puzzle:";
    public static final String MSG_MOVE_ACCEPTED = "Move accepted.";
    public static final String MSG_CURRENT_GRID = "Current grid:";
    public static final String MSG_GAME_COMPLETE = "You have successfully completed the Sudoku puzzle!";
    public static final String MSG_PLAY_AGAIN = "Press any key to play again...";
    public static final String MSG_GOODBYE = "Goodbye.";

    // Input prompt
    public static final String MSG_ENTER_COMMAND = "Enter command (e.g., A3 4, C5 clear, hint, check): ";

    // Error messages
    public static final String MSG_INVALID_COMMAND = "Invalid command. Examples: B3 7 | C5 clear | hint | check | quit";
    public static final String MSG_INVALID_CELL = "Invalid cell. Use A-I for rows and 1-9 for columns (e.g., B3).";
    public static final String MSG_INVALID_VALUE = "Invalid value. Use 1-9 or 'clear'.";
    public static final String MSG_CELL_PREFILLED = "Invalid move. %s%d is pre-filled.";
    public static final String MSG_NO_HINT = "No hint available.";

    // Validation messages
    public static final String MSG_NO_VIOLATIONS = "No rule violations detected.";
    public static final String MSG_ROW_VIOLATION = "Number %d already exists in Row %c.";
    public static final String MSG_COL_VIOLATION = "Number %d already exists in Column %d.";
    public static final String MSG_SUBGRID_VIOLATION = "Number %d already exists in the same %dx%d subgrid";

    // Hint message
    public static final String MSG_HINT_FORMAT = "Hint: Cell %c%d = %d";

    private SudokuConstants() {}
}
