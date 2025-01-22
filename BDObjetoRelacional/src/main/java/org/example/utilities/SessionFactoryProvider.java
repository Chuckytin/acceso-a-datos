package org.example.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;

    //Constructor privado para evitar instanciación
    private SessionFactoryProvider() {}

    public static SessionFactory provideSessionFactory() {

        if (sessionFactory == null) {
            try {
                //Carga la configuración de hibernate.cfg.xml
                Configuration config = new Configuration();
                config.configure("hibernate.cfg.xml"); //Hibernate lo buscará en src/main/resources

                //Construcción de la SessionFactory
                sessionFactory = config.buildSessionFactory();

            } catch (Exception e) {
                System.err.println("Error al crear SessionFactory: " + e.getMessage());
                throw new RuntimeException("Error al inicializar Hibernate", e);
            }
        }

        return sessionFactory;
    }

     //Cierra la SessionFactory si está abierta.
     //Este método debe llamarse al finalizar la aplicación para liberar recursos
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}