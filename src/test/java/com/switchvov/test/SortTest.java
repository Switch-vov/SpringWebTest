package com.switchvov.test;

import com.switchvov.model.SortObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by Switch on 2018/05/18.
 */
public class SortTest {
    @Test
    public void testSort() {
        String fileName = "E:\\websites\\baza-ms-name-list-index-dev\\log\\all.log";
        try (Stream<String> lines = Files.lines(Paths.get(fileName), Charset.defaultCharset())) {
            FileWriter writer = new FileWriter(fileName + ".resort.log");
            lines.map(string -> {
                String[] split = string.split("\t");
                return SortObject.getInstance().setCompany(split[0]).setStandardId(split[1]).setStandardName(split[2]);
            }).filter(object -> StringUtils.isNotBlank(object.getCompany()))
                    .sorted(Comparator.comparing(SortObject::getStandardName))
                    .forEachOrdered(object -> {
                        try {
                            writer.append(object.getCompany() + "\t" + object.getStandardId() + "\t" + object.getStandardName() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
