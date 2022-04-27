package com.sistemadebanco;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        interfaceUsuario.menuPrincipal();
    }

}