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

    public ControladorDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bancodb";
        String user = "admin";
        String password = "630198-Rf";
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void guardarUsuario(String nome, String login, String senha) throws SQLException {
        String comando = "INSERT INTO clientes (nome, login, senha, saldo) VALUES (?, ?, ?, ?)";
        PreparedStatement declaracao = connection.prepareStatement(comando);
        declaracao.setString(1, nome);
        declaracao.setString(2, login);
        declaracao.setString(3, senha);
        declaracao.setDouble(4, 0);
        declaracao.executeUpdate();
    }

    public void atualizarSaldo(double saldo, String login) throws SQLException {
        String comando = "UPDATE clientes SET saldo=? WHERE login=?";
        PreparedStatement declaracao = connection.prepareStatement(comando);
        declaracao.setDouble(1, saldo);
        declaracao.setString(2, login);
        declaracao.executeUpdate();
    }

    public void apagarUsuario(Usuario usuario) throws SQLException {
        String comando = "DELETE FROM clientes WHERE login=?";

        PreparedStatement declaracao = connection.prepareStatement(comando);
        declaracao.setString(1, usuario.getLogin());
        declaracao.executeUpdate();
    }

    public ArrayList<Usuario> retornarTodosUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String comando = "SELECT * FROM clientes";
        Statement declaracao = connection.createStatement();
        ResultSet resultado = declaracao.executeQuery(comando);

        while (resultado.next()) {
            String nome = resultado.getString("nome");
            String login = resultado.getString("login");
            String senha = resultado.getString("senha");
            double saldo = resultado.getDouble("saldo");
            usuarios.add(new Usuario(nome, login, senha, saldo));
        }
        return usuarios;
    }
}
