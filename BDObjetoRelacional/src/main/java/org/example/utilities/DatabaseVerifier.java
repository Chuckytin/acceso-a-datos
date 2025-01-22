package org.example.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseVerifier {

    public static boolean verifyDatabaseExists(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (SQLException e) {
            System.err.println("Error al verificar la base de datos: " + e.getMessage());
            return false;
        }
    }

}
