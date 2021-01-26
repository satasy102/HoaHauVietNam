package com.ChanhTin.controller.admin.thisinh;

import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.service.ThiSinhService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "HienThiThongTinThiSinhServlet", urlPatterns = "/thongtinthisinh")
public class HienThiThongTinThiSinhServlet extends HttpServlet {
    String username;
    ThiSinhService thiSinhService=new ThiSinhService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        int id = Integer.parseInt(request.getParameter("id"));
        ThiSinh thiSinh = null;
        try {
            thiSinh = thiSinhService.timTheoID(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("thiSinh", thiSinh);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/thongtinthisinh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
