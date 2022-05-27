package com.sistemadebanco;

import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {

    private ControladorDB bancoDeDados;

    public Banco() {
        bancoDeDados = new ControladorDB();
    }

    public void cadastrarNovoUsuario() {
        System.out.println("Digite os dados do novo usuário a seguir: ");
        String nome = InputUsuario.inputString("Nome: ");
        String login = InputUsuario.inputString("Login: ");
        String senha = InputUsuario.inputString("Senha: ");
        bancoDeDados.guardarUsuario(nome, login, senha);

    }

    private Usuario validarUsuario(String loginUsuario, String senhaUsuario) {
        ArrayList<Usuario> todosUsuarios = bancoDeDados.retornarTodosUsuarios();
        for (Usuario usuario : todosUsuarios) {
            if (usuario.getLogin().equals(loginUsuario)) {
                if (usuario.checarSenha(senhaUsuario)) {
                    System.out.println("Login efetuado com sucesso");
                    return usuario;
                }
            }
        }
        System.out.println("Dados inválidos");
        return null;
    }

    public Usuario encontrarUsuario(String nome) {
        ArrayList<Usuario> todosUsuarios = bancoDeDados.retornarTodosUsuarios();
        for (Usuario usuario : todosUsuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public void removerUsuario(Usuario usuario) {
        bancoDeDados.apagarUsuario(usuario);
    }

    public Usuario fazerLoginUsuario() {
        String login = InputUsuario.inputString("Login: ");
        String senha = InputUsuario.inputString("Senha: ");
        Usuario usuario = validarUsuario(login, senha);
        return usuario;
    }

    public ControladorDB getBancoDeDados() {
        return bancoDeDados;
    }

    public void debitoUsuario(Usuario usuario, double valor) {
        usuario.debito(valor);
        bancoDeDados.atualizarSaldo(usuario.getSaldo(), usuario.getLogin());
    }

    public void creditoUsuario(Usuario usuario, double valor) {
        usuario.credito(valor);
        bancoDeDados.atualizarSaldo(usuario.getSaldo(), usuario.getLogin());
    }

}