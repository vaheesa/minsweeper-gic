package com.gic;

public final class Assert {
    private Assert() {}

    public static void equalsInt(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }

    public static void isTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public static void isFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("Expected condition to be false");
        }
    }

    public static void equalsString(String expected, String actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        throw new AssertionError("Expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    public static void throwsException(Class<? extends Throwable> expected, Runnable action) {
        try {
            action.run();
        } catch (Throwable t) {
            if (expected.isInstance(t)) {
                return;
            }
            throw new AssertionError(
                    "Expected exception of type " + expected.getName() + " but got " + t.getClass().getName(),
                    t);
        }
        throw new AssertionError("Expected exception of type " + expected.getName() + " but nothing was thrown");
    }
}
