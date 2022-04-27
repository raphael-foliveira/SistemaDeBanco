package com.sistemadebanco;

import java.util.ArrayList;

public class Banco {

    static private ArrayList<Usuario> todosUsuarios = new ArrayList<>();;

    static private void adicionarUsuario(Usuario usuario) {
        todosUsuarios.add(usuario);
    }

    static public void removerUsuario(Usuario usuario) {
        todosUsuarios.remove(usuario);
    }

    static public void cadastrarNovoUsuario() {
        System.out.println("Digite os dados do novo usuário a seguir: ");
        String nome = InterfaceUsuario.inputString("Nome: ");
        String login = InterfaceUsuario.inputString("Login: ");
        String senha = InterfaceUsuario.inputString("Senha: ");
        Usuario novoUsuario = new Usuario(nome, login, senha);
        adicionarUsuario(novoUsuario);
    }

    private static Usuario validarUsuario(String loginUsuario, String senhaUsuario) {
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

    public static Usuario encontrarUsuario(String nome) {
        for (Usuario usuario : todosUsuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario fazerLoginUsuario() {
        String login = InterfaceUsuario.inputString("Login: ");
        String senha = InterfaceUsuario.inputString("Senha: ");
        Usuario usuario = validarUsuario(login, senha);
        return usuario;

    }

}