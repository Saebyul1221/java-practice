package com.bank.Console;

import com.bank.DB.DBConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleInput {
    /**
     * @param msg 콘솔에 출력할 문자열
     */
    public static void log(String msg) {
        System.out.println(msg);
    }

    /**
     *
     * @param msg 문자열 입출력
     * @return 입력받은 값
     */
    public static String next(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        return sc.next();
    }

    /**
     * 빈 문자열을 여러번 출력하여 콘솔을 지워진 것 처럼 표기함
     */
    public static void clear() {
        for(int i = 0; i < 100; i++) System.out.println("");
    }

    /**
     * n초 대기 후 메인 화면으로 이동
     * @param time sleep를 수행할 시간. (ms)
     */
    public static void sleepMain(int time) {
        try {
            Thread.sleep(time);
            clear();
            ConsoleManager.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * n초 대기 후 로그인 이후 화면으로 이동
     * @param time sleep를 수행할 시간. (ms)
     */
    public static void sleepAfter(int time, String username, String backPin) {
        DBConnector DB = null;
        try { DB = new DBConnector(); }
        catch (IOException e) { e.printStackTrace(); }

        Connection conn = DB.getConn();
        ConsoleManager ConsoleManager = new ConsoleManager(conn);

        try {
            Thread.sleep(time);
            clear();
            ConsoleManager.afterLogin(username, backPin);
        } catch (InterruptedException | SQLException e ) {
            e.printStackTrace();
        }
    }
}
