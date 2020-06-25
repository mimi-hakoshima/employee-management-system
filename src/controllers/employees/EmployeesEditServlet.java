package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BelongsNum;
import models.Employee;
import utils.DBUtil;


@WebServlet("/employees/edit")
public class EmployeesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EmployeesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee e = em.find(Employee.class, Long.parseLong(request.getParameter("id")));
        //BelongsNum b = em.find(BelongsNum.class, e.getBelongs_num());
        //Report r =em.find(Report.class, e.getCode());

        List<BelongsNum> belongsnum = em.createNamedQuery("getAllBelongsNum", BelongsNum.class).getResultList();

        em.close();

        request.setAttribute("belongsnum", belongsnum);
        request.setAttribute("employee", e);
        //request.setAttribute("belongsnum", b);
        //request.setAttribute("report", r);

        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("employee_id", e.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
        rd.forward(request, response);

    }

}
