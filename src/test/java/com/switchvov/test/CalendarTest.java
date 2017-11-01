package com.switchvov.test;

import org.junit.Test;
import utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    @Test
    public void test() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(cal.getTime()));
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 17);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(dateFormat.format(cal.getTime()));
        Date now = new Date();
        if (now.after(cal.getTime())) {
            cal.add(Calendar.DATE, 1);
        }
        long delay = cal.getTimeInMillis() - System.currentTimeMillis();
        System.out.println(dateFormat.format(cal.getTime()));
        System.out.println(delay);
    }

    @Test
    public void test2() {
        String s = UUIDUtils.genUUID();
        System.out.println(s);
    }
}
