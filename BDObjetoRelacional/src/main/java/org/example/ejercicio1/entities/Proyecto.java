package org.example.ejercicio1.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long proId;
    @Column(name="nombre")
    private String proName;

    @ManyToMany(mappedBy = "proyectos")
    private Set<Empleado> empleados = new HashSet<>();;

    public Proyecto() {}

    public Proyecto(long proId, String proName) {
        this.proId = proId;
        this.proName = proName;
    }

    public long getProId() {
        return proId;
    }

    public void setProId(long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }
}
