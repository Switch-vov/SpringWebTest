package com.switchvov.lambda.processFile;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;

public class ProcessorTest {
    @Test
    public void test() throws Exception {
        System.out.println(FileProcess.processFile(BufferedReader::readLine));
        System.out.println(FileProcess.processFile(bufferedReader -> bufferedReader.readLine() + bufferedReader.readLine()));
    }
}
