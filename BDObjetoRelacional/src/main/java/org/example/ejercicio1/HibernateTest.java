package org.example.ejercicio1;

import org.example.ejercicio1.utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {

    public static void main(String[] args) {
        // Obtiene la SessionFactory
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();

        // Intenta abrir una sesión
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            System.out.println("¡Conexión a la base de datos establecida con éxito!");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace(System.err);
        }

        // Cierra la SessionFactory
        SessionFactoryProvider.closeSessionFactory();
    }
}
