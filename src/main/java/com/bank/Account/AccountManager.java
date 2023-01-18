package com.bank.Account;

import com.bank.Console.ConsoleInput;
import com.bank.Console.ConsoleManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    private ConsoleInput console = new ConsoleInput();
    private Connection conn;
    private AccountDTO DTO;

    public AccountManager() {
        super();
    }

    public AccountManager(Connection conn, AccountDTO DTO) {
        this.conn = conn;
        this.DTO = DTO;
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
        stmt.setString(3, DTO.getBackPin());

        stmt.executeUpdate();
        DTO.setAccount(newAccount);
        console.log("계좌 등록이 완료되었습니다!");
        console.log("3초 후 메인화면으로 이동됩니다.");
        ConsoleInput.sleepAfter(conn, 3000, DTO);
    }

    public void delAccount() throws SQLException {
        ConsoleInput.clear();

        console.log("계좌의 비밀번호를 입력해주세요.");
        String password = console.next("> ");

        PreparedStatement stmt;

        String selQuery = "SELECT * FROM `Account` WHERE `BACK_PIN` = ? AND `Password` = ?";

        stmt = conn.prepareStatement(selQuery);
        stmt.setString(1, DTO.getBackPin());
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if(!rs.next()) {
            console.log("입력하신 계좌가 존재하지 않거나, 패스워드가 옳지 않습니다.");
            console.log("3초 후 메인화면으로 이동됩니다.");
            ConsoleInput.sleepAfter(conn, 3000, DTO);
        }

        String delQuery = "UPDATE `Account` SET `Account` = 0, `Password` = 'None', `Balance` = 0 WHERE `BACK_PIN` = ?";
        stmt = conn.prepareStatement(delQuery);
        stmt.setString(1, DTO.getBackPin());
        stmt.executeUpdate();

        DTO.setAccount("0");
        console.log("계좌 제거가 완료되었습니다!");
        console.log("3초 후 메인화면으로 이동됩니다.");
        ConsoleInput.sleepAfter(conn, 3000, DTO);
    }

    public void getMoney(AccountDTO DTO) {
        BankMethods bank = new BankMethods(conn);
        int money = bank.getMoney(DTO);

        console.log(DTO.getUsername() + "님의 잔액은 " + money + "원입니다.");
        console.log("3초 후 메인화면으로 이동됩니다.");
        ConsoleInput.sleepAfter(conn, 3000, DTO);
    }
}
