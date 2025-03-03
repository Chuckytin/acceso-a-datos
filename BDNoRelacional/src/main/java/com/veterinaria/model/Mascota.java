package com.veterinaria.model;

import lombok.Data;
import java.util.Date;

@Data
public class Mascota {

    private String id;
    private String nombre;
    private String raza;
    private Integer edad;
    private Date ultimaVisita;
    private Propietario propietario; // Propietario embebido
    //private String propietarioId;
}