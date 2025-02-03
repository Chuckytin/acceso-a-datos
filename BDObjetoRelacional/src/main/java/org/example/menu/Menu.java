package org.example.menu;

import org.example.conexion.Data;
import org.example.entities.Department;
import org.example.entities.Employee;
import org.example.utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void gestor() {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()) {

            Scanner entrada = new Scanner(System.in);
            boolean salir = false;
            int opcion;

            while (!salir) {
                System.out.println("1 - Ver empleados. \n2 - Ver departamentos. \n3 - Añadir departamento. \n4 - Salir.");
                opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {
                    case 1:
                        listEmployees(session);
                        break;
                    case 2:
                        listDepartments(session);
                        break;
                    case 3:
                        addDepartment(session);
                        break;
                    case 4:
                        salir = true;
                        System.out.println("Hasta luego...");
                        break;
                    default:
                        System.out.println("Opción no válida, inténtelo de nuevo...");
                }
            }

            entrada.close();

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private static void listDepartments(Session session) {
        List<Department> departments = session.createQuery("FROM Department", Department.class).list();

        if (departments.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
        } else {
            for (Department dept : departments) {
                System.out.println("ID: " + dept.getDeptId() + ", Nombre: " + dept.getDeptName());
            }
        }
    }

    private static void listEmployees(Session session) {
        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();

        if (employees.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Employee emp : employees) {
                System.out.println("ID: " + emp.getEmpId() + ", Nombre: " + emp.getEmpName() +
                        ", ID Departamento: " + emp.getDepartment().getDeptId());
            }
        }

        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Deseas añadir un nuevo empleado? (si/no)");
        String respuesta = entrada.nextLine();

        if (respuesta.equalsIgnoreCase("si")) {
            addEmployee(session);
        }
    }

    private static void addEmployee(Session session) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Nombre del empleado: ");
        String nombreEmpleado = entrada.nextLine();

        System.out.println("Asigna el ID del departamento: ");
        listDepartments(session);
        int departamentoNum = entrada.nextInt();
        entrada.nextLine();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departamentoNum);
            if (department == null) {
                System.err.println("ERROR: El departamento no existe.");
                transaction.rollback();
                return;
            }

            Employee employee = new Employee(nombreEmpleado);
            employee.setDepartment(department);
            session.persist(employee);
            transaction.commit();

            System.out.println("Empleado agregado con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("ERROR al agregar empleado: " + e.getMessage());
        }
    }

    private static void addDepartment(Session session) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre del nuevo departamento: ");
        String nombreDepartamento = entrada.nextLine();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Department department = new Department(nombreDepartamento);
            session.persist(department);
            transaction.commit();

            System.out.println("Departamento agregado con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("ERROR al agregar departamento: " + e.getMessage());
        }
    }

}


