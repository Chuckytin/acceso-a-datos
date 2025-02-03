package org.example.conexion;

import org.example.utilities.HibernateConfigReader;

public class Data {

    //Obtiene las propiedades de conexión desde hibernate.cfg.xml
    public static String url = HibernateConfigReader.getConnectionProperty("hibernate.connection.url");
    public static String username = HibernateConfigReader.getConnectionProperty("hibernate.connection.username");
    public static String password = HibernateConfigReader.getConnectionProperty("hibernate.connection.password");

}
