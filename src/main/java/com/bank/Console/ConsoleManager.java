package com.bank.Console;

import com.bank.Account.AccountDTO;
import com.bank.Account.AccountTransfer;
import com.bank.DB.DBConnector;
import com.bank.DeveloperOption;
import com.bank.User.UserManager;
import com.bank.Account.AccountManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConsoleManager {
    private static Connection conn = null;
    private static ConsoleInput console = new ConsoleInput();

    public ConsoleManager(Connection conn) {
        this.conn = conn;
    }

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
     * @param DTO AccountDTO 객체
     */
    public void afterLogin(AccountDTO DTO) throws SQLException {
        String username = DTO.getUsername();
        String account = DTO.getAccount();
        console.log("-----------------------------");
        console.log("");
        console.log(username + "님, 환영합니다!");
        if(Integer.parseInt(account) == 0) console.log("아직 등록된 계좌가 없습니다.");
        else console.log("등록 계좌: " + account);
        console.log("");
        console.log("[1] 계좌 등록");
        console.log("[2] 계좌 제거");
        console.log("[3] 이체");
        console.log("[4] 잔고 확인");
        console.log("");
        console.log("-----------------------------");
        String input = ConsoleInput.next("번호 입력 > ");

        AccountManager AccountManager = new AccountManager(conn, DTO);
        AccountTransfer AccountTransfer = new AccountTransfer(conn, account);
        DeveloperOption DeveloperOption = new DeveloperOption(conn);
        switch (input) {
            case "1": AccountManager.addAccount(); break;
            case "2": AccountManager.delAccount(); break;
            case "3": AccountTransfer.transfer(DTO); break;
            case "4": AccountManager.getMoney(DTO); break;
            case "5": DeveloperOption.transfer(DTO); break;
            default: {
                ConsoleInput.clear();
                afterLogin(DTO);
            }
            break;
        }
    }
}
