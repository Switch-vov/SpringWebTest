package com.switchvov.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UnionTest2 {

    private Map<String , String> logs;

    @Before
    public void init() {
        try {
            logs = new HashMap<>();
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\tbd_users_20170825.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String[] split = line.split(" ");
                String userName = split[0];
                String unionId = split[1];
                logs.put(userName,unionId);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnionId() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\candidate_op_union_id_dev.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String[] split = line.split(" ");
                StringBuilder builder = new StringBuilder(200)
                        .append("UPDATE candidate_op_log SET union_id = '")
                        .append(split[1])
                        .append("' WHERE user_id = '")
                        .append(split[0])
                        .append("';");
                String str = builder.toString();
                System.out.println(str);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testActivityUnionId() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\baza_activity_log.txt"));
            FileWriter fw = new FileWriter(new File("C:\\Users\\jap\\Desktop\\baza_activity_log_update_20170825.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String[] split = line.split(" ");
                StringBuilder builder = new StringBuilder(200)
                        .append("UPDATE activity_log SET receiver_union_id = '")
                        .append(logs.get(split[0]))
                        .append("' WHERE id = '")
                        .append(split[1])
                        .append("';");
                String str = builder.toString();
                System.out.println(str);
                fw.append(str + "\n");
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
