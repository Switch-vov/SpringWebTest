package com.switchvov.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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

    @Test
    public void testJson3() {
        TBDResponse<JSONObject> response = JSON.parseObject("{  \"data\": {    \"duplicateIds\": [      \"a84d0464-c3ed-45c6-bc82-55d6d8c56713\"    ]  },  \"success\": false,  \"error\": {    \"code\": \"Duplicate\",    \"description\": \"简历重复\"  }}", TBDResponse.class);
        System.out.println(response);
        System.out.println(response.getData().getJSONArray("duplicateIds").toArray()[0]);
        TBDResponse<JSONObject> response2 = JSON.parseObject("{  \"data\": \"a84d0469-ad81-4f1a-b0e7-d6ca9244a2c6\",  \"success\": true,  \"error\": null}", TBDResponse.class);
        System.out.println(response2);
        String resumeId = String.valueOf(response2.getData());
        System.out.println(resumeId);
    }

    @Test
    public void testJSON4() {
//        String s = JSON.toJSONString(Optional.empty());
//        System.out.println(s);
//        TBDResponse response = JSON.parseObject(s, TBDResponse.class);
//        System.out.println(response);
        TBDResponse response = JSON.parseObject("null", TBDResponse.class);
        System.out.println(response);
    }
}

class TBDResponse<D> {

    private boolean success;
    private D data;
    private Error error;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public static class Error {

        private String code;
        private String description;

        public Error() {

        }

        public Error(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @Override
    public String toString() {
        return "TBDResponse{" +
                "success=" + success +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}


class Mobile {
    Long mobile;
    Integer code;

    public Mobile() {

    }

    public Mobile(Long mobile) {
        this.mobile = mobile;
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        this.code = localRandom.nextInt(1000, 10000);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
