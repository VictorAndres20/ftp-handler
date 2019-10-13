package com.vaptech.business.ftp;

import com.vaptech.business.FTPHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;

public class FTP extends FTPHandler<FTPClient> {

    private FTPClient ftpClient;

    /**
     * Constructor for a FTP Handler
     *
     * @param host      - Host or IP address of FTP server
     * @param port      - Port number to connect
     * @param user      - User credential to connect on FTP server
     */
    public FTP(String host, int port, String user, String password) {
        super(host, port, user);
        setPassword(password);
    }

    @Override
    public void buildClient() {
        ftpClient = new FTPClient();
        ftpClient.configure(new FTPClientConfig());
    }

    @Override
    public boolean connect() {
        try {
            ftpClient.connect(getHost(),getPort());
            ftpClient.login(getUser(),getPassword());
            return ftpClient.isConnected();
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("ex.getMessage() = " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            if(ftpClient != null)
                ftpClient.logout();

            if(ftpClient != null)
                ftpClient.disconnect();
            return true;
        } catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
            return false;
        }
    }

    @Override
    public LinkedList<HashMap<String, String>> listFiles(String remoteDirectory, String remoteFileWildcard) {
        LinkedList<HashMap<String, String>> files = new LinkedList<>();
        if(connect()){
            try {
                FTPFile[] retrievedFiles = retrieveFiles(remoteDirectory,remoteFileWildcard);
                for (FTPFile retrievedFile : retrievedFiles) {
                    HashMap<String, String> fileData = new HashMap<>();
                    fileData.put("name",retrievedFile.getName());
                    fileData.put("size",String.valueOf(retrievedFile.getSize()));
                    fileData.put("all",retrievedFile.toString());
                    files.add(fileData);
                }
            } catch (Exception ex){
                ex.printStackTrace();
            } finally {
                disconnect();
            }
        }
        return files;
    }

    @Override
    public LinkedList<HashMap<String, Object>> retrieveContent(String remoteDirectory, String remoteFileWildcard) {
        return null;
    }

    @Override
    public int saveFiles(String remoteDirectory, String remoteFileWildcard, String localDirectory) {
        return 0;
    }

    @Override
    public boolean putFile(String remoteDirectory, String fileName, String content) {
        return false;
    }

    @Override
    public int deleteFiles(String remoteDirectory, String remoteFileWildcard) {
        return 0;
    }

    private FTPFile[] retrieveFiles(String remoteDirectory, String remoteFileWildcard) {
        if(StringUtils.isNotBlank(remoteDirectory)){
            try {
                String remote = "/" +
                        remoteDirectory +
                        "/" +
                        (StringUtils.isNotBlank(remoteFileWildcard) ? remoteFileWildcard : "*");
                return ftpClient.listFiles(remote);
            } catch (IOException e) {
                e.printStackTrace();
                return new FTPFile[0];
            }
        } else
            return new FTPFile[0];

    }
}
