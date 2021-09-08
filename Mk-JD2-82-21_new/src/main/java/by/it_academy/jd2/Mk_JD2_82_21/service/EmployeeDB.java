package by.it_academy.jd2.Mk_JD2_82_21.service;

import by.it_academy.jd2.Mk_JD2_82_21.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDB {
    private static EmployeeDB instance = new EmployeeDB ();

    private static String url = "jdbc:postgresql://localhost:5432/crm";
    private static String username = "postgres";
    private static String password = "kukushenka08071987";

    private EmployeeDB () {

    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException ("Ошибка загрузки драйвера", ex);
        }
    }

    public static long saveEmployee (Employee employee) {
        long id;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO application.employes(\n" +
                    "name, salary)\n" +
                    "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setDouble(2, employee.getSalary ());

                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    generatedKeys.next();
                    id = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return id;
    }

    public static Employee getOneEmployee(long employeeId) {
        Employee employee = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM application.employes WHERE id=?";
            try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setLong (1, employeeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    long id = resultSet.getLong (1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    employee = new Employee (id, name, salary);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employee;
    }

    public static ArrayList<Employee> selectEmployees() {

        ArrayList<Employee> employees = new ArrayList<Employee>();
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement statement = conn.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM application.employes");
            while(resultSet.next()) {
                long id = resultSet.getLong (1);
                String name = resultSet.getString(2);
                double salary = resultSet.getDouble(3);
                Employee employee = new Employee (id, name, salary);
                employees.add(employee);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employees;
    }

    public static EmployeeDB getInstance() {
        return instance;
    }
}
