package com.example.main;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.conexion.Conexion;
import com.example.consultas.Consultas;

public class Main {

    static Scanner entrada = new Scanner(System.in);

    //main
    public static void main(String[] args) throws SQLException {

        //Consultas.crearMascotas();

        Consultas.borrarMascota();
        Consultas.verMascotas();

    }

}
