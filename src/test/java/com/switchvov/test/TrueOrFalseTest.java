package com.switchvov.test;

import org.junit.Test;

public class TrueOrFalseTest {
    @Test
    public void test() {
        boolean a = true;
        boolean b = false;
        System.out.println(a | b);
        System.out.println(a & b);
        System.out.println(a || b);
        System.out.println(a && b);
        a |= b;
        System.out.println(a);
    }
}
