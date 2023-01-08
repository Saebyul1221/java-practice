package com.bank.User;

import java.sql.Connection;

import com.bank.Console.ConsoleInput;

public class UserManager {
    ConsoleInput console = new ConsoleInput();
    Connection conn = null;

    public UserManager(Connection conn) {
        this.conn = conn;
    }

    public void Register() {
        console.log("회원가입을 위해 본인의 성함을 입력해주세요.");
        String name = console.next("> ");
        
        console.log("주민등록번호 앞자리를 입력해주세요.");
        String frontPin = console.next("> ");

        console.log("주민등록번호 뒷자리를 입력해주세요.");
        String backPin = console.next("> ");

        /**
         * @todo 유저 중복 확인 및 DB에 INSERT
         */
    }
}
