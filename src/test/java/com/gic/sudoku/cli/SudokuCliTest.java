package com.gic.sudoku.cli;

import com.gic.Assert;

import java.lang.reflect.Method;

public final class SudokuCliTest {
    public void main_exists() throws Exception {
        Method m = SudokuCli.class.getDeclaredMethod("main", String[].class);
        Assert.isTrue(m != null);
    }
}
