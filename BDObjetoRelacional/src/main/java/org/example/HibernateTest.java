package org.example;

import org.example.entities.Employee;
import org.example.utilities.DatabaseVerifier;
import org.example.utilities.HibernateConfigReader;
import org.example.utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateTest {

    public static void main(String[] args) {

        //Obtiene las propiedades de conexión desde hibernate.cfg.xml
        String url = HibernateConfigReader.getConnectionProperty("hibernate.connection.url");
        String username = HibernateConfigReader.getConnectionProperty("hibernate.connection.username");
        String password = HibernateConfigReader.getConnectionProperty("hibernate.connection.password");

        if (!DatabaseVerifier.verifyDatabaseExists(url, username, password)) {
            System.err.println("La base de datos no existe o no es accesible.");
            return;
        }

        Transaction transaction = null;

        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Employee emp1 = new Employee(5, "Homero");
            Employee emp2 = new Employee("Tatiana");
            session.save(emp1); //save si existe el ID
            session.persist(emp2); //persist si no conocemos el ID para que lo cree

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback(); //revertir la transacción en caso de error
            }
            System.err.println("ERROR: " + e.getMessage());

        }

        //Cierra la SessionFactory al finalizar la aplicación
        SessionFactoryProvider.closeSessionFactory();

    }
}
