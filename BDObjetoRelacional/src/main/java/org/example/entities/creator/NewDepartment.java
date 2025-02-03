package org.example.entities.creator;

import org.example.conexion.Data;
import org.hibernate.annotations.processing.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewDepartment {

    String sqlInsert = "INSERT INTO departments (deptName) VALUES (?)";

    public NewDepartment (String deptName) {

        try (Connection connection = DriverManager.getConnection(Data.url, Data.username, Data.password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

            preparedStatement.setString(1, deptName);
            preparedStatement.executeUpdate();
            System.out.println("Departamento " + deptName + " creado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear el departamento: " + e.getMessage());
        }

    }

}
