package com.switchvov.test;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jap on 2017/6/24.
 */
public class DateTest {

    @Test
    public void testDate() {
        Date newDate = DateUtils.addDays(new Date(), 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDate);
        System.out.println("Current Day :" + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("Current Day of Week is : " + dayOfWeek);

        int offset = 7 - dayOfWeek;
        if (dayOfWeek == 0) {
            offset = 0;
        }
        cal.add(Calendar.DATE, offset - 7);
        System.out.println("last Sunday is : " + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        cal.add(Calendar.DATE, -28);
        System.out.println("last last Sunday is : " + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
    }

    @Test
    public void testParseDate() throws ParseException {
        String s1 = "05/07/2018 16:03:14";
        String s2 = "2018/5/9 16:06:50";
        String[] patterns = {"MM/dd/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};
        Date date1 = DateUtils.parseDate(s1, patterns);
        Date date2 = DateUtils.parseDate(s2, patterns);
        System.out.println();
    }
}
