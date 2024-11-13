package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Create_Table {

    public static void main(String[] args) {
        createTable();
    }

    private static void createTable() {

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd)) {

            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE Clientes (" +
                    "dni varchar(9) PRIMARY KEY NOT NULL," +
                    "nombreCompleto varchar(50) NOT NULL," +
                    "codigoPostal char(5)" +
                    ")";

            boolean resultado = statement.execute(sql);
            System.out.println("Tabla " + (resultado ? "creada con éxito!" : "no creada!"));

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }
}
