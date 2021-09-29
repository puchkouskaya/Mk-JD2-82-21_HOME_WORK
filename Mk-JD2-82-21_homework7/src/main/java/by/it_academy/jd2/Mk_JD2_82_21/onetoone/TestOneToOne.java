package by.it_academy.jd2.Mk_JD2_82_21.onetoone;

import by.it_academy.jd2.Mk_JD2_82_21.onetoone.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.onetoone.model.Employee;
import org.hibernate.Session;

import java.sql.SQLException;

public class TestOneToOne {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Department department = new Department("Зорька");

        sessionOne.save(department);

        Employee employee = new Employee();

        employee.setName("Манька");
        employee.setSalary("888.8");
        employee.setDepartment(department);

        sessionOne.save(employee);

        Department department1 = new Department("Колхоз 777");

        sessionOne.save(department1);

        Employee employee1 = sessionOne.get(Employee.class, 2L);

        employee.setSalary("9999.0");
        employee.setDepartment(department);

        sessionOne.update(employee1);


        sessionOne.getTransaction().commit();


        HibernateUtil.shutdown();
    }
}
