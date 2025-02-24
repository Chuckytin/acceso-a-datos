package org.example.ejercicio2.utilities;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFactoryProvider {

    private static final Logger log = LoggerFactory.getLogger(SessionFactoryProvider.class);

    private static SessionFactory sessionFactory;

    // Constructor privado para evitar instanciación
    private SessionFactoryProvider() {}

    // Método para obtener la SessionFactory (y verificar la conexión al mismo tiempo)
    public static SessionFactory provideSessionFactory() {

        if (sessionFactory == null) {
            try {
                // Carga la configuración de hibernate.cfg.xml
                Configuration config = new Configuration();
                log.info("Configurando Hibernate desde hibernate.cfg.xml");
                config.configure("hibernate.cfg.xml"); // Hibernate lo buscará en src/main/resources

                // Construcción de la SessionFactory
                sessionFactory = config.buildSessionFactory();

                // Verificación de la conexión a la base de datos a través de Hibernate
                try (var session = sessionFactory.openSession()) {
                    session.beginTransaction();
                    session.getTransaction().commit(); // Si no hay excepción, la conexión es exitosa
                    log.info("Conexión exitosa a la base de datos a través de Hibernate");
                }

            } catch (HibernateException e) {
                log.error("Error al crear SessionFactory o al verificar la conexión", e);
                throw new RuntimeException("Error al inicializar Hibernate o verificar la conexión", e);
            }
        } else {
            log.debug("Reutilizando SessionFactory existente");
        }

        return sessionFactory;
    }


    // Cierra la SessionFactory si está abierta
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            try {
                sessionFactory.close();
                log.info("SessionFactory cerrada exitosamente");
            } catch (Exception e) {
                log.error("Error al cerrar SessionFactory", e);
            } finally {
                sessionFactory = null;
                log.debug("Referencia de SessionFactory establecida en null");
            }
        } else {
            log.debug("No se realizó cierre: SessionFactory ya está cerrada o es null");
        }
    }
}
