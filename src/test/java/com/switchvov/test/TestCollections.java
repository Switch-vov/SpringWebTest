package com.switchvov.test;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestCollections {

    @Test
    public void testCollections() {
        List<String> meToFriendUnionIds = new ArrayList<>();
        meToFriendUnionIds.add("a7e70427-b1d1-406b-89ad-04796eba6010");
        meToFriendUnionIds.add("a7e70425-f915-400c-b317-6afbac956af3");
        meToFriendUnionIds.add("a7e8022f-f5da-4da0-88c5-826e95a0a9e7");
        List<String> friendToMeUnionIds = new ArrayList<>();
        friendToMeUnionIds.add("a7e70427-b1d1-406b-89ad-04796eba6010");
        friendToMeUnionIds.add("a7e70425-f915-400c-b317-6afbac956af3");
        List<String> intersection = (List<String>) CollectionUtils.intersection(meToFriendUnionIds, friendToMeUnionIds);
        System.out.println(intersection);
    }
}
