package com.switchvov.test;

import com.switchvov.util.SqlHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.ResultSet;

public class JDBCTest {

    @Test
    public void testJDBC() {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\jap\\Desktop\\candidate_source_count_log_prod_20171016.txt"));
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(new File("C:\\Users\\jap\\Desktop\\candidate_source_count_log_insert_prod_20171016.txt"));

            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String sql = "SELECT DISTINCT owner_id, union_id FROM resume WHERE owner_id = '" + line + "' AND LENGTH(union_id) > 0";
                ResultSet resultSet = SqlHelper.executeQuery(sql, null);

                if (resultSet.next()) {
                    String ownerId = resultSet.getString("owner_id");
                    String unionId = resultSet.getString("union_id");

                    StringBuilder builder = new StringBuilder();
                    builder.append("UPDATE candidate_source_count_log SET union_id = '");
                    builder.append(unionId);
                    builder.append("' WHERE open_id = '");
                    builder.append(ownerId);
                    builder.append("';");
                    String updateSQL = builder.toString();
                    System.out.println(updateSQL);
                    fw.append(updateSQL + "\n");
                    fw.flush();
                }
                line = br.readLine();
                SqlHelper.close(resultSet, null, null);
            }
            SqlHelper.close(null, SqlHelper.getPs(), SqlHelper.getConnection());
            fr.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
