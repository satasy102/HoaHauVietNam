package com.ChanhTin.controller.admin.trinhdovanhoa;

import com.ChanhTin.model.TrinhDoVanHoa;
import com.ChanhTin.service.TrinhDoVanHoaService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ThemTrinhDoVHServlet", urlPatterns = "/themtdvh")
public class ThemTrinhDoVHServlet extends HttpServlet {
    String username;
    TrinhDoVanHoaService trinhDoVanHoaService=new TrinhDoVanHoaService();
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
            String tenTrinhDoVH=request.getParameter("tenTrinhDoVH");
            TrinhDoVanHoa trinhDoVanHoa = new TrinhDoVanHoa(tenTrinhDoVH);
            request.setAttribute("message",trinhDoVanHoaService.them(trinhDoVanHoa));
            RequestDispatcher dispatcher= request.getRequestDispatcher("views/admin/trinhdovanhoa/themtdvh.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);
        RequestDispatcher dispatcher= request.getRequestDispatcher("views/admin/trinhdovanhoa/themtdvh.jsp");
        dispatcher.forward(request,response);
    }
}
