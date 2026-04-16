package com.example.sudoku.cli;

import com.example.Assert;

import java.lang.reflect.Method;

public final class SudokuCliTest {
    public void main_exists() throws Exception {
        Method m = SudokuCli.class.getDeclaredMethod("main", String[].class);
        Assert.isTrue(m != null);
    }
}
