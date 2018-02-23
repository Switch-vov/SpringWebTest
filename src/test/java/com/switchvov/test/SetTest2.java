package com.switchvov.test;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class SetTest2 {

    @Test
    public void testSet() {
        Set<String> defaultSetChinese = new LinkedHashSet<>();
        Set<String> defaultSetPinYin = new LinkedHashSet<>();
        defaultSetChinese.add("默认地图");
        defaultSetPinYin.add("MORENDITU");
        Set<String> chinese = new LinkedHashSet<>(defaultSetChinese);
        Set<String> pinyin = new LinkedHashSet<>(defaultSetPinYin);
        for (int i = 0; i < 10; i++) {
            chinese.add("地图：" + i);
            pinyin.add("map：" + i);
        }
        for (String s : pinyin) {
            System.out.println(s);
        }
        for (String s : chinese) {
            System.out.println(s);
        }
    }
}
