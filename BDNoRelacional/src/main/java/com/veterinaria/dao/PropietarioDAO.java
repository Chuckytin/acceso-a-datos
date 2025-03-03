package com.veterinaria.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.veterinaria.connection.MongoDBConnection;
import com.veterinaria.model.Propietario;
import org.bson.Document;

import java.util.Objects;

public class PropietarioDAO {

    private final MongoCollection<Document> collection;

    public PropietarioDAO() {
        this.collection = MongoDBConnection.getDatabase().getCollection("propietarios");
    }

    public String insertPropietario(Propietario propietario) {
        Document doc = new Document("nombre", propietario.getNombre())
                .append("apellidos", propietario.getApellidos())
                .append("telefono", propietario.getTelefono())
                .append("email", propietario.getEmail());

        InsertOneResult result = collection.insertOne(doc);
        return Objects.requireNonNull(result.getInsertedId()).asObjectId().getValue().toString();
    }
}