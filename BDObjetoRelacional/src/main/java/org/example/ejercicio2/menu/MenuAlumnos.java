package org.example.ejercicio2.menu;

import org.example.ejercicio2.entities.Alumno;
import org.example.ejercicio2.entities.Empresa;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class MenuAlumnos {

    private static final Logger log = LoggerFactory.getLogger(MenuAlumnos.class);

    // Crea un nuevo alumno
    public static void crearAlumno(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce los apellidos del alumno: ");
        String apellidos = scanner.nextLine();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setApellidos(apellidos);
            session.persist(alumno);
            transaction.commit();
            System.out.println("Alumno agregado con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error al crear el alumno: {}", e.getMessage(), e);
        }
    }

    // Muestra todos los alumnos
    public static void verAlumnos(Session session) {
        List<Alumno> alumnos = session.createQuery("FROM Alumno", Alumno.class).list();

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        }
    }

    // Busca alumno por nombre
    public static void buscarAlumnoPorNombre(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre del alumno a buscar: ");
        String nombre = scanner.nextLine();

        List<Alumno> alumnos = session.createQuery("FROM Alumno WHERE nombre = :nombre", Alumno.class)
                .setParameter("nombre", nombre)
                .list();

        if (alumnos.isEmpty()) {
            System.out.println("No se encontraron alumnos con el nombre: " + nombre);
        } else {
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        }
    }

    // Modifica alumno por ID
    public static void modificarAlumno(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el ID del alumno a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno == null) {
                System.out.println("No se encontró el alumno con ID " + id);
                return;
            }

            System.out.print("Nuevo nombre del alumno (actual: " + alumno.getNombre() + "): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                alumno.setNombre(nuevoNombre);
            }

            System.out.print("Nuevos apellidos del alumno (actual: " + alumno.getApellidos() + "): ");
            String nuevosApellidos = scanner.nextLine();
            if (!nuevosApellidos.isEmpty()) {
                alumno.setApellidos(nuevosApellidos);
            }

            session.update(alumno);
            transaction.commit();
            System.out.println("Alumno actualizado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error al actualizar el alumno: {}", e.getMessage(), e);
        }
    }

    // Borra alumno por ID
    public static void borrarAlumno(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el ID del alumno a borrar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno == null) {
                System.out.println("No se encontró el alumno con ID " + id);
                return;
            }
            session.delete(alumno);
            transaction.commit();
            System.out.println("Alumno eliminado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error al borrar el alumno: {}", e.getMessage(), e);
        }
    }

    // Mostrar empresas de los alumnos
    public static void mostrarEmpresasDeAlumno(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el ID del alumno: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Alumno alumno = session.get(Alumno.class, id);
        if (alumno == null) {
            System.out.println("No se encontró el alumno con ID " + id);
            return;
        }

        if (alumno.getEmpresas().isEmpty()) {
            System.out.println("El alumno no está asociado a ninguna empresa.");
        } else {
            System.out.print("El alumno está asociado a la empresa:");
            for (Empresa empresa : alumno.getEmpresas()) {
                System.out.print(" - " + empresa.getNombre());
            }
            System.out.println();
        }
    }
}