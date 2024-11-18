package com.example.transaction;

import com.example.connection.JDBC_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC_Transaction {

    public static void main(String[] args) {
        transaction();
    }

    public static void transaction() {

        String tableName = "Clientes1";
        String sqlInsert = "INSERT INTO " + tableName + " (dni, apellidos, codigoPostal) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd)) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

                connection.setAutoCommit(false); //deshabilita el autocommit

                //insercción de datos
                int i = 0;
                preparedStatement.setLong(++i, 166655590);
                preparedStatement.setString(++i, "Befredoro Italiono");
                preparedStatement.setInt(++i, 22556);

                //ejecución de la consulta
                preparedStatement.executeUpdate();

                //throw new SQLException("Forzando error de transacción para comprobar rollback");

            } catch (SQLException e) {

                JDBC_Connection.muestraErrorSQL(e);
                try {
                    //intento de rollback en caso de error
                    connection.rollback();
                    System.err.println("Se hace ROLLBACK.");
                } catch (SQLException err) {
                    System.err.println("ERROR en el ROLLBACK.");
                    JDBC_Connection.muestraErrorSQL(err);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR de conexión.");
            e.printStackTrace(System.err);
        }
    }
}