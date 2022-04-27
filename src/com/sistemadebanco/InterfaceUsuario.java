package com.sistemadebanco;

import java.sql.SQLException;

public class InterfaceUsuario {

    Banco banco;

    public InterfaceUsuario() throws SQLException {
        this.banco = new Banco();
    }

    public void menuPrincipal() throws SQLException {
        System.out.println("O que deseja fazer?");
        System.out.println("1) Acessar conta");
        System.out.println("2) Criar conta");
        System.out.println("0) Sair");
        int opcao = InputUsuario.inputInt(">>> ");
        menuPrincipalOpcao(opcao);
    }

    public void menuPrincipalOpcao(int opcao) throws SQLException {
        switch (opcao) {
            case 1:
                Usuario usuarioLogado = banco.fazerLoginUsuario();
                if (!usuarioLogado.equals(null)) {
                    menuUsuario(usuarioLogado);
                }
                break;
            case 2:
                banco.cadastrarNovoUsuario();
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

    public void menuUsuario(Usuario usuario) throws SQLException {
        System.out.println("Escolha uma opção a seguir: ");
        System.out.println("1) Depósito");
        System.out.println("2) Saque");
        System.out.println("3) Transferência");
        System.out.println("4) Ver saldo");
        System.out.println("5) Apagar minha conta");
        System.out.println("0) Log Out");
        int opcao = InputUsuario.inputInt(">>> ");
        menuUsuarioOpcao(opcao, usuario);
    }

    public void menuUsuarioOpcao(int opcao, Usuario usuario) throws SQLException {
        double valor;
        switch (opcao) {
            case 1:
                valor = InputUsuario.inputDouble("Valor do depósito: ");
                usuario.credito(valor);
                banco.getBancoDeDados().atualizarSaldo(usuario.getSaldo(), usuario.getLogin());
                menuUsuario(usuario);
                break;
            case 2:
                valor = InputUsuario.inputDouble("Valor do saque: ");
                usuario.debito(valor);
                banco.getBancoDeDados().atualizarSaldo(usuario.getSaldo(), usuario.getLogin());
                menuUsuario(usuario);
                break;
            case 3:
                String nomeDoUsuarioDestino = InputUsuario.inputString("Nome do usuário para transferência: ");
                valor = InputUsuario.inputDouble("Valor da transferência: ");
                Usuario usuarioDestino = banco.encontrarUsuario(nomeDoUsuarioDestino);
                usuario.transferir(usuarioDestino, valor);
                banco.getBancoDeDados().atualizarSaldo(usuario.getSaldo(), usuario.getLogin());
                banco.getBancoDeDados().atualizarSaldo(usuarioDestino.getSaldo(), usuarioDestino.getLogin());
                menuUsuario(usuario);
                break;
            case 4:
                usuario.printSaldo();
                menuUsuario(usuario);
                break;
            case 5:
                System.out.println("Sentimos muito por sua partida");
                banco.removerUsuario(usuario);
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
