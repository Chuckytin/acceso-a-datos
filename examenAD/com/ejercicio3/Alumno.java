package com.ejercicio3;

import java.sql.*;

public class Alumno {
    private int id;
    private String nombre;
    private String apellido1;

    // Constructor vacío
    public Alumno() {
    }

    // Constructor con parámetros
    public Alumno(int id, String nombre, String apellido1) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }

    // Método toString sobreescrito para mostrar los datos del alumno
    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                '}';
    }

<<<<<<< HEAD
    // Método para guardar un alumno en la tabla ALUMNOS2
    public void guardarAlumno2(Connection connection) {

        String sqlInsert = "INSERT INTO ALUMNOS2 (nombre, apellido1) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.apellido1);
            preparedStatement.executeUpdate();

            System.out.println("Alumno guardado en ALUMNOS2: " + this.toString());

=======
    //Método para guardar un alumno en la tabla ALUMNOS2
    public void guardarAlumno2(Connection connection) {
        
        String sqlInsert = "INSERT INTO ALUMNOS2 (nombre, apellido1) VALUES (?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.apellido1);
            preparedStatement.executeUpdate();
            
            System.out.println("Alumno guardado en ALUMNOS2: " + this.toString());
            
>>>>>>> b92c9b93618b5cdbfcbe4fda800248a072cb8b00
        } catch (SQLException e) {
            System.err.println("Error al guardar el alumno en ALUMNOS2: " + e.getMessage());
        }
    }

    //Método para cargar un alumno desde un ResultSet
    public static Alumno cargar(ResultSet resultSet) throws SQLException {
<<<<<<< HEAD

        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String apellido1 = resultSet.getString("apellido1");

=======
        
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String apellido1 = resultSet.getString("apellido1");
        
>>>>>>> b92c9b93618b5cdbfcbe4fda800248a072cb8b00
        return new Alumno(id, nombre, apellido1);
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
}

class Main {

    //main
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(Datos.urlConnection, Datos.user, Datos.pwd)) {
            //Crear la tabla ALUMNOS2 si no existen
            crearTablaAlumnos2(connection);

            //Crear los 5 alumnos
            Alumno alumno1 = new Alumno(0, "Juan", "Pérez");
            Alumno alumno2 = new Alumno(0, "María", "Gómez");
            Alumno alumno3 = new Alumno(0, "Luis", "Martínez");
            Alumno alumno4 = new Alumno(0, "Ana", "Sánchez");
            Alumno alumno5 = new Alumno(0, "Pedro", "López");


            // guardar los alumnos en la tabla ALUMNOS2
            alumno1.guardarAlumno2(connection);
            alumno2.guardarAlumno2(connection);
            alumno3.guardarAlumno2(connection);
            alumno4.guardarAlumno2(connection);
            alumno5.guardarAlumno2(connection);

            //Muestra los alumnos cargados desde la base de datos de la tabla ALUMNOS2
            System.out.println("\nAlumnos cargados de la tabla ALUMNOS2:");
            mostrarAlumnos(connection, "ALUMNOS2");

        } catch (SQLException e) {
            System.err.println("Error en la conexión o en la ejecución SQL: " + e.getMessage());
        }
    }

    //Método para crear la tabla ALUMNOS2
    private static void crearTablaAlumnos2(Connection connection) {
        String sqlCreateTable = """
                CREATE TABLE IF NOT EXISTS ALUMNOS2 (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(50) NOT NULL,
                    apellido1 VARCHAR(100) NOT NULL
                );
                """;
        
        try (Statement statement = connection.createStatement()) {
            
            statement.execute(sqlCreateTable);
            System.out.println("Tabla ALUMNOS2 creada o ya existía.");
            
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla ALUMNOS2: " + e.getMessage());
        }
    }

    //Método para comprobar si la tabla existe en la BD
    private static boolean existeTabla(String nombreTabla) throws SQLException {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(Datos.urlConnection, Datos.user, Datos.pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.close();
        }

        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean existe = false;

        try (ResultSet resultSet = databaseMetaData.getTables(null, null, nombreTabla, null)) {
            
            if (resultSet.next()) {
                existe = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return existe;

    }

    //Método para mostrar los alumnos desde una tabla especificada
    private static void mostrarAlumnos(Connection connection, String tabla) throws SQLException {
        
        String sqlSelect = "SELECT * FROM " + tabla;
<<<<<<< HEAD

=======
        
>>>>>>> b92c9b93618b5cdbfcbe4fda800248a072cb8b00
        try (Statement statement = connection.createStatement();
             
             ResultSet resultSet = statement.executeQuery(sqlSelect)) {

            while (resultSet.next()) {
                Alumno alumnoCargado = Alumno.cargar(resultSet);
                System.out.println(alumnoCargado);
            }
        }
    }

    //Clase estática con los datos de conexión
    static class Datos {
        public static String baseDeDatos = "";
        public static String host = "";
        public static String port = "3306";
        public static String parametrosAdicionales = "";
        public static String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + baseDeDatos + parametrosAdicionales;
        public static String user = "";
        public static String pwd = "";
    }

}
