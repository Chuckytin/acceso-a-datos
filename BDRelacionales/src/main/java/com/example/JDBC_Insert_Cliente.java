package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Insert_Cliente {

    public static void main(String[] args) {
        insertarCliente();
    }

    public static void insertarCliente () {

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                    JDBC_Connection.user, JDBC_Connection.pwd);
             Statement statement = connection.createStatement()) {

            int numFilas = statement.executeUpdate(
                    "INSERT INTO Clientes (dni, nombreCompleto, codigoPostal) VALUES" +
                            "('17748595Z', 'Angel Torcal Catalan', '50001')," +
                            "('11598763Z', 'Esther Sarabia Zeranda', '50002')," +
                            "('15569847P', 'Javier García Zaragoza', '50003')");

            System.out.println("Filas insertadas: " + numFilas);

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
