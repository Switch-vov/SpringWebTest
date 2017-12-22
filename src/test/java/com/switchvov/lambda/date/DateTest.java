package com.switchvov.lambda.date;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateTest {
    @Test
    public void testDate() {
        LocalDate date = LocalDate.of(2017, 12, 11);
        System.out.println(date); // 2017-12-11
        System.out.println(date.getYear()); // 2017
        System.out.println(date.getMonth()); // DECEMBER
        System.out.println(date.getDayOfMonth()); // 11
        System.out.println(date.getDayOfWeek()); // MONDAY
        System.out.println(date.lengthOfMonth()); // 31
        System.out.println(date.isLeapYear()); // false

        LocalDate today = LocalDate.now(); // 获取今天Date

        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(year + " " + month + " " + day); // 2017 12 11

        LocalTime time = LocalTime.of(20, 14, 30);
        System.out.println(time.getHour()); // 20
        System.out.println(time.getMinute()); // 14
        System.out.println(time.getSecond()); // 30

        date = LocalDate.parse("2017-12-11");
        System.out.println(date); // 2017-12-11
        time = LocalTime.parse("20:14:30");
        System.out.println(time); // 20:14:30

        LocalDateTime dt1 = LocalDateTime.of(2017, Month.DECEMBER, 11, 20, 14, 30);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(20, 14, 30);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        date = dt1.toLocalDate();
        time = dt1.toLocalTime();

        System.out.println(Instant.ofEpochSecond(3)); // 1970-01-01T00:00:03Z
        System.out.println(Instant.ofEpochSecond(3, 1)); // 1970-01-01T00:00:03.000000001Z
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000)); // 1970-01-01T00:00:03Z
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000)); // 1970-01-01T00:00:03Z

        Duration d1 = Duration.between(time, time.plus(1, ChronoUnit.MINUTES));
        System.out.println(d1); // PT1M
        Duration d2 = Duration.between(dt1, dt1.plus(1, ChronoUnit.DAYS));
        System.out.println(d2); // PT24H
        Duration d3 = Duration.between(dt1, dt1.minus(1, ChronoUnit.DAYS));
        System.out.println(d3); // PT-24H
        Duration d4 = Duration.between(Instant.ofEpochSecond(0), Instant.ofEpochSecond(2, 1));
        System.out.println(d4); // PT2.000000001S

        System.out.println(Duration.ofMinutes(3)); // PT3M
        System.out.println(Duration.of(3, ChronoUnit.MINUTES)); // PT3M
        System.out.println(Period.ofDays(10)); // P10D
        System.out.println(Period.ofWeeks(3)); // P21D
        System.out.println(Period.of(2, 6, 1)); // P2Y6M1D

        LocalDate date1 = LocalDate.of(2017, 12, 11);
        System.out.println(date1); // 2017-12-11
        LocalDate date2 = date1.withYear(2014);
        System.out.println(date2); // 2014-12-11
        LocalDate date3 = date2.withDayOfMonth(10);
        System.out.println(date3); // 2014-12-10
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 5);
        System.out.println(date4); // 2014-05-10

        LocalDate date5 = date4.plusWeeks(1);
        System.out.println(date5); // 2014-05-17
        LocalDate date6 = date5.minusYears(3);
        System.out.println(date6); // 2011-05-17
        LocalDate date7 = date6.plus(6, ChronoUnit.MONTHS);
        System.out.println(date7); // 2011-11-17

        LocalDate date8 = date7.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date8); // 2011-11-20
        LocalDate date9 = date8.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date9); // 2011-11-30
    }


}


