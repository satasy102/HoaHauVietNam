package com.ChanhTin.controller.admin.dantoc;

import com.ChanhTin.model.DanToc;
import com.ChanhTin.service.DanTocService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ThemDanTocServlet",urlPatterns = "/themdantoc")
public class ThemDanTocServlet extends HttpServlet {
    DanTocService danTocService=new DanTocService();
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
            String tenDanToc=request.getParameter("tenDanToc");
            DanToc danToc=new DanToc(tenDanToc);
            request.setAttribute("message", danTocService.them(danToc));
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/dantoc/themdantoc.jsp");
            dispatcher.forward(request, response);
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

        RequestDispatcher dispatcher= request.getRequestDispatcher("views/admin/dantoc/themdantoc.jsp");
        dispatcher.forward(request,response);
    }
}
