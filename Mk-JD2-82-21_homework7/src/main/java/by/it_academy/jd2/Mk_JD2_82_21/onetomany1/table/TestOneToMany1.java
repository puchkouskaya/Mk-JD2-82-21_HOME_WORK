package by.it_academy.jd2.Mk_JD2_82_21.onetomany1.table;

import by.it_academy.jd2.Mk_JD2_82_21.onetomany1.table.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.onetomany1.table.model.Employee;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestOneToMany1 {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = HibernateUtilOneToMany1.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Employee employee = new Employee("Манька", "8888.00");
        sessionOne.save(employee);

        Employee employee1 = new Employee("Санька", "1088.00");
        sessionOne.save(employee1);

        Employee employee2 = new Employee("Ванька", "1088.00");
        sessionOne.save(employee2);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);

        Department department = new Department("Зорька", employees);
        sessionOne.save(department);

        sessionOne.getTransaction().commit();
        
        HibernateUtilOneToMany1.shutdown();
    }
}
