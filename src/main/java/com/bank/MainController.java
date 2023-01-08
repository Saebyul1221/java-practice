package com.bank;

import com.bank.Console.ConsoleManager;
import com.bank.DB.DBConnector;

import java.io.IOException;
import java.sql.Connection;

public class MainController {
    public static void main(String args[]) throws IOException {
        Connection conn = new DBConnector().makeConnection();
        new ConsoleManager(conn);

        ConsoleManager.init();
    }
}