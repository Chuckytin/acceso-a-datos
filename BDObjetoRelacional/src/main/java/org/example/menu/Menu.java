package org.example.menu;

import org.example.utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Menu {

    private static final Logger log = LoggerFactory.getLogger(Menu.class);

    //gestor
    public static void gestor() {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()) {

            Scanner entrada = new Scanner(System.in);
            boolean salir = false;
            int opcion = -1;

            while (!salir) {
                System.out.println("""
                        1 - Crear nuevo Empleado o Departamento.
                        2 - Ver Empleados o Departamentos.
                        3 - Actualizar Empleado o Departamento.
                        4 - Borrar Empleado o Departamento.
                        5 - Salir.""");

                // Validar que la opción ingresada sea un número válido
                while (opcion == -1) {
                    try {
                        opcion = Integer.parseInt(entrada.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Por favor, ingresa un número válido entre 1 y 5.");
                        log.warn("El usuario ingresó una opción no numérica.");
                    }
                }

                switch (opcion) {
                    case 1:
                        boolean salirNuevo = false;
                        while (!salirNuevo) {
                            System.out.println("1 - Nuevo Empleado.\n2 - Nuevo Departamento.\n3 - Salir.");
                            int eleccion = -1;
                            while (eleccion == -1) {
                                try {
                                    eleccion = Integer.parseInt(entrada.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("ERROR: Por favor, ingresa una opción válida.");
                                    log.warn("El usuario ingresó una opción no numérica en la creación.");
                                }
                            }
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
                            int eleccion2 = -1;
                            while (eleccion2 == -1) {
                                try {
                                    eleccion2 = Integer.parseInt(entrada.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("ERROR: Por favor, ingresa una opción válida.");
                                    log.warn("El usuario ingresó una opción no numérica en la visualización.");
                                }
                            }
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
                            int eleccion3 = -1;
                            while (eleccion3 == -1) {
                                try {
                                    eleccion3 = Integer.parseInt(entrada.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("ERROR: Por favor, ingresa una opción válida.");
                                    log.warn("El usuario ingresó una opción no numérica en la actualización.");
                                }
                            }
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
                            int eleccion4 = -1;
                            while (eleccion4 == -1) {
                                try {
                                    eleccion4 = Integer.parseInt(entrada.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("ERROR: Por favor, ingresa una opción válida.");
                                    log.warn("El usuario ingresó una opción no numérica en la eliminación.");
                                }
                            }
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
                        log.info("El usuario ha salido del menú.");
                        break;
                    default:
                        System.out.println("Opción no válida, inténtelo de nuevo...");
                        log.warn("El usuario ingresó una opción no válida en el menú principal.");
                }
                opcion = -1;  //Restablecer la opción después de cada ciclo.
            }

        } catch (Exception e) {
            log.error("Se ha producido un error en el gestor: {}.", e.getMessage(), e);
        }
    }
}
