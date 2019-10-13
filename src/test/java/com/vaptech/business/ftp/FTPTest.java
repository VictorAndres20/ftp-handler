package com.vaptech.business.ftp;

import org.junit.Test;

import static org.junit.Assert.*;

public class FTPTest {

    @Test
    public void testConnection() {
        FTP ftp1 = new FTP("",21,"","");
        boolean conn1 = ftp1.testConnection();
        System.out.println("conn1 = " + conn1);
        assertTrue(conn1);

        FTP ftp2 = new FTP("",21,"","");
        boolean conn2 = ftp2.testConnection();
        System.out.println("conn2 = " + conn2);
        assertFalse(conn2);

        FTP ftp3 = new FTP("",21,"","");
        assertFalse(ftp3.testConnection());

        FTP ftp4 = new FTP("",21,"","");
        assertFalse(ftp4.testConnection());
    }

    @Test
    public void listFiles() {
    }
}