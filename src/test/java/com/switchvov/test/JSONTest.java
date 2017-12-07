package com.switchvov.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class JSONTest {
    @Test
    public void testJson() {
        String json = "{\"isWaitUpdate\":true}";
        Tas tas = JSON.parseObject(json, Tas.class);
        System.out.println(tas);
    }

    @Test
    public void testSetAndList() {
        List<String> list = Arrays.asList("abc", "bcd", "cde");
        Set<String> set = new HashSet<>();
        set.add("123");
        set.add("234");
        set.add("345");
        set.add("abc");
        Collection collectionSet = set;
        Collection collectionList = list;
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(collectionList));

        System.out.println(JSON.toJSONString(set));
        System.out.println(JSON.toJSONString(collectionSet));

        set.addAll(list);
        System.out.println(JSON.toJSONString(set));
    }

    @Test
    public void testJson2() {
        List<Mobile> mobiles = LongStream.rangeClosed(15100000031L, 15100000200L)
                .mapToObj(Mobile::new)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(mobiles));
    }
}

class Mobile {
    Long mobile;

    public Mobile() {

    }

    public Mobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }
}

class Tas {
    private Boolean isWaitUpdate;

    public Boolean getWaitUpdate() {
        return isWaitUpdate;
    }

    public void setWaitUpdate(Boolean waitUpdate) {
        isWaitUpdate = waitUpdate;
    }
}
