package com.switchvov.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringListTest {
    @Test
    public void testStringList() {
        List<String> strings = null;
        strings = Optional.ofNullable(strings).orElse(new ArrayList<>());
        strings.add("abc");
        strings.add("bcd");
        int count = strings.stream().collect(Collectors.joining(" ")).length();
        System.out.println(count);
    }
}
