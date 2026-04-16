package com.gic.sudoku.cli;

import java.util.Locale;

public class CommandParser {

    public Command parse(String input) {
        if (input == null || input.isBlank()) {
            return new Command(CommandType.EMPTY, null, -1, -1, -1);
        }

        String normalized = input.trim().toLowerCase(Locale.ROOT);

        if (normalized.equals("quit") || normalized.equals("exit")) {
            return new Command(CommandType.QUIT, null, -1, -1, -1);
        }
        if (normalized.equals("check")) {
            return new Command(CommandType.CHECK, null, -1, -1, -1);
        }
        if (normalized.equals("hint")) {
            return new Command(CommandType.HINT, null, -1, -1, -1);
        }

        String[] parts = input.trim().split("\\s+");
        if (parts.length != 2) {
            return new Command(CommandType.INVALID, null, -1, -1, -1);
        }

        CellRef ref = parseCellRef(parts[0]);
        if (ref == null) {
            return new Command(CommandType.INVALID_CELL, null, -1, -1, -1);
        }

        String action = parts[1].toLowerCase(Locale.ROOT);
        if (action.equals("clear")) {
            return new Command(CommandType.CLEAR, ref, ref.row(), ref.col(), -1);
        }

        Integer value = parseDigit(action);
        if (value == null) {
            return new Command(CommandType.INVALID_VALUE, null, -1, -1, -1);
        }

        return new Command(CommandType.SET, ref, ref.row(), ref.col(), value);
    }

    private CellRef parseCellRef(String token) {
        if (token.length() != 2) {
            return null;
        }

        char rowCh = Character.toUpperCase(token.charAt(0));
        char colCh = token.charAt(1);

        if (rowCh < 'A' || rowCh > 'I') {
            return null;
        }
        if (colCh < '1' || colCh > '9') {
            return null;
        }

        return new CellRef(rowCh - 'A', colCh - '1');
    }

    private Integer parseDigit(String s) {
        if (s.length() != 1) {
            return null;
        }
        char ch = s.charAt(0);
        if (ch < '1' || ch > '9') {
            return null;
        }
        return ch - '0';
    }

    public record CellRef(int row, int col) {}

    public record Command(CommandType type, CellRef cell, int row, int col, int value) {}

    public enum CommandType {
        SET, CLEAR, HINT, CHECK, QUIT, EMPTY, INVALID, INVALID_CELL, INVALID_VALUE
    }
}
