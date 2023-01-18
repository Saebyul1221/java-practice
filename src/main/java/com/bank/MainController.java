/**
 * @개선사항
 *
 * 1. 객체 파라미터로 넘겨주는 정보(BACK_PIN, username 등)을 DTO로 데이터를 주고 받도록 수정.
 * 2. 어노테이션 적극 활용하기
 * 3. 여러 디자인 패턴 활용하기
 */
package com.bank;

import com.bank.Console.ConsoleManager;
import com.bank.DB.DBConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainController {
    public static void main(String args[]) throws IOException, SQLException {
        Connection conn = new DBConnector().makeConnection();
        new ConsoleManager(conn);

        ConsoleManager.init();
    }
}