package com.ChanhTin.controller.ui;

import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.service.ThiSinhService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DanhSachDaDuyetServlet", urlPatterns = "/danhsachdaduyet")
public class DanhSachDaDuyetServlet extends HttpServlet {
    ThiSinhService thiSinhService = new ThiSinhService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        try {
            danhSachThiSinh(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void danhSachThiSinh(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        List<ThiSinh> danhSachThiSinh = thiSinhService.danhSachDaDuyet();
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/ui/danhsachdaduyet.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
