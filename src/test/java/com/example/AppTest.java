package com.example;

import java.lang.reflect.Method;

public final class AppTest {
    public void main_exists() throws Exception {
        Method m = App.class.getDeclaredMethod("main", String[].class);
        Assert.isTrue(m != null);
    }
}
