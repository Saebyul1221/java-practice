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

    public static void clear() {
        for(int i = 0; i < 100; i++) System.out.println("");
    }
}
