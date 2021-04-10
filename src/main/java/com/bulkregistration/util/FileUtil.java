package com.bulkregistration.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    public static void writeFile(String msisdn, String text) {
        try {
            File file = new File(msisdn + ".txt");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(text);
            }
        } catch (IOException e) {
            LOG.error("File write failed for msisdn {} ", msisdn);
        }
    }

}
