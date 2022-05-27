package com.sistemadebanco;

public class InterfaceUsuario {

    private Banco banco;

    public InterfaceUsuario() {
        this.banco = new Banco();
    }

    public void menuPrincipal() {
        System.out.println("O que deseja fazer?");
        System.out.println("1) Acessar conta");
        System.out.println("2) Criar conta");
        System.out.println("0) Sair");

        int opcao = InputUsuario.inputInt(">>> ");

        menuPrincipalOpcao(opcao);
    }

    public void menuPrincipalOpcao(int opcao) {
        if (opcao == 1) {
            Usuario usuarioLogado = banco.fazerLoginUsuario();
            if (!usuarioLogado.equals(null)) {
                menuUsuario(usuarioLogado);
            } else {
                System.out.println("Usuário ou senha inválidos.");
            }
        } else if (opcao == 2) {
            banco.cadastrarNovoUsuario();

        } else if (opcao == 0) {
            System.out.println("Até logo");
            System.exit(0);
        } else {
            System.out.println("Opção inválida");
        }
        menuPrincipal();
    }

    public void menuUsuario(Usuario usuario) {
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

    public void menuUsuarioOpcao(int opcao, Usuario usuario) {
        double valor;
        if (opcao == 1) {
            valor = InputUsuario.inputDouble("Valor do depósito: ");
            banco.creditoUsuario(usuario, valor);
        } else if (opcao == 2) {
            valor = InputUsuario.inputDouble("Valor do saque: ");
            banco.debitoUsuario(usuario, valor);
        } else if (opcao == 3) {
            String nomeDoUsuarioDestino = InputUsuario.inputString("Nome do usuário para transferência: ");
            valor = InputUsuario.inputDouble("Valor da transferência: ");

            Usuario usuarioDestino = banco.encontrarUsuario(nomeDoUsuarioDestino);

            banco.debitoUsuario(usuario, valor);
            banco.creditoUsuario(usuarioDestino, valor);
        } else if (opcao == 4) {
            usuario.printSaldo();
        } else if (opcao == 5) {
            System.out.println("Sentimos muito por sua partida");
            banco.removerUsuario(usuario);
            menuPrincipal();
        } else if (opcao == 0) {
            menuPrincipal();
        } else {
            System.out.println("Comando inválido");
        }
        menuUsuario(usuario);
    }
}
