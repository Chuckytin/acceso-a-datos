package com.veterinaria.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.veterinaria.connection.MongoDBConnection;
import com.veterinaria.model.Propietario;
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PropietarioMascotaDAO {

    private final MongoCollection<Document> collection;

    public PropietarioMascotaDAO() {
        this.collection = MongoDBConnection.getDatabase().getCollection("propietario_mascota");
    }

    public String insertPropietarioConMascotas(Propietario propietario) {
        // Convierte la lista de mascotas a una lista de documentos
        List<Document> mascotasDocumentos = propietario.getMascotas().stream()
                .map(mascota -> new Document("nombre", mascota.getNombre())
                        .append("raza", mascota.getRaza())
                        .append("edad", mascota.getEdad())
                        .append("ultimaVisita", mascota.getUltimaVisita()))
                .collect(Collectors.toList());

        // Crea el documento del propietario con sus mascotas
        Document doc = new Document("nombre", propietario.getNombre())
                .append("apellidos", propietario.getApellidos())
                .append("telefono", propietario.getTelefono())
                .append("email", propietario.getEmail())
                .append("mascotas", mascotasDocumentos);

        // Inserta el documento en la colección
        InsertOneResult result = collection.insertOne(doc);
        return Objects.requireNonNull(result.getInsertedId()).asObjectId().getValue().toString();
    }

}