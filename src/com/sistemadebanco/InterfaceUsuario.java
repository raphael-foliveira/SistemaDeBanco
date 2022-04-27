package com.sistemadebanco;

import java.util.Scanner;

public class InterfaceUsuario {

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

    public static void menuPrincipal() {
        System.out.println("O que deseja fazer?");
        System.out.println("1) Acessar conta");
        System.out.println("2) Criar conta");
        System.out.println("0) Sair");
        int opcao = InterfaceUsuario.inputInt(">>> ");
        menuPrincipalOpcao(opcao);
    }

    public static void menuPrincipalOpcao(int opcao) {
        switch (opcao) {
            case 1:
                Usuario usuarioLogado = Banco.fazerLoginUsuario();
                if (!usuarioLogado.equals(null)) {
                    menuUsuario(usuarioLogado);
                }
                break;
            case 2:
                Banco.cadastrarNovoUsuario();
                menuPrincipal();
                break;
            case 0:
                System.out.println("Até logo");
                break;
            default:
                System.out.println("Opção inválida");
                menuPrincipal();
                break;
        }
    }

    public static void menuUsuario(Usuario usuario) {
        System.out.println("Escolha uma opção a seguir: ");
        System.out.println("1) Depósito");
        System.out.println("2) Saque");
        System.out.println("3) Transferência");
        System.out.println("4) Ver saldo");
        System.out.println("5) Apagar minha conta");
        System.out.println("0) Log Out");
        int opcao = InterfaceUsuario.inputInt(">>> ");
        menuUsuarioOpcao(opcao, usuario);
    }

    public static void menuUsuarioOpcao(int opcao, Usuario usuario) {
        double valor;
        switch (opcao) {
            case 1:
                valor = InterfaceUsuario.inputDouble("Valor do depósito: ");
                usuario.credito(valor);
                menuUsuario(usuario);
                break;
            case 2:
                valor = InterfaceUsuario.inputDouble("Valor do saque: ");
                usuario.debito(valor);
                menuUsuario(usuario);
                break;
            case 3:
                String nomeDoUsuarioDestino = InterfaceUsuario.inputString("Nome do usuário para transferência: ");
                valor = InterfaceUsuario.inputDouble("Valor da transferência: ");
                Usuario usuarioDestino = Banco.encontrarUsuario(nomeDoUsuarioDestino);
                usuario.transferir(usuarioDestino, valor);
                menuUsuario(usuario);
                break;
            case 4:
                usuario.printSaldo();
                menuUsuario(usuario);
                break;
            case 5:
                System.out.println("Sentimos muito por sua partida");
                Banco.removerUsuario(usuario);
                menuPrincipal();
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                System.out.println("Comando inválido");
                menuUsuario(usuario);
                break;
        }
    }

}
