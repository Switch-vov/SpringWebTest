package com.switchvov.lambda.processFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileProcess {
    public static String processFile(Processor<BufferedReader, String> processor) throws Exception {
        InputStream input = FileProcess.class.getClassLoader().getResourceAsStream("process-file-data.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            return processor.process(br);
        }
    }
}
