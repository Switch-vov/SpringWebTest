package com.switchvov.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UnionTest3 {
    @Test
    public void testUnionId() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\candidate_count_log_prod_20171013.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            StringBuilder builder = new StringBuilder();
            while (StringUtils.isNotBlank(line)) {
                builder.append("'" + line + "',");
                line = br.readLine();
            }
            System.out.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
