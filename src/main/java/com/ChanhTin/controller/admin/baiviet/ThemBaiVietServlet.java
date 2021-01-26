package com.ChanhTin.controller.admin.baiviet;

import com.ChanhTin.model.BaiViet;
import com.ChanhTin.service.BaiVietService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ThemBaiVietServlet", urlPatterns = "/thembaiviet")
public class ThemBaiVietServlet extends HttpServlet {
    BaiVietService baiVietService=new BaiVietService();
    String username;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);
        try {
            String anhTieuDe=request.getParameter("anhTieuDe");
            String tieuDe=request.getParameter("tieuDe");
            String noiDungTomTat=request.getParameter("noiDungTomTat");
            String noiDung=request.getParameter("summernote");

            BaiViet baiViet = new BaiViet(tieuDe,noiDungTomTat,noiDung,anhTieuDe);
            request.setAttribute("message", baiVietService.them(baiViet));
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/baiviet/thembaiviet.jsp");
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
        if (username == null || username.isEmpty()) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);

        RequestDispatcher dispatcher= request.getRequestDispatcher("views/admin/baiviet/thembaiviet.jsp");
        dispatcher.forward(request,response);
    }
}
