package com.bank.Account;

import com.bank.Console.ConsoleInput;

import java.sql.Connection;

public class AccountTransfer {
    private ConsoleInput console = new ConsoleInput();
    private Connection conn = null;
    private String account;


    public AccountTransfer(Connection conn, String account) {
        this.conn = conn;
        this.account = account;
    }

    public void transfer(AccountDTO DTO) {
        console.log("입금할 계좌를 입력해주세요.");
        String targetAcc = console.next("> ");

        console.log("입금할 금액을 입력해주세요.");
        String transferMoney = console.next("> ");

        BankMethods acc = new BankMethods(conn);
        int account = acc.getAccount(Integer.parseInt(targetAcc));
        if(account == 0) {
            console.log("존재하지 않는 계좌입니다.");
            console.log("3초 후 메인화면으로 이동됩니다.");
            ConsoleInput.sleepAfter(conn, 3000, DTO);
        }

        acc.delete(DTO, Integer.parseInt(transferMoney));
        acc.transfer(Integer.parseInt(targetAcc), Integer.parseInt(transferMoney));

        console.log(targetAcc + " 계좌에 " + transferMoney + "원이 입금되었습니다.");
        console.log("3초 후 메인화면으로 이동됩니다.");
        ConsoleInput.sleepAfter(conn, 3000, DTO);
    }
}
