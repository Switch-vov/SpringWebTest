package com.switchvov.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class UnionTest {

    private Set<String> numbers;

    // @Before
    public void init() throws Exception {
        numbers = new HashSet<>(6500);
        FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\baza-numbers.txt"));
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (StringUtils.isNotBlank(line)) {
            numbers.add(line);
            line = br.readLine();
        }
    }

    /**
     * 获取数据SQL
     * SELECT DISTINCT user_name,union_id
     * FROM baza_user
     * ORDER BY user_name
     */
    @Test
    public void testUnionId() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\rc_tbd_users_20170825.txt"));
            FileWriter fw = new FileWriter(new File("C:\\Users\\jap\\Desktop\\rc_tbd-users-new-20170825.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String[] split = line.split(" ");
                StringBuilder builder = new StringBuilder(200)
                        .append("UPDATE user_info SET union_id = '")
                        .append(split[1])
                        .append("' WHERE user_name = '")
                        .append(split[0])
                        .append("';");
                String str = builder.toString();
                fw.append(str + "\n");
                System.out.println(str);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetMobile() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\tbd-users-20170816.txt"));
            FileWriter fw = new FileWriter(new File("C:\\Users\\jap\\Desktop\\tbd-users-new-20170816.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                if (!line.contains("@")) {
                    fw.append(line + "\n");
                    System.out.println(line);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetMobile2() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\tbd_users_20170825.txt"));
            FileWriter fw = new FileWriter(new File("C:\\Users\\jap\\Desktop\\tbd_users_new_20170825.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String[] split = line.split(" ");
                if (numbers.contains(split[0])) {
                    StringBuilder builder = new StringBuilder(200)
                            .append("UPDATE user_info SET union_id = '")
                            .append(split[1])
                            .append("' WHERE user_name = '")
                            .append(split[0])
                            .append("';");
                    String str = builder.toString();
                    fw.append(str + "\n");
                    System.out.println(str);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
