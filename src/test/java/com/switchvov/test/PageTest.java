package com.switchvov.test;

import org.junit.Test;

public class PageTest {
    @Test
    public void testPage() {
        Integer pageSize = 1000;
        Integer total = 4000;
        Integer pageCount = (total + pageSize - 1) / pageSize;
        System.out.println(pageCount);
    }
}
