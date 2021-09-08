package by.it_academy.jd2.Mk_JD2_82_21.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.service.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    private static final String EMPLOYEE_PARAM_NAME = "name";
    private static final String SALARY_PARAM_NAME = "salary";
    private static final String ID_PARAM_NAME = "id_employee";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(EMPLOYEE_PARAM_NAME);
        double salary = Double.parseDouble(req.getParameter(SALARY_PARAM_NAME));
        Employee employee = new Employee (name, salary);

        long id = EmployeeDB.getInstance().saveEmployee(employee);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/views/newEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(ID_PARAM_NAME));

        Employee employee = EmployeeDB.getInstance().getOneEmployee(id);
        req.setAttribute("employee", employee);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }


}
