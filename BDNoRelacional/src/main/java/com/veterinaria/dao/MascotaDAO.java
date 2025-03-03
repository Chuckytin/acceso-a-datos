package com.veterinaria.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.veterinaria.connection.MongoDBConnection;
import com.veterinaria.model.Mascota;
import org.bson.Document;

import java.util.Objects;

public class MascotaDAO {

    private final MongoCollection<Document> collection;

    public MascotaDAO() {
        this.collection = MongoDBConnection.getDatabase().getCollection("mascotas");
    }

    public String insertMascota(Mascota mascota) {
        // Crear el documento del propietario embebido
        Document propietarioDoc = new Document("id", mascota.getPropietario().getId())
                .append("nombre", mascota.getPropietario().getNombre())
                .append("apellidos", mascota.getPropietario().getApellidos())
                .append("telefono", mascota.getPropietario().getTelefono())
                .append("email", mascota.getPropietario().getEmail());

        // Crear el documento de la mascota
        Document doc = new Document("nombre", mascota.getNombre())
                .append("raza", mascota.getRaza())
                .append("edad", mascota.getEdad())
                .append("ultimaVisita", mascota.getUltimaVisita())
                .append("propietario", propietarioDoc);

        // Insertar el documento en la colección
        InsertOneResult result = collection.insertOne(doc);
        return Objects.requireNonNull(result.getInsertedId()).asObjectId().getValue().toString();
    }
}