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
import java.util.List;

@WebServlet(name = "XetDuyetServlet", urlPatterns = "/xetduyet")
public class XetDuyetServlet extends HttpServlet {
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

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            ThiSinh thiSinh = thiSinhService.timTheoID(id);

            if(thiSinh.getTrangThaiDuyet().equalsIgnoreCase("Chờ duyệt"))
                thiSinh.setTrangThaiDuyet("Đã duyệt");
            else if(thiSinh.getTrangThaiDuyet().equalsIgnoreCase("Đã duyệt"))
                thiSinh.setTrangThaiDuyet("Bị loại");
            else if(thiSinh.getTrangThaiDuyet().equalsIgnoreCase("Bị loại"))
                thiSinh.setTrangThaiDuyet("Chờ duyệt");

            thiSinhService.sua(id, thiSinh);
            List<ThiSinh> danhSachThiSinh = thiSinhService.danhSach();
            request.setAttribute("danhSachThiSinh", danhSachThiSinh);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/trangchuAdmin.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
