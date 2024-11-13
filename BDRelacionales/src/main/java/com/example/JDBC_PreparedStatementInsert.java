package com.example;

import java.sql.*;

public class JDBC_PreparedStatementInsert {

    public static void main(String[] args) {
        preparedStatementInsert();
    }

    private static void preparedStatementInsert() {

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd)) {

            Statement statement = connection.createStatement();

            String sqlCreateTable = "CREATE TABLE Clientes1 (" +
                    "dni CHAR(9) NOT NULL PRIMARY KEY," +
                    "apellidos VARCHAR(32) NOT NULL," +
                    "codigoPostal Integer" +
                    ")";

            statement.execute(sqlCreateTable);

            String sqlInsertInto = "INSERT INTO Clientes1 (dni, apellidos, codigoPostal) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertInto);

            preparedStatement.setInt(1, 179648260);
            preparedStatement.setString(2, "Torcal Fernandez");
            preparedStatement.setInt(3, 50005);

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
