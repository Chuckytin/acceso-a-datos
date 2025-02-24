package org.example.ejercicio1.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long empId;
    @Column(name="nombre")
    private String empName;
    @ManyToOne
    @JoinColumn(name="dpt", nullable=false)
    private Departamento departamento;
    @ManyToMany
    @JoinTable(
            name = "empleados_proyectos",
            joinColumns = @JoinColumn(name = "empleado"),
            inverseJoinColumns = @JoinColumn(name = "proyecto")
    )
    private Set<Proyecto> proyectos = new HashSet<>();;

    /*    AÑADIDO     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Familiar> familiares;

    public Empleado() {}

    public Empleado(long empId, String empName, Departamento departamento) {
        this.empId = empId;
        this.empName = empName;
        this.departamento = departamento;
        this.proyectos=new HashSet<>();
        familiares = new ArrayList<>();
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }
}
