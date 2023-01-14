package com.bank.DB;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String propFile = "config/config.properties";
    private static String HOST = new String();
    private static String PORT = new String();
    private static String SCHEMA = new String();
    private static String USER = new String();
    private static String PASSWORD = new String();
    private static String URL = new String();
    private Connection conn = null;

    public Connection getConn() {
        return this.conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    public DBConnector() throws IOException {
        configSetup();
    }

    /**
     * config.properties로 부터 값을 받아 저장하는 함수
     * @throws IOException
     */
    public static void configSetup() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(propFile);

        prop.load(new java.io.BufferedInputStream(fis));
        HOST = prop.getProperty("DB_HOST");
        PORT = prop.getProperty("DB_PORT");
        SCHEMA = prop.getProperty("DB_SCHEMA");
        USER = prop.getProperty("DB_USER");
        PASSWORD = prop.getProperty("DB_PASSWORD");
        URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + SCHEMA + "?characterEncoding=utf8";
    }

    /**
     * DB와 연결하는 함수
     * @return DB Connection
     */
    public Connection makeConnection()  {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB Connection Successed.");
        } catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }

        this.setConn(conn);
        return conn;
    }
}