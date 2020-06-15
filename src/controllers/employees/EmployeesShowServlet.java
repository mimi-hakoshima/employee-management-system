package controllers.employees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BelongsNum;
import models.Employee;
import models.Report;
import utils.DBUtil;


@WebServlet("/employees/show")
public class EmployeesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EmployeesShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee e = em.find(Employee.class, Long.parseLong(request.getParameter("id")));
        BelongsNum b = em.find(BelongsNum.class, e.getBelongs_num());
        Report r = em.find(Report.class, e.getCode());



        em.close();

        request.setAttribute("employee", e);
        request.setAttribute("belongsNum", b);
        request.setAttribute("report", r);



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
        rd.forward(request, response);

    }

}
