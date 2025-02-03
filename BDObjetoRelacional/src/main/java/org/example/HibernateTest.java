package org.example;

import org.example.conexion.Data;
import org.example.menu.Menu;
import org.example.utilities.DatabaseVerifier;
import org.example.utilities.SessionFactoryProvider;


public class HibernateTest {

    public static void main(String[] args) {

        if (!DatabaseVerifier.verifyDatabaseExists(Data.url, Data.username, Data.password)) {
            System.err.println("La base de datos no existe o no es accesible.");
            return;
        }

        Menu.gestor();

        SessionFactoryProvider.closeSessionFactory();
    }

}
