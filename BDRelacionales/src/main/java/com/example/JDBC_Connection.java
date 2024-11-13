package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Connection {

    public static String baseDeDatos = "sql7744626";
    public static String host = "sql7.freemysqlhosting.net";
    public static String port = "3306";
    public static String parAdic = "";
    public static String urlConecciont = "jdbc:mysql://" + host + ":" + port + "/" + baseDeDatos + parAdic;
    public static String user = "sql7744626";
    public static String pwd = "axcWNzMuCb";

    //main de base de datos en la nube temporal
    public static void main(String[] args) {

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
