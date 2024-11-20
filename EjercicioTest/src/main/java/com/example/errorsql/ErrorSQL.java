package com.example.errorsql;

import java.sql.SQLException;

public class ErrorSQL {

    public static void muestraErrorSQL(SQLException e) {

        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());

    }

}
