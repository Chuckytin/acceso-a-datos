package com.veterinaria.model;

import lombok.Data;

import java.util.Date;

@Data
public class Mascota {

    private Long id;

    private String nombre;

    private String raza;

    private Integer edad;

    private Propietario propietario;

    private Date ultimaVisita;

}