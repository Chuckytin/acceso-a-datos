package com.example.consultas;

import com.example.conexion.Conexion;
import com.example.errorsql.ErrorSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Consultas {

    static Scanner entrada = new Scanner(System.in);

    public static void crearMascotas() throws SQLException {

        String sqlInsert = "INSERT INTO Mascotas (nombreMascota, propietarioMascota, tipoMascota) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = Conexion.inicializarConexion().prepareStatement(sqlInsert)) {

            System.out.println("Indica el nombre de su mascota:");
            String nombreMascota = entrada.nextLine();
            System.out.println("Indica el nombre del propietario/a: ");
            String nombrePropietario = entrada.nextLine();
            System.out.println("Indica el tipo de animal: ");
            String tipoAnimal = entrada.nextLine();

            preparedStatement.setString(1, nombreMascota);
            preparedStatement.setString(2, nombrePropietario);
            preparedStatement.setString(3, tipoAnimal);

            int resultado = preparedStatement.executeUpdate();
            System.out.println((resultado == 1) ?
                    resultado + " fila insertada." : resultado + " filas insertadas.");

        }

    }

    public static void verMascotas() {

        String sqlSelect = "SELECT * FROM Mascotas";

        try (PreparedStatement preparedStatement = Conexion.inicializarConexion().prepareStatement(sqlSelect)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("----------------------------------------");

            while(resultSet.next()) {

                System.out.println("Mascota: " + resultSet.getString("nombreMascota"));
                System.out.println("Propietario: " + resultSet.getString("propietarioMascota"));
                System.out.println("Tipo de mascota: " + resultSet.getString("tipoMascota"));
                System.out.println("----------------------------------------");
            }

        } catch (SQLException e) {
            ErrorSQL.muestraErrorSQL(e);
        }

    }

    public static void anadirMascota() {

    }

    public static void borrarMascota() {

        int id = 0;

        do {
            System.out.println("¿Qué mascota quieres borrar? (introduce el ID de la mascota):");
            id = entrada.nextInt();
            entrada.nextLine();

            String sqlDelete = "DELETE FROM Mascotas WHERE idMascota = " + id;

            try (PreparedStatement preparedStatement = Conexion.inicializarConexion().prepareStatement("sqlDelete")) {

                System.out.println("Mascota " + id + " borrada con éxito.");

            } catch (SQLException e) {
                ErrorSQL.muestraErrorSQL(e);
            }


        } while (id == 0);

    }

}
