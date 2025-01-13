package org.example;

import org.example.entities.Employee;
import org.example.utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateTest {

    public static void main(String[] args) {

        try {
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            Employee emp = new Employee(101, "John");
            session.save(emp);

            t.commit();
            sessionFactory.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
