package com.ChanhTin.controller.ui;

import com.ChanhTin.model.BaiViet;
import com.ChanhTin.service.BaiVietService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ChiTietBaiVietServlet", urlPatterns = "/chitietbaiviet")
public class ChiTietBaiVietServlet extends HttpServlet {
    BaiVietService baiVietService=new BaiVietService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        try {
            BaiViet baiViet=baiVietService.timTheoID(id);
            request.setAttribute("baiViet",baiViet);
            List<BaiViet> danhSachBaiViet=baiVietService.danhSachBaiVietMoiNhat();
            request.setAttribute("danhSachBaiViet",danhSachBaiViet);
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("views/ui/chitietbaiviet.jsp");
            requestDispatcher.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
