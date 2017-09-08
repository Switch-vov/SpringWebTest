package com.switchvov.test;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WebClientTest {
    @Test
    public void testWebClient() throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic MDI1NzcyYzcwNDYwMzk1Y2EyNjU2MjhmZDI4NWJkNjk6N2I3YzY4MDEzOGE4NWU3YTc2NzBjZTk0MzIxNDRiODE=");
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpHeaders.add(entry.getKey(), entry.getValue());
        }
        HttpEntity<?> requestEntity = new HttpEntity<>(null, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(URI.create("http://127.0.0.1:6166/candidate/download?candidateId=a7a1042b-37f1-4aa9-9510-16e0162f7e13"),
                HttpMethod.GET, requestEntity, byte[].class);
        String fileName = responseEntity.getHeaders().get("Content-Disposition").get(0).split("=")[1];
        FileOutputStream fos = new FileOutputStream("E:\\" + fileName);
        ByteArrayInputStream bais = new ByteArrayInputStream(responseEntity.getBody());
        FileCopyUtils.copy(bais,fos);
    }
}
