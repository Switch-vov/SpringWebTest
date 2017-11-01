package com.switchvov.test;

import org.junit.Test;

public class LocationTest {
    @Test
    public void testLocation() {
        double myLat = 40.009561;
        double myLng = 116.36718;

        double otherLat = 39.354451;
        double otherLng = 117.157363;

        long distance = Math.round(6378.138 * 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((myLat * Math.PI / 180 - otherLat * Math.PI / 180) / 2), 2) +
                Math.cos(myLat * Math.PI / 180) * Math.cos(otherLat * Math.PI / 180) * Math.pow(Math.sin((myLng * Math.PI / 180 - otherLng * Math.PI / 180) / 2), 2))) * 1000);
        System.out.println(distance);
    }

    @Test
    public void testLocation2() {
        double myLat = 40.009561;
        double myLng = 116.36718;

        double otherLat = 39.354451;
        double otherLng = 117.157363;

        long distance = Math.round(12756276 * Math.asin(Math.sqrt(Math.pow(Math.sin((myLat * Math.PI / 360 - otherLat * Math.PI / 360)), 2) +
                Math.cos(myLat * Math.PI / 180) * Math.cos(otherLat * Math.PI / 180) * Math.pow(Math.sin((myLng * Math.PI / 360 - otherLng * Math.PI / 360)), 2))));
        System.out.println(distance);
    }
}
