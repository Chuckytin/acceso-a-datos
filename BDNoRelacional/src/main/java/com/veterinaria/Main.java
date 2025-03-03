package com.veterinaria;

import com.veterinaria.dao.MascotaDAO;
import com.veterinaria.dao.PropietarioDAO;
import com.veterinaria.dao.PropietarioMascotaDAO;
import com.veterinaria.connection.MongoDBConnection;
import com.veterinaria.model.Mascota;
import com.veterinaria.model.Propietario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // Crea un propietario
        Propietario propietario = new Propietario();
        propietario.setNombre("Javi");
        propietario.setApellidos("Jabo");
        propietario.setTelefono("600250350");
        propietario.setEmail("javibo@gmail.com");

        // Inserta el propietario en la colección "propietario"
        PropietarioDAO propietarioDAO = new PropietarioDAO();
        String propietarioId = propietarioDAO.insertPropietario(propietario);
        propietario.setId(propietarioId); // Asignar el ID generado
        logger.info("Propietario insertado con éxito. ID: {}", propietarioId);

        // Crea una mascota con su propietario embebido
        Mascota mascota = new Mascota();
        mascota.setNombre("Rex");
        mascota.setRaza("Pastor Alemán");
        mascota.setEdad(4);
        mascota.setUltimaVisita(new Date());
        mascota.setPropietario(propietario); // Asignar el propietario

        // Inserta la mascota en la colección "mascota"
        MascotaDAO mascotaDAO = new MascotaDAO();
        String mascotaId = mascotaDAO.insertMascota(mascota);
        logger.info("Mascota insertada con éxito. ID: {}", mascotaId);

        // Cierra la conexión
        MongoDBConnection.closeConnection();
    }
}