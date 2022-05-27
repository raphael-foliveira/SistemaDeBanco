package com.sistemadebanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class ControladorDB {

    Connection connection;

    public ControladorDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/bancodb";
            String user = "admin";
            String password = "";
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarUsuario(String nome, String login, String senha) {
        try {
            String comando = "INSERT INTO usuarios (nome, login, senha, saldo) VALUES (?, ?, ?, ?)";
            PreparedStatement declaracao = connection.prepareStatement(comando);
            declaracao.setString(1, nome);
            declaracao.setString(2, login);
            declaracao.setString(3, senha);
            declaracao.setDouble(4, 0);
            declaracao.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarSaldo(double saldo, String login) {
        try {
            String comando = "UPDATE usuarios SET saldo=? WHERE login=?";
            PreparedStatement declaracao = connection.prepareStatement(comando);
            declaracao.setDouble(1, saldo);
            declaracao.setString(2, login);
            declaracao.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void apagarUsuario(Usuario usuario) {
        try {
            String comando = "DELETE FROM usuarios WHERE login=?";
    
            PreparedStatement declaracao = connection.prepareStatement(comando);
            declaracao.setString(1, usuario.getLogin());
            declaracao.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Usuario> retornarTodosUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            String comando = "SELECT * FROM usuarios";
            Statement declaracao = connection.createStatement();
            ResultSet resultado = declaracao.executeQuery(comando);
            while (resultado.next()) {
                String nome = resultado.getString("nome");
                String login = resultado.getString("login");
                String senha = resultado.getString("senha");
                double saldo = resultado.getDouble("saldo");
                usuarios.add(new Usuario(nome, login, senha, saldo));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return usuarios;
    }
}
