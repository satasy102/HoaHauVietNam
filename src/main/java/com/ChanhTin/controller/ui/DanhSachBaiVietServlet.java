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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DanhSachBaiVietServlet", urlPatterns = "/tintuc")
public class DanhSachBaiVietServlet extends HttpServlet {
    BaiVietService baiVietService = new BaiVietService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BaiViet> danhSachBaiViet = new ArrayList<>();
        try {
            danhSachBaiViet=baiVietService.danhSach();
            double soTrang=Math.ceil(danhSachBaiViet.size()/5.0);
            request.setAttribute("soTrang",soTrang);
            int idTrang = Integer.parseInt(request.getParameter("idTrang"));
            int soBaiVietMotTrang = 5;
            if (idTrang != 1) {
                idTrang = idTrang - 1;
                idTrang = idTrang * soBaiVietMotTrang + 1;
            }
            danhSachBaiViet = baiVietService.danhSachCoPhanTrang(idTrang, soBaiVietMotTrang);

        } catch (Exception ex) {
            try {
                danhSachBaiViet = baiVietService.danhSachCoPhanTrang(1, 5);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            request.setAttribute("danhSachBaiViet", danhSachBaiViet);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/ui/tintuc.jsp");
            requestDispatcher.forward(request, response);
        }
    }


}
