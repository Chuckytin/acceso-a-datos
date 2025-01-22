package org.example.entities;

import jakarta.persistence.*;

@Entity //Convierte la clase en una Entidad
@Table(name = "employees")
public class Employee {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //Generación automática del ID
    @Column(name = "empId")
    private int empId;

    @Column(name = "empName")
    private String empName;

    // Constructores
    public Employee(int empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public Employee(String empName) {
        this.empName = empName;
    }

    public Employee() {}

    // GETTERS & SETTERS
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}