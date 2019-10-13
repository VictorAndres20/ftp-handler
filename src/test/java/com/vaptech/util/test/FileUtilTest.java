package com.vaptech.util.test;

import com.vaptech.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void readBytesFile() {
        File fileInput = new File("./data/inputs/test1.json");
        byte[] bytes = FileUtil.readBytesFile(fileInput);
        System.out.println(bytes != null ? Arrays.toString(bytes) : "NULL My GOD!");
        assertNotNull(bytes);
        assertNotEquals(0,bytes.length);
    }

    @Test
    public void readContentString() {
        File fileInput = new File("./data/inputs/test1.json");
        String content = FileUtil.readContentString(fileInput);
        System.out.println("content = " + content);
        assertNotNull(content);
    }

    @Test
    public void stringBytes() {
        File fileInput = new File("./data/inputs/test1.json");
        String content = FileUtil.readContentString(fileInput);
        assertNotEquals(0,FileUtil.stringBytes(content));
    }

    @Test
    public void writeBytesFile() {
        File fileInput1 = new File("./data/inputs/test1.json");
        byte[] bytes = FileUtil.readBytesFile(fileInput1);
        boolean result = FileUtil.writeBytesFile(new File("./data/outputs/test4.json"),bytes);
        assertTrue(result);

        String content = FileUtil.readContentString(fileInput1);
        assertTrue(FileUtil.writeBytesFile(new File("./data/outputs/test5.json"),
                FileUtil.stringBytes(content)));
    }
}