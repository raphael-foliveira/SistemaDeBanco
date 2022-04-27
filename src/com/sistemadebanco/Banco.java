package com.sistemadebanco;

import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {

    private ControladorDB bancoDeDados;

    public Banco() throws SQLException {
        bancoDeDados = new ControladorDB();
    }

    public void cadastrarNovoUsuario() throws SQLException {
        System.out.println("Digite os dados do novo usuário a seguir: ");
        String nome = InputUsuario.inputString("Nome: ");
        String login = InputUsuario.inputString("Login: ");
        String senha = InputUsuario.inputString("Senha: ");
        bancoDeDados.guardarUsuario(nome, login, senha);

    }

    private Usuario validarUsuario(String loginUsuario, String senhaUsuario) throws SQLException {
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

    public Usuario encontrarUsuario(String nome) throws SQLException {
        ArrayList<Usuario> todosUsuarios = bancoDeDados.retornarTodosUsuarios();
        for (Usuario usuario : todosUsuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public void removerUsuario(Usuario usuario) throws SQLException {
        bancoDeDados.apagarUsuario(usuario);
    }

    public Usuario fazerLoginUsuario() throws SQLException {
        String login = InputUsuario.inputString("Login: ");
        String senha = InputUsuario.inputString("Senha: ");
        Usuario usuario = validarUsuario(login, senha);
        return usuario;
    }

    public ControladorDB getBancoDeDados() {
        return bancoDeDados;
    }

}