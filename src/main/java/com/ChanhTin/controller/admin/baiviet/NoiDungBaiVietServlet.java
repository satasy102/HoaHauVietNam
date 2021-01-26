package com.ChanhTin.controller.admin.baiviet;

import com.ChanhTin.dao.BaiVietDAO;
import com.ChanhTin.model.BaiViet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "NoiDungBaiVietServlet",urlPatterns = "/noidungbaiviet")
public class NoiDungBaiVietServlet extends HttpServlet {
    BaiVietDAO baiVietDAO=new BaiVietDAO();
    String username;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);
        try {
            BaiViet baiViet= baiVietDAO.findById(id);
            request.setAttribute("baiViet", baiViet);
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("views/admin/baiviet/noidungbaiviet.jsp");
            requestDispatcher.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
