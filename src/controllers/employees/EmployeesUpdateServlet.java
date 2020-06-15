package controllers.employees;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Password;
import models.Report;
import models.validators.EmployeeValidator;
import utils.DBUtil;
import utils.EncryptUtil;


@WebServlet("/employees/update")
public class EmployeesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EmployeesUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Employee e = em.find(Employee.class, (Long)(request.getSession().getAttribute("employee_id")));
            Report r = em.find(Report.class, e.getCode());
            Password p = em.find(Password.class, e.getCode());

            //ログインユーザの取得
            HttpSession session = ((HttpServletRequest)request).getSession();
            Employee loginuser = (Employee)session.getAttribute("login_employee");

            // 現在の値と異なる社員番号が入力されていたら
            // 重複チェックを行う指定をする
            Boolean code_duplicate_check = true;
            if(e.getCode().equals(request.getParameter("code"))){
                code_duplicate_check = false;
            } else {
                e.setCode(request.getParameter("code"));
            }


            // パスワード欄に入力があったら
            // パスワードの入力値チェックを行う指定をする
            Boolean password_check_flag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")){
                password_check_flag = false;
            } else {
                p.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("salt")
                                )
                        );
            }


            e.setName_kanzi(request.getParameter("name_kanzi"));
            e.setName_kana(request.getParameter("name_kana"));

            e.setBelongs_num(request.getParameter("belongs_num"));

            e.setAdmin_flg(Integer.parseInt(request.getParameter("admin_flg")));

            Date birthday_at = new Date(System.currentTimeMillis());
            String bd_str = request.getParameter("birthday_at");
            if(bd_str != null && !bd_str.equals("")){
                birthday_at = Date.valueOf(request.getParameter("birthday_at"));
                e.setBirthday_at(birthday_at);
            }

            Date join_at = new Date(System.currentTimeMillis());
            String jd_str = request.getParameter("join_at");
            if(jd_str != null && !jd_str.equals("")){
                join_at = Date.valueOf(request.getParameter("join_at"));
                e.setJoin_at(join_at);
            }

            Date leave_at = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("leave_at");
            if(rd_str != null && !rd_str.equals("")){
                leave_at = Date.valueOf(request.getParameter("leave_at"));
                e.setLeave_at(leave_at);
            }

            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            r.setReport_name(loginuser.getName_kanzi());

            e.setDelete_flg(0);


            List<String> errors = EmployeeValidator.validate(e, p, code_duplicate_check, password_check_flag);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employee", e);
                request.setAttribute("password", p);
                request.setAttribute("report", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");


                request.getSession().removeAttribute("employee_id");

                response.sendRedirect(request.getContextPath() + "/employees/index");
            }

        }
    }

}
