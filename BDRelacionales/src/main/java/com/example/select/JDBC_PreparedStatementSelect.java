package com.example.select;

import com.example.connection.JDBC_Connection;

import java.sql.*;
import java.util.Scanner;

public class JDBC_PreparedStatementSelect {

    public static void main(String[] args) {
        preparedStatementSelect();
    }

    private static void preparedStatementSelect() {

        String tableName = "Clientes1";

        String sqlSelect = "SELECT * FROM " + tableName + " WHERE DNI = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd);
                Scanner entrada = new Scanner(System.in)) {

            //DatabaseMetadata verifica si la tabla existe
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSetTabla = databaseMetaData.getTables(null, null, tableName, new String[]{"TABLE"});

            boolean tablaExiste = resultSetTabla.next();

            if (!tablaExiste) {

                System.err.println("La tabla " + tableName + " no existe.");
                return;
            }

            //Prepara la consulta
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);

            System.out.println("Introduce el DNI de búsqueda: ");
            long dni = entrada.nextLong();

            //Establece el parámetro
            preparedStatement.setLong(1, dni);

            //Ejecuta la consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println("Cliente encontrado: ");
                System.out.println("DNI: " + resultSet.getLong("DNI"));
                System.out.println("Apellidos: " + resultSet.getString("apellidos"));
                System.out.println("Código Postal: " + resultSet.getInt("codigoPostal"));

            } else {
                System.out.printf("Cliente con DNI '%d' no encontrado.", dni);
            }

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
