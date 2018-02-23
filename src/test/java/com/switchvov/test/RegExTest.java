package com.switchvov.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {

    @Test
    public void test() {
        Pattern pattern = Pattern.compile("sales.");
        Matcher matcher = pattern.matcher("sales.xls\nsales.xls");
        boolean b = matcher.find();
        String[] split = pattern.split("sales.xls\nsales.xls");
        System.out.println(JSON.toJSONString(split));
        Predicate<String> stringPredicate = pattern.asPredicate();
        Assert.isTrue(b);
    }
}
