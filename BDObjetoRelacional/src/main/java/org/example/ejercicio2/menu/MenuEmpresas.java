package org.example.ejercicio2.menu;

import org.example.ejercicio2.entities.Alumno;
import org.example.ejercicio2.entities.Empresa;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MenuEmpresas {

    private static final Logger log = LoggerFactory.getLogger(MenuEmpresas.class);

    // Crea una nueva empresa
    public static void crearEmpresa(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el CIF de la empresa: ");
        String cif = scanner.nextLine();
        System.out.println("Introduce el nombre de la empresa: ");
        String nombre = scanner.nextLine();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Empresa empresa = new Empresa();
            empresa.setCif(cif);
            empresa.setNombre(nombre);
            session.persist(empresa);
            transaction.commit();
            System.out.println("Empresa agregada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error al crear la empresa: {}", e.getMessage(), e);
        }
    }

    // Asigna alumno a empresa
    public static void asignarAlumnoAEmpresa(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el ID del alumno: ");
        int idAlumno = scanner.nextInt();
        scanner.nextLine(); // Consume el salto de línea
        System.out.println("Introduce el CIF de la empresa: ");
        String cifEmpresa = scanner.nextLine();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, idAlumno);
            Empresa empresa = session.get(Empresa.class, cifEmpresa);

            if (alumno == null || empresa == null) {
                System.out.println("Alumno o empresa no encontrados.");
                return;
            }

            empresa.getAlumnos().add(alumno);
            session.update(empresa);
            transaction.commit();
            System.out.println("Alumno asignado a la empresa correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error al asignar alumno a empresa: {}", e.getMessage(), e);
        }
    }
}