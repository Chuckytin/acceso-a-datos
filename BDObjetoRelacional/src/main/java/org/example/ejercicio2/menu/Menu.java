package org.example.ejercicio2.menu;

import org.example.ejercicio2.utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Menu {

    public static void menu() {
        Session session = SessionFactoryProvider.provideSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Menú Alumnos");
            System.out.println("2. Menú Empresas");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuAlumnos(session, scanner);
                    break;
                case 2:
                    menuEmpresas(session, scanner);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);

        SessionFactoryProvider.closeSessionFactory();
        scanner.close();
    }

    private static void menuAlumnos(Session session, Scanner scanner) {
        int opcion;

        do {
            System.out.println("1. Crear Alumno");
            System.out.println("2. Ver Alumnos");
            System.out.println("3. Buscar Alumno por Nombre");
            System.out.println("4. Modificar Alumno");
            System.out.println("5. Borrar Alumno");
            System.out.println("6. Mostrar Empresa de un Alumno");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    MenuAlumnos.crearAlumno(session);
                    break;
                case 2:
                    MenuAlumnos.verAlumnos(session);
                    break;
                case 3:
                    MenuAlumnos.buscarAlumnoPorNombre(session);
                    break;
                case 4:
                    MenuAlumnos.modificarAlumno(session);
                    break;
                case 5:
                    MenuAlumnos.borrarAlumno(session);
                    break;
                case 6:
                    MenuAlumnos.mostrarEmpresasDeAlumno(session);
                    break;
                case 7:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }

    private static void menuEmpresas(Session session, Scanner scanner) {
        int opcion;

        do {
            System.out.println("1. Crear Empresa");
            System.out.println("2. Asignar Alumno a Empresa");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    MenuEmpresas.crearEmpresa(session);
                    break;
                case 2:
                    MenuEmpresas.asignarAlumnoAEmpresa(session);
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }
}