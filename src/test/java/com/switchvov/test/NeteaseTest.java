package com.switchvov.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedHashMap;
import java.util.Map;

public class NeteaseTest {
    private String appKey = "f40590eadb67d811b981d5daf3fe8023";

    private String appSecret = "f7a38c58bbcf";

    public static long unixTime(){
        return System.currentTimeMillis() / 1000L;
    }

    @Test
    public void getHttpHeaders(){
        Map<String,String> headers = new LinkedHashMap<>();
        String appKey = this.appKey;
        String appSecret = this.appSecret;
        String nonce = RandomStringUtils.randomAlphanumeric(8);
        String curTime = String.valueOf(NeteaseTest.unixTime());
        String checkSum = DigestUtils.sha1Hex(appSecret + nonce + curTime);
        headers.put("AppKey",appKey);
        headers.put("Nonce",nonce);
        headers.put("CurTime",curTime);
        headers.put("CheckSum",checkSum);
        System.out.println(headers.toString());
    }

}
