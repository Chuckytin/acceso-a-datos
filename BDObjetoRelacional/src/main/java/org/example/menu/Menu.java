package org.example.menu;


import org.example.utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;


public class Menu {

    public static void gestor() {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()) {

            Scanner entrada = new Scanner(System.in);
            boolean salir = false;
            int opcion;

            while (!salir) {
                System.out.println("""
                        1 - Crear nuevo Empleado o Departamento.\
                        
                        2 - Ver Empleados o Departamentos.\
                        
                        3 - Actualizar Empleado o Departamento. \
                        
                        4 - Borrar Empleado o Departamento.\
                        
                        5 - Salir.""");
                opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {
                    case 1:
                        boolean salirNuevo = false;
                        while (!salirNuevo) {
                            System.out.println("1 - Nuevo Empleado.\n2 - Nuevo Departamento.\n3 - Salir.");
                            int eleccion = entrada.nextInt();
                            entrada.nextLine();
                            switch (eleccion) {
                                case 1 -> GestorCrud.createEmployee(session);
                                case 2 -> GestorCrud.createDepartment(session);
                                case 3 -> salirNuevo = true;
                                default -> System.out.println("Opción no válida!");
                            }
                        }
                        break;
                    case 2:
                        boolean salirVer = false;
                        while (!salirVer) {
                            System.out.println("1 - Ver Empleados.\n2 - Ver Departamentos.\n3 - Salir.");
                            int eleccion2 = entrada.nextInt();
                            entrada.nextLine();
                            switch (eleccion2) {
                                case 1 -> GestorCrud.readEmployee(session);
                                case 2 -> GestorCrud.readDepartment(session);
                                case 3 -> salirVer = true;
                                default -> System.out.println("Opción no válida!");
                            }
                        }
                        break;
                    case 3:
                        boolean salirActualizar = false;
                        while (!salirActualizar) {
                            System.out.println("1 - Actualizar Empleado.\n2 - Actualizar Departamento.\n3 - Salir.");
                            int eleccion3 = entrada.nextInt();
                            entrada.nextLine();
                            switch (eleccion3) {
                                case 1 -> GestorCrud.updateEmployee(session);
                                case 2 -> GestorCrud.updateDepartment(session);
                                case 3 -> salirActualizar = true;
                                default -> System.out.println("Opción no válida!");
                            }
                        }
                        break;
                    case 4:
                        boolean salirBorrar = false;
                        while (!salirBorrar) {
                            System.out.println("1 - Borrar Empleado.\n2 - Borrar Departamento.\n3 - Salir.");
                            int eleccion4 = entrada.nextInt();
                            entrada.nextLine();
                            switch (eleccion4) {
                                case 1 -> GestorCrud.deleteEmployee(session);
                                case 2 -> GestorCrud.deleteDepartment(session);
                                case 3 -> salirBorrar = true;
                                default -> System.out.println("Opción no válida!");
                            }
                        }
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Hasta luego...");
                        break;
                    default:
                        System.out.println("Opción no válida, inténtelo de nuevo...");
                }
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

}


