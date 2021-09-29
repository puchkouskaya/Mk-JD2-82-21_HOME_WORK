package by.it_academy.jd2.Mk_JD2_82_21.manytoone;

import by.it_academy.jd2.Mk_JD2_82_21.manytoone.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.manytoone.model.Employee;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestManyToOne {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = HibernateUtilManyToOne.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Department department = new Department();
        department.setName("Зорька");

        Employee employee = new Employee("Манька", "8888.00", department);
        sessionOne.save(employee);

        Employee employee1 = new Employee("Санька", "1088.00", department);
        sessionOne.save(employee1);

        Employee employee2 = new Employee("Ванька", "1088.00", department);
        sessionOne.save(employee2);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);

        department.setEmployees(employees);

        sessionOne.save(department);

        sessionOne.getTransaction().commit();

        HibernateUtilManyToOne.shutdown();
    }
}
