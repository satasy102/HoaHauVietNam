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

@WebServlet(name = "SuaDanTocServlet",urlPatterns = "/suadantoc")
public class SuaDanTocServlet extends HttpServlet {
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
            suaDanToc(request, response);
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
        try {
            formSuaDanToc(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void formSuaDanToc(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        DanToc danToc = danTocService.timTheoID(id);
        request.setAttribute("danToc", danToc);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/dantoc/suadantoc.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void suaDanToc(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        int id=Integer.parseInt(request.getParameter("id"));
        String tenDanToc=request.getParameter("tenDanToc");

        DanToc danToc=new DanToc(id,tenDanToc);

        request.setAttribute("message", danTocService.sua(danToc.getIdDanToc(), danToc));

        request.setAttribute("danToc", danToc);

        request.setAttribute("danhSachDanToc", danTocService.danhSach());

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/dantoc/suadantoc.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
