package org.example.ejercicio2.utilities;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

//
public class HibernateConfigReader {

    /*
    private static final Logger log = LoggerFactory.getLogger(HibernateConfigReader.class);
    private static Properties properties;

    //Lee las propiedades de conexión desde el archivo hibernate.cfg.xml.
    public static void readConnectionProperties() {
        log.debug("Leyendo propiedades de conexión de Hibernate");

        Configuration configuration = new Configuration();

        try {
            configuration.configure("hibernate.cfg.xml"); //carga el archivo de configuración

            log.info("Propiedades de Hibernate cargadas correctamente");

            properties = configuration.getProperties();

            String password = properties.getProperty("hibernate.connection.password");
            log.debug("Valor de hibernate.connection.password = {}",
                    password != null ? "[***]" : "no configurado");

        } catch (RuntimeException e) {
            log.error("Error al cargar el archivo de configuración de Hibernate", e);
            throw new RuntimeException(e);
        }

    }

    //Obtiene el valor de una propiedad específica desde el archivo de resources hibernate.cfg.xml
    public static String getConnectionProperty(String propertyName) {
        log.debug("Obteniendo propiedad de conexión: {}", propertyName);

        // Solo llamamos a readConnectionProperties() si las propiedades no están cargadas
        if (properties == null) {
            readConnectionProperties();
        }

        String value = properties.getProperty(propertyName);

        // Mantenemos el formato consistente para todos los logs
        log.debug("Valor de {} = {}",
                propertyName,
                "hibernate.connection.password".equals(propertyName) ? "[***]" : value);

        return value;
    }

    /*
    private static final ConcurrentHashMap<String, String> CONFIG_CACHE = new ConcurrentHashMap<>();

    // Lee las propiedades de conexión desde el archivo hibernate.cfg.xml.
    public static synchronized void readConnectionProperties() {
        if (connectionProperties == null || CONFIG_CACHE.isEmpty()) {
            log.debug("Cargando configuración de Hibernate");

            Configuration configuration = new Configuration();
            try {
                configuration.configure("hibernate.cfg.xml");

                connectionProperties = configuration.getProperties();
                // Almacenamos las propiedades sensibles en el cache
                CONFIG_CACHE.put("hibernate.connection.url",
                        maskSensitive(connectionProperties.getProperty("hibernate.connection.url")));
                CONFIG_CACHE.put("hibernate.connection.username",
                        maskSensitive(connectionProperties.getProperty("hibernate.connection.username")));
                CONFIG_CACHE.put("hibernate.connection.password",
                        maskSensitive(connectionProperties.getProperty("hibernate.connection.password")));

                log.info("Propiedades de Hibernate cargadas correctamente");
            } catch (Exception e) {
                log.error("Error al cargar el archivo de configuración de Hibernate", e);
                throw new RuntimeException(e);
            }
        } else {
            log.debug("Usando propiedades cacheadas");
        }
    }

    // Obtiene el valor de una propiedad específica desde el archivo de resources hibernate.cfg.xml
    public static String getConnectionProperty(String propertyName) {
        log.trace("Obteniendo propiedad de conexión: {}", maskPropertyName(propertyName));

        if (!CONFIG_CACHE.containsKey(propertyName)) {
            readConnectionProperties(); // Asegura que la configuración está cargada
        }

        String value = CONFIG_CACHE.getOrDefault(propertyName, "");
        log.debug("Valor de {}: {}", maskPropertyName(propertyName),
                isSensitiveProperty(propertyName) ? "[***]" : value);

        return value;
    }

    private static boolean isSensitiveProperty(String propertyName) {
        return propertyName.contains("password") ||
                propertyName.contains("credential") ||
                propertyName.endsWith(".password");
    }

    private static String maskPropertyName(String propertyName) {
        return isSensitiveProperty(propertyName) ? "[SENSITIVE_PROPERTY]" : propertyName;
    }

    private static String maskSensitive(String value) {
        if (value == null || !isSensitiveProperty(value)) {
            return value;
        }
        return value.replaceAll("\\.", "*");
    }
    */

}
