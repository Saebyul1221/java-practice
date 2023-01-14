package com.bank.Account;

import com.bank.Console.ConsoleInput;
import com.bank.Console.ConsoleManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    ConsoleInput console = new ConsoleInput();
    Connection conn = null;

    private String username;
    private int account;
    private String backPin;

    public AccountManager(Connection conn, String username, int account, String backPin) {
        this.conn = conn;
        this.username = username;
        this.account = account;
        this.backPin = backPin;
    }

    public void addAccount() throws SQLException {
        ConsoleInput.clear();
        console.log("계좌번호를 입력해주세요.");
        String newAccount = console.next("> ");

        console.log("계좌의 비밀번호를 입력해주세요.");
        String password = console.next("> ");

        String upQuery = "UPDATE `Account` SET `Account` = ?, `Password` = ? WHERE `BACK_PIN` = ?";
        PreparedStatement stmt = conn.prepareStatement(upQuery);
        stmt.setString(1, newAccount);
        stmt.setString(2, password);
        stmt.setString(3, backPin);

        stmt.executeUpdate();
        console.log("계좌 등록이 완료되었습니다!");
        console.log("3초 후 메인화면으로 이동됩니다.");

        ConsoleManager ConsoleManager = new ConsoleManager(conn);
        ConsoleManager.setAccount(Integer.parseInt(newAccount));
        ConsoleInput.sleepAfter(3000, username, backPin);
    }

    public void delAccount() throws SQLException {
        ConsoleInput.clear();
        console.log("계좌번호를 입력해주세요.");
        String newAccount = console.next("> ");

        console.log("계좌의 비밀번호를 입력해주세요.");
        String password = console.next("> ");

        PreparedStatement stmt;

        String selQuery = "SELECT * FROM `Account` WHERE `BACK_PIN` = ? AND `password` = ?";
        stmt = conn.prepareStatement(selQuery);
        stmt.setString(1, backPin);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if(!rs.next()) {
            console.log("존재하지 않는 계좌번호거나, 비밀번호가 일치하지 않습니다.");
            console.log("3초 후 메인화면으로 이동됩니다.");
            ConsoleInput.sleepAfter(3000, username, backPin);
        } else {
            String delQuery = "UPDATE `Account` SET `Account` = 0, `Password` = 'None' WHERE `BACK_PIN` = ?";
            stmt = conn.prepareStatement(delQuery);
            stmt.setString(1, backPin);
            stmt.executeUpdate();
        }
        console.log("계좌 제거가 완료되었습니다!");
        console.log("3초 후 메인화면으로 이동됩니다.");
        ConsoleInput.sleepAfter(3000, username, backPin);
    }

}
