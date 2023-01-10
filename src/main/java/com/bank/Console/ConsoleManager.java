package com.bank.Console;

import com.bank.User.UserManager;

import java.sql.Connection;
import java.sql.SQLException;

public class ConsoleManager {
    static Connection conn = null;
    static ConsoleInput console = new ConsoleInput();

    public ConsoleManager(Connection conn) {
        this.conn = conn;
    }

    public static void init() throws SQLException {
        console.log("-----------------------------");
        console.log("");
        console.log("환영합니다! 회원가입 또는 로그인을 해주세요.");
        console.log("");
        console.log("[1] 회원가입");
        console.log("[2] 로그인");
        console.log("");
        console.log("-----------------------------");
        String input = ConsoleInput.next("번호 입력 > ");

        UserManager UserManager = new UserManager(conn);
        switch (input) {
            case "1": UserManager.Register(); break;
            case "2": UserManager.Login(); break;
            default: console.log("WIP"); break;
        }
    }

    public static void afterLogin(String username, int account) {
        console.log("-----------------------------");
        console.log("");
        console.log(username + "님, 환영합니다!");
        if(account == 0) console.log("아직 등록된 계좌가 없습니다.");
        else console.log("등록 계좌: " + account);
        console.log("");
        console.log("[1] 계좌 등록");
        console.log("[2] 계좌 제거");
        console.log("[3] 이체");
        console.log("[4] 잔고 확인");
        console.log("");
        console.log("-----------------------------");
        String input = ConsoleInput.next("번호 입력 > ");

        switch (input) {
            case "1": console.log("WIP"); break;
            case "2": console.log("WIP"); break;
            case "3": console.log("WIP"); break;
            case "4": console.log("WIP"); break;
            default: console.log("WIP"); break;
        }
    }
}
