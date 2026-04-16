package com.gic;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.gic.sudoku.cli.SudokuCliTest;
import com.gic.sudoku.impl.BacktrackingSolverTest;
import com.gic.sudoku.impl.RandomPuzzleGeneratorTest;
import com.gic.sudoku.impl.StandardGridValidatorTest;
import com.gic.sudoku.model.SudokuPuzzleTest;

public final class TestRunner {
    public static void main(String[] args) throws Exception {
        int failures = 0;

        failures += runTests(AppTest.class);
        failures += runTests(SudokuCliTest.class);
        failures += runTests(RandomPuzzleGeneratorTest.class);
        failures += runTests(SudokuPuzzleTest.class);
        failures += runTests(BacktrackingSolverTest.class);
        failures += runTests(StandardGridValidatorTest.class);

        if (failures > 0) {
            System.err.println("FAILED (" + failures + " test(s))");
            System.exit(1);
        }

        System.out.println("OK");
    }

    private static int runTests(Class<?> testClass) throws Exception {
        Object instance = testClass.getDeclaredConstructor().newInstance();
        Method[] methods = testClass.getDeclaredMethods();

        int failures = 0;
        for (Method method : methods) {
            if (!isTestMethod(method)) {
                continue;
            }

            try {
                method.invoke(instance);
                System.out.println("PASS " + testClass.getSimpleName() + "." + method.getName());
            } catch (Throwable t) {
                failures++;
                Throwable cause = t.getCause() != null ? t.getCause() : t;
                System.err.println("FAIL " + testClass.getSimpleName() + "." + method.getName());
                System.err.println("  " + cause.getClass().getName() + ": " + cause.getMessage());
            }
        }

        return failures;
    }

    private static boolean isTestMethod(Method method) {
        String name = method.getName();
        if (!name.contains("_")) {
            return false;
        }
        if (method.isSynthetic()) {
            return false;
        }
        if (method.getParameterCount() != 0) {
            return false;
        }
        int mods = method.getModifiers();
        if (!Modifier.isPublic(mods)) {
            return false;
        }
        if (Modifier.isStatic(mods)) {
            return false;
        }
        return true;
    }
}
