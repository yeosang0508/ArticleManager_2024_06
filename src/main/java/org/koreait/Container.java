package org.koreait;

import java.util.Scanner;

public class Container {
    private static Scanner scanner;

    public static void init (){
        scanner = new Scanner (System.in);
    }

    public static void close(){
        scanner.close();
    }

    public static Scanner getScanner(){
        return scanner;
    }
}
