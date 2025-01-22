package org.example.utilities;

import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateConfigReader {

    //Lee las propiedades de conexión desde el archivo hibernate.cfg.xml.
    public static Properties readConnectionProperties() {

        Configuration configuration = new Configuration();

        configuration.configure("hibernate.cfg.xml"); //carga el archivo de configuración

        return configuration.getProperties(); //devuelve las propiedades
    }

    //Obtiene el valor de una propiedad específica desde el archivo de resources hibernate.cfg.xml
    public static String getConnectionProperty(String propertyName) {

        Properties properties = readConnectionProperties();
        
        return properties.getProperty(propertyName);
    }

}
