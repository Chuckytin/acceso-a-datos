package org.example.menu;

import org.example.entities.Employee;
import org.hibernate.Session;
import org.example.entities.Department;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GestorCrud {

    private static final Logger log = LoggerFactory.getLogger(GestorCrud.class);

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
            log.error("Error al crear el departamento {}: {}", nombreDepartamento, e.getMessage(), e);
        }
    }

    public static void createEmployee(Session session) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Nombre del empleado: ");
        String nombreEmpleado = entrada.nextLine();

        System.out.println("Asigna el ID del departamento: ");
        readDepartment(session); // Para ver los departamentos
        int departamentoNum = -1;

        // Validar que se ingresa un número entero válido
        while (departamentoNum == -1) {
            try {
                departamentoNum = Integer.parseInt(entrada.nextLine()); // Convertir a entero
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor, ingresa un número válido para el ID del departamento.");
                log.warn("El usuario ingresó un valor no numérico para el ID del departamento.");
            }
        }

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departamentoNum);
            if (department == null) {
                log.warn("Error el departamento {} no existe.", departamentoNum);
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
            log.error("Error al crear el empleado {}: {}", nombreEmpleado, e.getMessage(), e);
        }
    }

    public static void readEmployee(Session session) {
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
        readDepartment(session); // Visualiza los departamentos
        System.out.println("Introduce el ID del departamento a actualizar:");
        int deptId = -1;

        // Validar que se ingresa un número entero válido
        while (deptId == -1) {
            try {
                deptId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor, ingresa un número válido para el ID del departamento.");
                log.warn("El usuario ingresó un valor no numérico para el ID del departamento.");
            }
        }

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
            log.error("Error al actualizar el departamento {}: {}", deptId, e.getMessage(), e);
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
        int empId = -1;

        // Validar que se ingresa un número entero válido
        while (empId == -1) {
            try {
                empId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor, ingresa un número válido para el ID del empleado.");
                log.warn("El usuario ingresó un valor no numérico para el ID del empleado.");
            }
        }

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
                int newDeptId = -1;

                // Validar que se ingresa un número entero válido
                while (newDeptId == -1) {
                    try {
                        newDeptId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Por favor, ingresa un número válido para el nuevo ID del departamento.");
                        log.warn("El usuario ingresó un valor no numérico para el nuevo ID del departamento.");
                    }
                }

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
            log.error("Error al actualizar el empleado {}: {}", empId, e.getMessage(), e);
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
        int empId = -1;

        // Validar que se ingresa un número entero válido
        while (empId == -1) {
            try {
                empId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor, ingresa un número válido para el ID del empleado.");
                log.warn("El usuario ingresó un valor no numérico para el ID del empleado.");
            }
        }

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
            log.error("Error al borrar el empleado con ID {}: {}", empId, e.getMessage(), e);
        }
    }

    public static void deleteDepartment(Session session) {
        Scanner scanner = new Scanner(System.in);
        readDepartment(session);
        System.out.println("Introduce el ID del departamento a eliminar:");
        int deptId = -1;

        // Validar que se ingresa un número entero válido
        while (deptId == -1) {
            try {
                deptId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor, ingresa un número válido para el ID del departamento.");
                log.warn("El usuario ingresó un valor no numérico para el ID del departamento.");
            }
        }

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, deptId);
            if (department == null) {
                System.out.println("Departamento no encontrado.");
                return;
            }

            // Verifica si el departamento tiene empleados
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
            log.error("Error al borrar el departamento con ID {}: {}", deptId, e.getMessage(), e);
        }
    }


}
