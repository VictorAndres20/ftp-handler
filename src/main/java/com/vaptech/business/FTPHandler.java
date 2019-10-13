package com.vaptech.business;

import com.vaptech.contract.FTPContract;

/**
 * Abstract class that implements contract methods that
 * FTP handlers need to implement, and has all base attributes and actions
 *
 * @author Víctor Andrés Pedraza León
 * @since 1.8
 */
public abstract class FTPHandler<T> implements FTPContract {

    /**
     * FTP client class to use
     */
    private T ftpClient;

    /**
     * Host or IP address of FTP server
     */
    private String host;

    /**
     * Port number to connect
     */
    private int port;

    /**
     * User credential to connect on FTP server
     */
    private String user;

    /**
     * Password credential to connect on FTP server
     */
    private String password;

    /**
     * Constructor for a FTP Handler
     *
     * @param host - Host or IP address of FTP server
     * @param port - Port number to connect
     * @param user - User credential to connect on FTP server
     */
    public FTPHandler(String host, int port, String user) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.buildClient();
    }

    public boolean testConnection() {
        boolean conn = connect();
        System.out.println("FTPHandler.testConnection =" + conn);
        if(conn) disconnect();
        return conn;
    }

    public T getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(T ftpClient) {
        this.ftpClient = ftpClient;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
