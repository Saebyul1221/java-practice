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
        new DeveloperOption(conn);

        ConsoleManager.init();
    }
}