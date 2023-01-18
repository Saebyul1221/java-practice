package com.bank.User;

import java.sql.*;

import com.bank.Account.AccountDTO;
import com.bank.Console.ConsoleInput;
import com.bank.Console.ConsoleManager;

public class UserManager {
    private ConsoleInput console = new ConsoleInput();
    private Connection conn = null;

    public UserManager(Connection conn) {
        this.conn = conn;
    }

    public void Register() throws SQLException {
        ConsoleInput.clear();
        console.log("회원가입을 위해 본인의 성함을 입력해주세요.");
        String name = console.next("> ");
        
        console.log("주민등록번호 앞자리를 입력해주세요.");
        String frontPin = console.next("> ");

        console.log("주민등록번호 뒷자리를 입력해주세요.");
        String backPin = console.next("> ");

        String dupQuery = "SELECT * FROM `User` WHERE `BACK_PIN` = ?";
        PreparedStatement stmt;

        stmt = conn.prepareStatement(dupQuery);
        stmt.setString(1, backPin);

        ResultSet rs = stmt.executeQuery();

        if(rs.next()) {
            console.log("이미 가입된 명의가 있습니다.");
            this.Register();
        } else {
            String insQuery = "INSERT INTO `User`(`Name`, `FRONT_PIN`, `BACK_PIN`) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(insQuery);
            stmt.setString(1, name);
            stmt.setString(2, frontPin);
            stmt.setString(3, backPin);
            stmt.executeUpdate();

            String accInsQuery = "INSERT INTO `Account`(`BACK_PIN`) VALUES(?)";
            stmt = conn.prepareStatement(accInsQuery);
            stmt.setString(1, backPin);
            stmt.executeUpdate();

            console.log("가입이 완료되었습니다! 다시 로그인해주세요.");
            console.log("3초 후 메인화면으로 이동됩니다.");
            ConsoleInput.sleepMain(3000);

            stmt.close();
        }
    }

    public void Login() throws SQLException {
        console.log("본인의 주민등록번호 뒷자리를 입력해주세요.");
        String backPin = console.next("> ");

        String dupQuery = "SELECT U.Idx, U.Name, A.Account, U.Date FROM `User` AS U INNER JOIN `Account` AS A ON U.BACK_PIN = ? AND A.BACK_PIN = ?";
        PreparedStatement stmt;

        stmt = conn.prepareStatement(dupQuery);
        stmt.setString(1, backPin);
        stmt.setString(2, backPin);

        ResultSet rs = stmt.executeQuery();
        if(!rs.next()) {
            console.log("해당 주민등록번호로 가입된 명의가 존재하지 않습니다.");
            console.log("3초 후 메인화면으로 이동됩니다.");
            ConsoleInput.sleepMain(3000);
        } else {
            String Name = rs.getString("Name");
            String Account = rs.getString("Account");
            ConsoleInput.clear();

            ConsoleManager ConsoleManager = new ConsoleManager(conn);
            AccountDTO DTO = new AccountDTO();
            DTO.setUsername(Name);
            DTO.setBackPin(backPin);
            DTO.setAccount(Account);
            ConsoleManager.afterLogin(DTO);
        }
    }
}
