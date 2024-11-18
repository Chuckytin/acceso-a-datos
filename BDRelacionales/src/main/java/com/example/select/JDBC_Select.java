package com.example.select;

import com.example.connection.JDBC_Connection;

import java.sql.*;

public class JDBC_Select {

    public static void main(String[] args) {
        select();
    }

    private static void select() {

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clientes");

            int i = 1;
            int contadorFilas = 0;
            while (resultSet.next()) {

                System.out.print("[" + (i++) + "]");
                System.out.print(" | DNI: " + resultSet.getString("dni"));
                System.out.print(" | Nombre: " + resultSet.getString("nombreCompleto"));
                System.out.println(" | CP: " + resultSet.getString("codigoPostal"));

                contadorFilas++;

            }

            if (resultSet.last()) { //devuelve el ResulSet al revés

                int numFilas = resultSet.getRow();
                System.out.println("Número de filas: " + numFilas);

                do {

                    System.out.print("[" + (contadorFilas--) + "]");
                    System.out.print(" | DNI: " + resultSet.getString("dni"));
                    System.out.print(" | Nombre: " + resultSet.getString("nombreCompleto"));
                    System.out.println(" | CP: " + resultSet.getString("codigoPostal"));

                } while (resultSet.previous());

            }

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
