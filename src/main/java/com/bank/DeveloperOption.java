package com.bank;

import com.bank.Account.AccountDTO;
import com.bank.Console.ConsoleInput;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DeveloperOption {
    private static ConsoleInput console = new ConsoleInput();
    private static final String propFile = "config/config.properties";
    private Connection conn = null;
    private String password = null;

    public DeveloperOption(Connection conn) {
        this.conn = conn;
        configSetup();
    }

    public void configSetup() {
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(propFile);

            prop.load(new java.io.BufferedInputStream(fis));
            password = prop.getProperty("DEV_PASSWORD");
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void transfer(AccountDTO DTO) {
        console.log("입금 할 계좌를 입력해주세요.");
        String account = console.next("> ");

        console.log("입금 할 금액을 입력해주세요.");
        String money = console.next("> ");

        PreparedStatement stmt;
        String insQuery = "UPDATE `Account` SET `Balance` = `Balance` + ? WHERE `Account` = ?";

        try {
            stmt = conn.prepareStatement(insQuery);
            stmt.setInt(1, Integer.parseInt(money));
            stmt.setString(2, account);
            stmt.executeUpdate();

            console.log(account + " 계좌에 " + money + "원이 입금되었습니다.");
            console.log("3초 후 메인화면으로 이동됩니다.");
            ConsoleInput.sleepAfter(conn, 3000, DTO);
            stmt.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }

    }
}
