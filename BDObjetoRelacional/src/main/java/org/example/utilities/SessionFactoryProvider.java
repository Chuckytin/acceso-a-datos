package org.example.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {

    public static SessionFactory provideSessionFactory() {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml"); //Hibernate lo buscará en src/main/resources
        return config.buildSessionFactory();

    }

}
