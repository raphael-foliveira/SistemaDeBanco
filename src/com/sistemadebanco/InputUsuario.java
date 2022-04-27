package com.sistemadebanco;

import java.util.Scanner;

public class InputUsuario {
    public static String inputString(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensagem);
        String userString = scanner.nextLine();
        return userString;
    }

    public static int inputInt(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensagem);
        int userInt = scanner.nextInt();
        return userInt;
    }

    public static double inputDouble(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensagem);
        double userDouble = scanner.nextDouble();
        return userDouble;
    }
}
