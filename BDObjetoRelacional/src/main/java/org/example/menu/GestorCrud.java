package org.example.menu;

import org.example.entities.Employee;
import org.hibernate.Session;
import org.example.entities.Department;
import org.hibernate.Transaction;

import java.util.*;

public class GestorCrud {

    public static void createDepartment(Session session) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre del nuevo departamento: ");
        String nombreDepartamento = scanner.nextLine();

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

    public static void createEmployee(Session session) {

        Scanner entrada = new Scanner(System.in);
        System.out.println("Nombre del empleado: ");
        String nombreEmpleado = entrada.nextLine();

        System.out.println("Asigna el ID del departamento: ");
        readDepartment(session); //para ver los departamentos
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

    public static void readEmployee(Session session) {

        // TreeSet para ordenar automáticamente por el ID del empleado
        Set<Employee> employees = new TreeSet<>(Comparator.comparingInt(Employee::getEmpId));
        employees.addAll(session.createQuery("FROM Employee", Employee.class).list());

        if (employees.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Employee emp : employees) {
                System.out.println("ID: " + emp.getEmpId() + ", Nombre: " + emp.getEmpName() +
                        ", ID Departamento: " + (emp.getDepartment() != null ? emp.getDepartment().getDeptId() : "Sin departamento"));
            }
        }

        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Deseas añadir un nuevo empleado? (si/no)");
        String respuesta = entrada.nextLine();

        if (respuesta.equalsIgnoreCase("si")) {
            createEmployee(session);
        }

    }

    public static void readDepartment(Session session) {

        //TreeSet para ordenar automáticamente por el ID del departamento
        Set<Department> departments = new TreeSet<>(Comparator.comparingInt(Department::getDeptId));
        departments.addAll(session.createQuery("FROM Department", Department.class).list());

        if (departments.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
        } else {
            for (Department dept : departments) {
                System.out.println("ID: " + dept.getDeptId() + ", Nombre: " + dept.getDeptName());
            }
        }
    }

    public static void updateDepartment(Session session) {

        Scanner scanner = new Scanner(System.in);
        readDepartment(session); //visualiza los departments
        System.out.println("Introduce el ID del departamento a actualizar:");
        int deptId = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();
            Department department = session.get(Department.class, deptId);
            if (department == null) {
                System.out.println("No se encontró el departamento con ID " + deptId);
                return;
            }
            System.out.print("Nuevo nombre del departamento (actual: " + department.getDeptName() + "): ");
            String newName = scanner.nextLine();
            department.setDeptName(newName);
            session.update(department);
            transaction.commit();
            System.out.println("Departamento actualizado correctamente.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al actualizar departamento: " + e.getMessage());
        }
    }

    public static void updateEmployee(Session session) {

        Scanner scanner = new Scanner(System.in);
        Set<Employee> employees = new HashSet<>(session.createQuery("FROM Employee", Employee.class).list());

        if (employees.isEmpty()) {
            System.out.println("No hay empleados para actualizar.");
            return;
        }

        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getEmpId() + ", Nombre: " + emp.getEmpName() +
                    ", Departamento: " + (emp.getDepartment() != null ? emp.getDepartment().getDeptName() : "Ninguno"));
        }

        System.out.println("Introduce el ID del empleado a actualizar:");
        int empId = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, empId);
            if (employee == null) {
                System.out.println("Empleado no encontrado con ID " + empId);
                return;
            }

            System.out.print("Nuevo nombre del empleado (actual: " + employee.getEmpName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                employee.setEmpName(newName);
            }

            System.out.print("¿Cambiar departamento? (si/no): ");
            String cambiarDept = scanner.nextLine();

            if (cambiarDept.equalsIgnoreCase("si")) {
                readDepartment(session);
                System.out.print("Introduce el nuevo ID del departamento: ");
                int newDeptId = scanner.nextInt();
                scanner.nextLine();

                Department newDept = session.get(Department.class, newDeptId);
                if (newDept == null) {
                    System.out.println("El departamento no existe.");
                    transaction.rollback();
                    return;
                }
                employee.setDepartment(newDept);
            }

            session.update(employee);
            transaction.commit();
            System.out.println("Empleado actualizado correctamente.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al actualizar empleado: " + e.getMessage());
        }
    }

    public static void deleteEmployee(Session session) {

        Scanner scanner = new Scanner(System.in);
        Set<Employee> employees = new HashSet<>(session.createQuery("FROM Employee", Employee.class).list());

        if (employees.isEmpty()) {
            System.out.println("No hay empleados para eliminar.");
            return;
        }

        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getEmpId() + ", Nombre: " + emp.getEmpName());
        }

        System.out.println("Introduce el ID del empleado a eliminar:");
        int empId = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, empId);
            if (employee == null) {
                System.out.println("Empleado no encontrado.");
                return;
            }
            session.delete(employee);
            transaction.commit();
            System.out.println("Empleado eliminado correctamente.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }

    public static void deleteDepartment(Session session) {

        Scanner scanner = new Scanner(System.in);
        readDepartment(session);
        System.out.println("Introduce el ID del departamento a eliminar:");
        int deptId = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();
            Department department = session.get(Department.class, deptId);
            if (department == null) {
                System.out.println("Departamento no encontrado.");
                return;
            }

            //Verifica si el departamento tiene empleados
            Set<Employee> employeesInDept = department.getEmployees();
            if (employeesInDept != null && !employeesInDept.isEmpty()) {
                System.out.println("No se puede eliminar el departamento. Tiene empleados asignados.");
                transaction.rollback();
                return;
            }

            session.delete(department);
            transaction.commit();
            System.out.println("Departamento eliminado correctamente.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar departamento: " + e.getMessage());
        }
    }
}