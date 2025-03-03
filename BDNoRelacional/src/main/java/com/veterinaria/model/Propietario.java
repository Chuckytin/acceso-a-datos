package com.veterinaria.model;

import lombok.Data;
import java.util.List;

@Data
public class Propietario {

    private String id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private List<Mascota> mascotas; // Lista de mascotas del propietario

}