package org.example.entities.creator;

import org.example.conexion.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewEmployee {

    String sql = "INSERT INTO employees (empName, deptId) VALUES (?, ?)";

    public NewEmployee (String empName, int deptId) {

        try (Connection connection = DriverManager.getConnection(Data.url, Data.username, Data.password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, empName);
            statement.setInt(2, deptId);

            statement.executeUpdate();

            System.out.println("Empleado " + empName + " creado exitosamente en el departamento " + deptId + ".");

        } catch (SQLException e) {
            System.err.println("Error al crear el empleado: " + e.getMessage());
        }

    }

}
