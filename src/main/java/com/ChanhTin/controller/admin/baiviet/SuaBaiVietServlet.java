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

@WebServlet(name = "SuaBaiVietServlet", urlPatterns = "/suabaiviet")
public class SuaBaiVietServlet extends HttpServlet {
    BaiVietService baiVietService=new BaiVietService();
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
            suaBaiViet(request, response);
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
            formSuaBaiViet(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void formSuaBaiViet(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        BaiViet baiViet = baiVietService.timTheoID(id);
        request.setAttribute("baiViet", baiViet);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/baiviet/suabaiviet.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void suaBaiViet(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        int id=Integer.parseInt(request.getParameter("id"));
        String tieuDe=request.getParameter("tieuDe");
        String noiDungTomTat=request.getParameter("noiDungTomTat");
        String noiDung=request.getParameter("noiDung");
        String anhTieuDe=request.getParameter("anhTieuDe");

        BaiViet baiViet=new BaiViet(id,tieuDe,noiDungTomTat,noiDung,anhTieuDe);

        request.setAttribute("message", baiVietService.sua(baiViet.getMaBaiViet(), baiViet));

        request.setAttribute("baiViet", baiViet);

        request.setAttribute("danhSachBaiViet", baiVietService.danhSach());

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/baiviet/suabaiviet.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
