package by.it_academy.jd2.Mk_JD2_82_21.onetoone.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String salary;

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;


    public Employee() {

    }

    public Employee(String name, String salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
