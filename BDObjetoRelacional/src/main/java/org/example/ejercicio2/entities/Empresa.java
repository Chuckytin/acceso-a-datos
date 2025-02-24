package org.example.ejercicio2.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @Column(name = "cif", unique = true, nullable = false)
    private String cif;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "alumnos_empresas",
            joinColumns = @JoinColumn(name = "cif"),
            inverseJoinColumns = @JoinColumn(name = "id_alumno")
    )
    private Set<Alumno> alumnos = new HashSet<>();

    // Getters y Setters
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Empresa {" +
                "cif = '" + cif + '\'' +
                ", nombre = '" + nombre + '\'' +
                '}';
    }
}