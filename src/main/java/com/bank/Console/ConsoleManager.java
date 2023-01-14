package com.bank.Console;

import com.bank.User.UserManager;
import com.bank.Account.AccountManager;

import java.sql.Connection;
import java.sql.SQLException;

public class ConsoleManager {
    static Connection conn = null;
    static ConsoleInput console = new ConsoleInput();
    private int account;

    public ConsoleManager(Connection conn) {
        this.conn = conn;
    }

    public void setAccount(int account) { this.account = account; }
    /**
     * 회원가입 및 로그인 화면 출력 함수
     * @throws SQLException
     */
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
            default: {
                ConsoleInput.clear();
                init();
            }
            break;
        }
    }

    /**
     * 로그인 이후의 화면을 띄우는 함수
     * @param username 사용자의 이름
     * @param backPin  (조회 목적) 사용자의 뒷자리 주민등록번호
     */
    public void afterLogin(String username, String backPin) throws SQLException {
        console.log("-----------------------------");
        console.log("");
        console.log(username + "님, 환영합니다!");
        if(this.account == 0) console.log("아직 등록된 계좌가 없습니다.");
        else console.log("등록 계좌: " + account);
        console.log("");
        console.log("[1] 계좌 등록");
        console.log("[2] 계좌 제거");
        console.log("[3] 이체");
        console.log("[4] 잔고 확인");
        console.log("");
        console.log("-----------------------------");
        String input = ConsoleInput.next("번호 입력 > ");

        AccountManager AccountManager = new AccountManager(conn, username, account, backPin);
        switch (input) {
            case "1": AccountManager.addAccount(); break;
            case "2": AccountManager.delAccount(); break;
            case "3": console.log("WIP"); break;
            case "4": console.log("WIP"); break;
            default: console.log("WIP"); break;
        }
    }
}
