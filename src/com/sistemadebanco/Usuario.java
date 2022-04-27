package com.sistemadebanco;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private Conta conta;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.conta = new Conta();
    }

    public void credito(double valor) {
        conta.credito(valor);
    }

    public void debito(double valor) {
        conta.debito(valor);
    }

    public void printSaldo() {
        System.out.println("Saldo: ");
        System.out.printf("R$ %.2f\n", conta.getSaldo());
    }

    public void transferir(Usuario usuarioDestino, double valor) {
        this.debito(valor);
        usuarioDestino.credito(valor);
    }

    public boolean checarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public String getLogin() {
        return this.login;
    }

    public String getNome() {
        return this.nome;
    }
}