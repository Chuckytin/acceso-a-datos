package com.veterinaria.db;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.veterinaria.model.Mascota;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MascotaDAO {

    private static final Logger logger = LoggerFactory.getLogger(MascotaDAO.class);

    private final MongoCollection<Document> collection;

    public MascotaDAO() {
        this.collection = MongoDBConnection.getDatabase().getCollection("mascotas");
    }

    public String insertMascota(Mascota mascota) {
        try {
            Document doc = new Document("nombre", mascota.getNombre())
                    .append("raza", mascota.getRaza())
                    .append("edad", mascota.getEdad())
                    .append("propietario", new Document("id", mascota.getPropietario().getId())
                            .append("nombre", mascota.getPropietario().getNombre())
                            .append("apellidos", mascota.getPropietario().getApellidos())
                            .append("telefono", mascota.getPropietario().getTelefono())
                            .append("email", mascota.getPropietario().getEmail()))
                    .append("ultimaVisita", mascota.getUltimaVisita());

            InsertOneResult result = collection.insertOne(doc);
            logger.info("Mascota insertada con ID: {}", result.getInsertedId());
            return Objects.requireNonNull(result.getInsertedId()).asObjectId().getValue().toString();
        } catch (MongoException e) {
            logger.error("Error al insertar la mascota: ", e);
            return null;
        }
    }
}