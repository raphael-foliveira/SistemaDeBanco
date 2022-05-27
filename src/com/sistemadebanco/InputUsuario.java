package com.sistemadebanco;

import java.util.Scanner;

public class InputUsuario {
    private static Scanner scanner;

    public static String inputString(String mensagem) {
        scanner = new Scanner(System.in);

        System.out.print(mensagem);
        String userString = scanner.nextLine();
        return userString;
    }

    public static int inputInt(String mensagem) {
        scanner = new Scanner(System.in);

        System.out.print(mensagem);
        int userInt = scanner.nextInt();
        return userInt;
    }

    public static double inputDouble(String mensagem) {
        scanner = new Scanner(System.in);

        System.out.print(mensagem);
        double userDouble = scanner.nextDouble();
        return userDouble;
    }
}
