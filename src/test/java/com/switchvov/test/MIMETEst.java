package com.switchvov.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import utils.MIMEUtils;
import utils.UUIDUtils;

import java.io.*;

public class MIMETEst {
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\mime.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String[] split = line.split(" ");
                StringBuilder builder = new StringBuilder("mimeMap.put");
                builder.append("(\"" + split[0] + "\", \"" + split[1] + "\");");
                System.out.println(builder.toString());
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        String url = "http://wx.qlogo.cn/mmopen/PiajxSqBRaEJfjbyeZvbsn0yJx1K3VQnnoB2VicJPYsf5nZypdVY0bI2CibFgMKCwFkhkAx6fCszRz7fYlvEdHL6g/0";
        ResponseEntity<byte[]> obj = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        String contentType = obj.getHeaders().getContentType().toString();
        String fileSuffix = MIMEUtils.getFileSuffix(contentType);
        String fileName = UUIDUtils.genUUID() + "." + fileSuffix;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\jap\\Desktop\\" + fileName));
            StreamUtils.copy(obj.getBody(), fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        Integer i = null;
        int b = i;
        System.out.println(b);
    }
}
