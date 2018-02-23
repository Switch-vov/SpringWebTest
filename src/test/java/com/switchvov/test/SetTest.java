package com.switchvov.test;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class SetTest {
    private Set<String> set = new LinkedHashSet<>();

    @Before
    public void init() {
        set.add("aaa");
        set.add("bbb");
        set.add("ddd");
        set.add("ccc");
    }

    @Test
    public void testSet() {
        for (String s : set) {
            System.out.println(s);
        }
    }
}
