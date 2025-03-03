package com.veterinaria;

import com.veterinaria.db.MascotaDAO;
import com.veterinaria.db.MongoDBConnection;
import com.veterinaria.model.Mascota;
import com.veterinaria.model.Propietario;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Crear una mascota y un propietario
        Propietario propietario = new Propietario();
        propietario.setId(Long.valueOf("1"));
        propietario.setNombre("Emilio");
        propietario.setApellidos("Delgado");
        propietario.setTelefono("985658785");
        propietario.setEmail("em.ado@outlook.com");

        Mascota mascota = new Mascota();
        mascota.setNombre("Meneillos");
        mascota.setRaza("Común Europeo");
        mascota.setEdad(6);
        mascota.setPropietario(propietario);
        mascota.setUltimaVisita(new Date());

        // Insertar la mascota en la base de datos
        MascotaDAO mascotaDAO = new MascotaDAO();
        String mascotaId = mascotaDAO.insertMascota(mascota);
        System.out.println("Mascota insertada con ID: " + mascotaId);

        // Cerrar la conexión
        MongoDBConnection.closeConnection();
    }
}