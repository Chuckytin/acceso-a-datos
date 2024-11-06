package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection {

    //main de base de datos en la nube temporal
    public static void main(String[] args) {

        String baseDeDatos = "sql7743105";
        String host = "sql7.freemysqlhosting.net";
        String port = "3306";
        String parAdic = "";
        String urlConecciont = "jdbc:mysql://" + host + ":" + port + "/" + baseDeDatos + parAdic;
        String user = "sql7743105";
        String pwd = "jvITGN6mSA";

        try (Connection connection = DriverManager.getConnection(urlConecciont, user, pwd)) {

            System.out.println("Conexión realizada!");

        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

}
