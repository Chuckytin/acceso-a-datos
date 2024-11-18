package com.example;

import java.sql.*;

public class JDBC_PreparedStatementInsert {

    public static void main(String[] args) {
        preparedStatementInsert();
    }

    private static void preparedStatementInsert() {

        String tableName = "Clientes1";
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Clientes1 (" +
                "dni CHAR(9) NOT NULL PRIMARY KEY," +
                "apellidos VARCHAR(32) NOT NULL," +
                "codigoPostal Integer" +
                ")";

        //conexión a la base de datos
        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd)) {

            //DatabaseMetadata verifica si la tabla existe
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, tableName, new String[]{"TABLE"});

            boolean tableExists = tables.next();

            if (!tableExists) {
                Statement statement = connection.createStatement();
                statement.execute(sqlCreateTable);
                System.out.println("Tabla creada: " + tableName);
            } else {
                System.out.println("Tabla ya existe: " + tableName);
            }

            //Verifica si hay datos en la tabla gracias a la consulta COUNT
            String sqlCheckData = "SELECT COUNT(*) FROM Clientes1";
            PreparedStatement checkStatement = connection.prepareStatement(sqlCheckData);
            ResultSet checkResult = checkStatement.executeQuery();

            boolean hasData = checkResult.next() && checkResult.getInt(1) > 0;

            //si no hay datos se insertan los datos
            if (!hasData) {
                String sqlInsertInto = "INSERT INTO Clientes1 (dni, apellidos, codigoPostal) VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertInto);

                preparedStatement.setInt(1, 179648260);
                preparedStatement.setString(2, "Torcal Fernandez");
                preparedStatement.setInt(3, 50005);

                int rowsInserted = preparedStatement.executeUpdate();
                System.out.println((rowsInserted == 1) ?
                        rowsInserted + " fila insertada." : rowsInserted + " filas insertadas.");

            } else {
                System.out.println("La tabla ya contiene datos.");
            }

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
