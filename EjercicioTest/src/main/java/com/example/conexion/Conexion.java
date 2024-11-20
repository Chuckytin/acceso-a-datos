package com.example.conexion;

import com.example.errorsql.ErrorSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    /**
     * Método para iniciar la conexión a la base de datos 'sql7744626'
     */
    public static Connection inicializarConexion() {

        Connection connection = null;

        //Variables para la conexión
        String baseDeDatos = "sql7744626";
        String host = "sql7.freemysqlhosting.net";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + baseDeDatos;
        String usuario = "sql7744626";
        String password = "axcWNzMuCb";

        try {

            connection = DriverManager.getConnection(urlConnection, usuario, password);

            System.out.println("Conexión realizada con éxito!");

        } catch (SQLException e) {
            ErrorSQL.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return connection;

    }
}