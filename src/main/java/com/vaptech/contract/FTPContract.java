package com.vaptech.contract;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Interface that has the contract methods that
 * FTP handlers need to implement
 *
 * @author Víctor Andrés Pedraza León
 * @since 1.8
 */
public interface FTPContract {

    /**
     * Construct FTP client class
     */
    void buildClient();

    /**
     * Connect to FTP server
     *
     * @return boolean - true if connect successfully, false if not
     */
    boolean connect();

    /**
     * Disconnect to FTP server
     *
     * @return boolean - true if connect successfully, false if not
     */
    boolean disconnect();

    /**
     *
     *
     * @param remoteDirectory - FTP servers' directory
     * @param remoteFileWildcard - FTP server's file or Wildcard
     * @return HasMap LinkedList where store files' name, size and long name
     */
    LinkedList<HashMap<String,String>> listFiles(String remoteDirectory, String remoteFileWildcard);

    /**
     * Retrieve content from files listed
     *
     * @param remoteDirectory - FTP servers' directory
     * @param remoteFileWildcard - FTP server's file or Wildcard
     * @return HasMap LinkedList where store files' name, content, bytes
     */
    LinkedList<HashMap<String,Object>> retrieveContent(String remoteDirectory, String remoteFileWildcard);

    /**
     * Save files locally retrieved
     *
     * @param remoteDirectory - FTP servers' directory
     * @param remoteFileWildcard - FTP server's file or Wildcard
     * @param localDirectory - Local directory where store retrieved files
     * @return Integer - Quantity of files saved
     */
    int saveFiles(String remoteDirectory, String remoteFileWildcard, String localDirectory);

    /**
     * Put file in FTP server
     *
     * @param remoteDirectory - FTP servers' directory
     * @param fileName - File name to put
     * @param content - Content to save
     * @return boolean - true if can save, false if not
     */
    boolean putFile(String remoteDirectory, String fileName, String content);

    /**
     * Delete files on FTP server
     *
     * @param remoteDirectory - FTP servers' directory
     * @param remoteFileWildcard - FTP server's file or Wildcard
     * @return Integer - Quantity of files deleted
     */
    int deleteFiles(String remoteDirectory, String remoteFileWildcard);
}
