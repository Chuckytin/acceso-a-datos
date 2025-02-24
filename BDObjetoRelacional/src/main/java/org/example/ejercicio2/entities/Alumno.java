package org.example.ejercicio2.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @ManyToMany(mappedBy = "alumnos")
    private Set<Empresa> empresas = new HashSet<>();

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alumno {")
                .append("id = ").append(id)
                .append(", nombre = '").append(nombre).append('\'')
                .append(", apellidos = '").append(apellidos).append('\'')
                .append(", empresa = [");

        if (!empresas.isEmpty()) {
            for (Empresa empresa : empresas) {
                sb.append(empresa.getNombre()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Elimina la última coma y espacio
        }

        sb.append("]}");
        return sb.toString();
    }
}