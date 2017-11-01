package com.switchvov.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by jap on 2017/6/30.
 */
public class StringTest {

    public static void main(String[] args) {
        String str = "1234";
        changeStr(str);
        System.out.println(str);
    }

    private static void changeStr(String str) {
        str = "welcome";
    }

    @Test
    public void test() {
        String str = "18675522196";
        String[] split = str.split(",");
        Arrays.asList(split).forEach(System.out::println);
    }
}
