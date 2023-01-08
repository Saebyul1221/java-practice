package com.bank.Console;

import java.util.Scanner;

public class ConsoleInput {
    public static void log(String msg) {
        System.out.println(msg);
    }

    public static String next(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        return sc.next();
    }
}
