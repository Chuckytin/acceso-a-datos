package com.example;

import java.sql.*;

public class JDBC_Select {

    public static void main(String[] args) {
        select();
    }

    private static void select() {

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Clientes")) {

            int i = 1;
            while (resultSet.next()) {

                System.out.print("[" + (i++) + "]");
                System.out.print(" | DNI: " + resultSet.getString("dni"));
                System.out.print(" | Nombre: " + resultSet.getString("nombreCompleto"));
                System.out.println(" | CP: " + resultSet.getString("codigoPostal"));

            }

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
