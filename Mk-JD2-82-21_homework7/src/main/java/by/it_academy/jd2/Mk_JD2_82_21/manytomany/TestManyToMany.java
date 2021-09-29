package by.it_academy.jd2.Mk_JD2_82_21.manytomany;

import by.it_academy.jd2.Mk_JD2_82_21.manytomany.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.manytomany.model.Employee;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestManyToMany {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = HibernateUtilManyToMany.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Department department = new Department("Зорька");
        Department department1 = new Department("Борька");

        Employee employee = new Employee("Манька", "8888.00");
        Employee employee1 = new Employee("Санька", "1088.00");
        Employee employee2 = new Employee("Ванька", "1088.00");

        Set<Department> departments = new HashSet<>();
        departments.add(department);
        departments.add(department1);

        employee.setDepartment(departments);
        employee1.setDepartment(departments);
        employee2.setDepartment(departments);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);

        department.setEmployees(employees);
        department1.setEmployees(employees);

        sessionOne.save(department);
        sessionOne.save(department1);

        sessionOne.save(employee);
        sessionOne.save(employee1);
        sessionOne.save(employee2);

        sessionOne.getTransaction().commit();

        HibernateUtilManyToMany.shutdown();
    }
}
