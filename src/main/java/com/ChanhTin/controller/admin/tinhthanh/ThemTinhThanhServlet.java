package com.ChanhTin.controller.admin.tinhthanh;

import com.ChanhTin.model.TinhThanh;
import com.ChanhTin.service.TinhThanhService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ThemTinhThanhServlet",urlPatterns = "/themtinhthanh")
public class ThemTinhThanhServlet extends HttpServlet {
    TinhThanhService tinhThanhService=new TinhThanhService();
    String username;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()||!username.equalsIgnoreCase("admin")) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);

        try {
            String tenTinh=request.getParameter("tenTinh");
            TinhThanh tinhThanh=new TinhThanh(tenTinh);
            request.setAttribute("message", tinhThanhService.them(tinhThanh));
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/tinhthanh/themtinhthanh.jsp");
            dispatcher.forward(request, response);;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()||!username.equalsIgnoreCase("admin")) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);
        RequestDispatcher dispatcher= request.getRequestDispatcher("views/admin/tinhthanh/themtinhthanh.jsp");
        dispatcher.forward(request,response);
    }




}
