package com.bank.Account;

import com.bank.Console.ConsoleInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankMethods {
    private ConsoleInput console = new ConsoleInput();
    private Connection conn = null;

    public BankMethods(Connection conn) {
        this.conn = conn;
    }

    public int getAccount(int targetAcc) {
        String selQuery = "SELECT `Account` FROM `Account` WHERE `Account` = ?";
        int account = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(selQuery);
            stmt.setInt(1, targetAcc);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                account = rs.getInt("Account");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return account;
    }

    public int getMoney(AccountDTO DTO) {
        String selQuery = "SELECT `Balance` FROM `Account` WHERE `Account` = ? AND `BACK_PIN` = ?";
        int money = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(selQuery);
            stmt.setString(1, DTO.getAccount());
            stmt.setString(2, DTO.getBackPin());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                money = rs.getInt("Balance");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return money;
    }

    public boolean delete(AccountDTO DTO, int transferMoney) {
        int myMoney = this.getMoney(DTO);
        if(myMoney < transferMoney) {
            console.log("계좌 내 금액이 부족합니다.");
            console.log("3초 후 메인화면으로 이동됩니다.");
            ConsoleInput.sleepAfter(conn, 3000, DTO);
            return false;
        }

        String upQuery = "UPDATE `Account` SET `Balance` = `Balance` - ? WHERE `Account` = ? AND `BACK_PIN` = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(upQuery);
            stmt.setInt(1, transferMoney);
            stmt.setString(2, DTO.getAccount());
            stmt.setString(3, DTO.getBackPin());
            stmt.executeUpdate();
            return true;
        } catch (SQLException err) {
            err.printStackTrace();
            return false;
        }
    }

    public boolean transfer(int targetAcc, int transferMoney) {
        String upQuery = "UPDATE `Account` SET `Balance` = `Balance` + ? WHERE `Account` = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(upQuery);
            stmt.setInt(1, transferMoney);
            stmt.setInt(2, targetAcc);
            stmt.executeUpdate();
            return true;
        } catch (SQLException err) {
            err.printStackTrace();
            return false;
        }
    }

}
