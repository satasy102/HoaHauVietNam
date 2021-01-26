package com.ChanhTin.controller.login;

import com.ChanhTin.model.User;
import com.ChanhTin.service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LoginService loginService = new LoginService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String userName= request.getParameter("username");
        String password = request.getParameter("pass");

        try {
            User user= loginService.timTheoUserNamePassword(userName, password);
            if (user!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUserName());
                session.setAttribute("user", user);
                if(user.isAdmin())
                response.sendRedirect("admin");
                else response.sendRedirect("showbaiviet");
            }else {
                //Dang nhap khong thanh cong
                request.setAttribute("message", "Username, password Không đúng!");
                this.showLogin(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");
        if (action == null)
            action = "login";
        switch (action) {
            case "login":
                showLogin(request, response);
                break;
            case "logout":
                showLogout(request, response);
                break;

        }
    }

    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/login/login.jsp");
        rd.forward(request, response);
    }

    private void showLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //XOA HET CAC SESSION DE TRA VE LOGIN
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("views/login/login.jsp");
        rd.forward(request, response);
    }
}
