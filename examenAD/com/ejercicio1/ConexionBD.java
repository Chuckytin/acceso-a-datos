package com.ejercicio1;

import java.sql.*;

//Programa que se conecta a la base de datos sql7743091
//Hace consulta sobre la tabla examen y devuelve el nº de mi nombre
public class ConexionBD {

    //datos para la conexión
    public static String baseDeDatos = "sql7743091";
    public static String host = "sql7.freemysqlhosting.net";
    public static String port = "3306";
    public static String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + baseDeDatos;
    public static String user="sql7743091";
    public static String pwd="7bpBb7gnfE";

    //para comprobar el nombre de las BBDD
    //public static String sqlSHOW = "SHOW TABLES";

    //Para comprobar el nombre de las columnas
    //public static String sqlDescribe = "DESCRIBE EXAMEN";

    //consulta
    public static String sqlSelect = "SELECT * FROM EXAMEN";

    //main
    public static void main(String[] args) {

        try (
                Connection connection = DriverManager.getConnection(urlConnection, user, pwd);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect)) {

            System.out.println("Conexión realizada con éxito a la BD: " + baseDeDatos);

            //procesa el resultado
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo"); //el nombre de la columna según tu esquema.
                String nombre = resultSet.getString(1);

                if ("Angel".equals(nombre)) {
                    System.out.println("Soy Angel y mi código es el " + codigo);
                }

            }

        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e2) {
            e2.printStackTrace(System.err);
        }

    }

    //método personalizado para mostrar el mensaje de error
    private static void muestraErrorSQL (SQLException e) {

        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL estado: " + e.getSQLState());
        System.err.println("SQL codigo especifico: " + e.getErrorCode());
    }

}
