package org.example.ejercicio1.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "familiares")
public class Familiar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "parentesco", nullable = false)
    private String parentesco;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    public Familiar() {}

    public Familiar(String nombre, Date fechaNacimiento, String parentesco, Empleado empleado) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.parentesco = parentesco;
        this.empleado = empleado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
