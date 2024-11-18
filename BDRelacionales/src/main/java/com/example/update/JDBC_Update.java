package com.example.update;

import com.example.connection.JDBC_Connection;

import java.sql.*;

public class JDBC_Update {

    public static void main(String[] args) {
        update();
    }

    private static void update() {

        try (Connection connection = DriverManager.getConnection(JDBC_Connection.urlConecciont,
                JDBC_Connection.user, JDBC_Connection.pwd);
                Statement statement = connection.createStatement()) {

            String consulta1 = "UPDATE Clientes SET dni = '78901234X', nombreCompleto = 'NADALES', codigoPostal = '44126' WHERE codigoPostal = '50001'";
            String consulta2 = "UPDATE Clientes SET dni = '89012345E', nombreCompleto = 'ROJAS', codigoPostal = null WHERE codigoPostal = '50002'";
            String consulta3 = "UPDATE Clientes SET dni = '56789012B', nombreCompleto = 'SAMPER', codigoPostal = '29730' WHERE codigoPostal = '50003'";

            int resultado1 = statement.executeUpdate(consulta1);
            int resultado2 = statement.executeUpdate(consulta2);
            int resultado3 = statement.executeUpdate(consulta3);

            System.out.println("Filas actualizadas: " + (resultado1 + resultado2 + resultado3));

        } catch (SQLException e) {
            JDBC_Connection.muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}