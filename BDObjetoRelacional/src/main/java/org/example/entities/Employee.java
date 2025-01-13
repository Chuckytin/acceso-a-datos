package org.example.entities;

public class Employee {

    private int empId;

    private String empName;

    //Constructores
    public Employee(int empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public Employee() {}

    //GETTERS & SETTERS
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
