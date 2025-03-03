package com.veterinaria.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBConnection {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBConnection.class);

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "Veterinaria";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                mongoClient = MongoClients.create(CONNECTION_STRING);
                database = mongoClient.getDatabase(DATABASE_NAME);
                logger.info("Conexión a MongoDB establecida correctamente.");
            } catch (Exception e) {
                logger.error("Error al conectar a MongoDB: ", e);
            }
        }
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            logger.info("Conexión a MongoDB cerrada.");
        }
    }
}