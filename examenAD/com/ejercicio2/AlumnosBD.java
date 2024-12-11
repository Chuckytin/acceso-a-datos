package com.ejercicio2;

import java.sql.*;
import java.util.Scanner;

public class AlumnosBD {

    static Scanner entrada = new Scanner(System.in);
    static Connection connection;
    static DatabaseMetaData databaseMetaData;

    //main
    public static void main(String[] args) {

        try {

            int opcion;

            inicializarConexion("");

            if (!existeBDAlumnos()) {

                crearBDAlumnos();

            }

            inicializarConexion(Datos.baseDeDatos); //Conexión a la BD específica
            crearTablaAlumnos();
            crearTablaAsignaturas();
            crearTablaMatriculas();

            do {

                System.out.println("\nMenú Principal:");
                System.out.println("1.- Menú Alumnos");
                System.out.println("2.- Menú Asignaturas");
                System.out.println("3.- Ver Matrículas");
                System.out.println("0.- Salir");
                System.out.println("Elige una opción:");
                opcion = entrada.nextInt();
                entrada.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> menuAlumnos();
                    case 2 -> menuAsignaturas();
                    case 3 -> verMatriculas();
                    case 0 -> System.out.println("Hasta luego!");
                    default -> System.out.println("Opción no válida!");
                }
            } while (opcion != 0);

        } finally {
            // Cerrar la conexión
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Conexión cerrada correctamente.");
                }
            } catch (SQLException e) {
                muestraErrorSQL(e);
            }
        }

    }

    //Método para ver todas las matrículas
    private static void verMatriculas() {

        String sql = """
                SELECT ALUMNOS.id, ALUMNOS.nombre, ALUMNOS.apellidos, ASIGNATURAS.codigo, ASIGNATURAS.nombre
                FROM MATRICULAS
                JOIN ALUMNOS ON MATRICULAS.id_alumno = ALUMNOS.id
                JOIN ASIGNATURAS ON MATRICULAS.codigo_asignatura = ASIGNATURAS.codigo;
                """;

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            printResultSet(resultSet);

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    private static void crearTablaAsignaturas() {

        if (!existeTabla("ASIGNATURAS")) {
            String sqlCreateTable = """
                CREATE TABLE ASIGNATURAS (
                    codigo CHAR(3) PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL
                );
                """;

            try (Statement statement = connection.createStatement()) {
                statement.execute(sqlCreateTable);
                System.out.println("Tabla ASIGNATURAS creada.");
            } catch (SQLException e) {
                muestraErrorSQL(e);
            }
        } else {
            System.out.println("La tabla ASIGNATURAS ya existe.");
        }
    }

    private static void crearTablaMatriculas() {

        if (!existeTabla("MATRICULAS")) {
            String sqlCreateTable = """
                CREATE TABLE MATRICULAS (
                    id_alumno INT,
                    codigo_asignatura CHAR(3),
                    PRIMARY KEY (id_alumno, codigo_asignatura),
                    FOREIGN KEY (id_alumno) REFERENCES ALUMNOS(id),
                    FOREIGN KEY (codigo_asignatura) REFERENCES ASIGNATURAS(codigo)
                );
                """;

            try (Statement statement = connection.createStatement()) {
                statement.execute(sqlCreateTable);
                System.out.println("Tabla MATRICULAS creada.");
            } catch (SQLException e) {
                muestraErrorSQL(e);
            }
        } else {
            System.out.println("La tabla MATRICULAS ya existe.");
        }
    }

    //Submenú de alumnos
    private static void menuAlumnos() {
        int opcion;
        do {
            System.out.println("\nMenú Alumnos:");
            System.out.println("1.- Ver todos los Alumnos");
            System.out.println("2.- Crear Alumno");
            System.out.println("3.- Modificar Alumno");
            System.out.println("4.- Borrar Alumno");
            System.out.println("0.- Volver");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1 -> verAlumnos();
                case 2 -> crearAlumno();
                case 3 -> modificarAlumno();
                case 4 -> borrarAlumno();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida!");
            }
        } while (opcion != 0);
    }

    //Submenú de asignaturas
    private static void menuAsignaturas() {
        int opcion;
        do {
            System.out.println("\nMenú Asignaturas:");
            System.out.println("1.- Ver todas las Asignaturas");
            System.out.println("2.- Crear Asignatura");
            System.out.println("3.- Matricular Alumno");
            System.out.println("0.- Volver");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1 -> verAsignaturas();
                case 2 -> crearAsignatura();
                case 3 -> matricularAlumno();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida!");
            }
        } while (opcion != 0);
    }

    //Método para insertar alumno
    private static void matricularAlumno() {

        String sqlInsert = "INSERT INTO MATRICULAS (id_alumno, codigo_asignatura) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

            System.out.println("Introduce el ID del alumno:");
            int idAlumno = entrada.nextInt();
            entrada.nextLine(); //limpiar buffer

            System.out.println("Introduce el código de la asignatura:");
            String codigoAsignatura = entrada.nextLine().toUpperCase();

            preparedStatement.setInt(1, idAlumno);
            preparedStatement.setString(2, codigoAsignatura);
            preparedStatement.executeUpdate();
            System.out.println("Alumno matriculado con éxito.");

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

    }

    //Método para crear las asigaturas
    private static void crearAsignatura() {

        String sqlInsert = "INSERT INTO ASIGNATURAS (codigo, nombre) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

            System.out.println("Introduce el código de la asignatura (3 letras):");
            String codigo = entrada.nextLine().toUpperCase();
            System.out.println("Introduce el nombre de la asignatura:");
            String nombre = entrada.nextLine();

            preparedStatement.setString(1, codigo);
            preparedStatement.setString(2, nombre);
            preparedStatement.executeUpdate();
            System.out.println("Asignatura creada con éxito.");

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    //Método para ver asignaturas
    private static void verAsignaturas() {

        String sql = "SELECT * FROM ASIGNATURAS";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            printResultSet(resultSet);

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    // Método para inicializar la conexión a la BD
    private static void inicializarConexion(String baseDeDatos) {

        try {

            connection = DriverManager.getConnection(Datos.urlConnection, Datos.user, Datos.pwd);
            databaseMetaData = connection.getMetaData();
            System.out.println("Conexión realizada con éxito a la BD: " + baseDeDatos);
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    // Método para crear la base de datos
    private static void crearBDAlumnos() {
        try (Statement statement = connection.createStatement()) {
            String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS " + Datos.baseDeDatos;
            statement.executeUpdate(sqlCreateDB);
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    //Verificar si la base de datos existe
    private static boolean existeBDAlumnos() {
        boolean found = false;
        try (ResultSet resultSet = databaseMetaData.getCatalogs()) {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(Datos.baseDeDatos)) {
                    found = true;
                    break;
                }
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
        return found;
    }

    //Crea tabla ALUMNOS solo si no existe
    private static void crearTablaAlumnos() {

        try {
            if (!existeTabla("ALUMNOS")) {
                String sqlCreateTable = """
                    CREATE TABLE ALUMNOS (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(50) NOT NULL,
                        apellidos VARCHAR(100) NOT NULL
                    );
                    """;
                try (Statement statement = connection.createStatement()) {
                    statement.execute(sqlCreateTable);
                    System.out.println("Tabla ALUMNOS creada.");
                }
            } else {
                System.out.println("La tabla ALUMNOS ya existe.");
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    //Método para comprobar si la tabla existe en la BD
    private static boolean existeTabla(String nombreTabla) {

        boolean existe = false;

        try (ResultSet resultSet = databaseMetaData.getTables(null, null, nombreTabla, null)) {
            if (resultSet.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
        return existe;

    }

    // Método para ver todos los alumnos
    private static void verAlumnos() {
        String sql = "SELECT * FROM ALUMNOS";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            printResultSet(resultSet);

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    // Método para crear un nuevo alumno
    private static void crearAlumno() {
        String sqlInsert = "INSERT INTO ALUMNOS (nombre, apellidos) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            System.out.println("Introduce el nombre del alumno:");
            String nombre = entrada.nextLine();
            System.out.println("Introduce los apellidos del alumno:");
            String apellidos = entrada.nextLine();

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.executeUpdate();
            System.out.println("Alumno creado con éxito.");
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    // Método para modificar un alumno
    private static void modificarAlumno() {
        String sqlUpdate = "UPDATE ALUMNOS SET nombre = ?, apellidos = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            System.out.println("Introduce el ID del alumno que deseas modificar:");
            int id = entrada.nextInt();
            entrada.nextLine(); // Limpiar buffer
            System.out.println("Introduce el nuevo nombre:");
            String nuevoNombre = entrada.nextLine();
            System.out.println("Introduce los nuevos apellidos:");
            String nuevosApellidos = entrada.nextLine();

            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setString(2, nuevosApellidos);
            preparedStatement.setInt(3, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Alumno modificado con éxito.");
            } else {
                System.out.println("No se encontró un alumno con ese ID.");
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    // Método para borrar un alumno
    private static void borrarAlumno() {
        String sqlDelete = "DELETE FROM ALUMNOS WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            System.out.println("Introduce el ID del alumno que deseas borrar:");
            int id = entrada.nextInt();
            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Alumno borrado con éxito.");
            } else {
                System.out.println("No se encontró un alumno con ese ID.");
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    // Método para imprimir el contenido de un ResultSet
    private static void printResultSet(ResultSet rs) throws SQLException {

        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int columnsNumber = resultSetMetaData.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(", ");
                System.out.print(rs.getString(i) + " " + resultSetMetaData.getColumnName(i));
            }
            System.out.println();
        }
    }

    //Método para mostrar errores SQL
    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    //clase static con los datos de la BD
    static class Datos {
        public static String baseDeDatos = ""; //base de datos ALUMNOS
        public static String host = "";
        public static String port = "3306";
        public static String parametrosAdicionales = "";
        public static String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + baseDeDatos + parametrosAdicionales;
        public static String user = "";
        public static String pwd = "";
    }
}
