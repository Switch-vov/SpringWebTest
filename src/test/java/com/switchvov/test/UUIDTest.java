package com.switchvov.test;

import com.eaio.uuid.UUID;
import org.junit.Test;
import utils.uuid.UUIDUtils;

import java.util.concurrent.TimeUnit;

public class UUIDTest {
    @Test
    public void testUUID() throws InterruptedException {
        String s1 = UUIDUtils.create().toString();
        System.out.println(s1);
        TimeUnit.SECONDS.sleep(1);
        String s2 = UUIDUtils.create().toString();
        System.out.println(s2);
        TimeUnit.SECONDS.sleep(1);
        String s3 = UUIDUtils.create().toString();
        System.out.println(s3);
    }

    @Test
    public void testUUID2() {
        UUID uuid1 = new UUID();
        System.out.println(uuid1.toString());
        UUID uuid2 = new UUID();
        System.out.println(uuid2.toString());
        UUID uuid3 = new UUID();
        System.out.println(uuid3.toString());
    }
}
