package com.sistemadebanco;

public class Conta {

    private double saldo;

    public Conta() {
        this.saldo = 0;
    }

    public Conta(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void credito(double valor) {
        this.saldo += valor;
    }

    public void debito(double valor) {
        this.saldo -= valor;
    }

}