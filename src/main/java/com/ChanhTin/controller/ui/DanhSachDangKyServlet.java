package com.ChanhTin.controller.ui;

import com.ChanhTin.model.DanToc;
import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.model.TinhThanh;
import com.ChanhTin.service.DanTocService;
import com.ChanhTin.service.ThiSinhService;
import com.ChanhTin.service.TinhThanhService;

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

@WebServlet(name = "danhSachDangKyServlet", urlPatterns = "/danhsachthamgia")
public class DanhSachDangKyServlet extends HttpServlet {
    ThiSinhService thiSinhService = new ThiSinhService();
    DanTocService danTocService = new DanTocService();
    TinhThanhService tinhThanhService = new TinhThanhService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        if (action == null) action = "";
        try {
            List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
            request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

            List<DanToc> danhSachDanToc = danTocService.danhSach();
            request.setAttribute("danhSachDanToc", danhSachDanToc);

            switch (action) {
                case "cungtinhthanh":
                    danhSachThiSinhCungTinhThanh(request, response);
                    break;
                case "cungdantoc":
                    danhSachThiSinhCungDanToc(request, response);
                    break;
                case "timtheoten":
                    danhSachThiSinhTimTheoTen(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        List<ThiSinh> danhSachThiSinh=new ArrayList<>();
        int idTrang=0;
        int soHoSoThiSinhMotTrang = 5;
        try {
            List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
            request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

            List<DanToc> danhSachDanToc = danTocService.danhSach();
            request.setAttribute("danhSachDanToc", danhSachDanToc);

            danhSachThiSinh=thiSinhService.danhSach();
            double soTrang=Math.ceil(danhSachThiSinh.size()/5.0);
            request.setAttribute("soTrang",soTrang);

            idTrang = Integer.parseInt(request.getParameter("idTrang"));

            if (idTrang != 1) {
                idTrang = idTrang - 1;
                idTrang = idTrang * soHoSoThiSinhMotTrang + 1;
            }

        } catch (NumberFormatException | SQLException ex) {
           idTrang=1;
        } finally {
            try {
                danhSachThiSinh = thiSinhService.danhSachCoPhanTrang(idTrang, soHoSoThiSinhMotTrang);
                request.setAttribute("danhSachThiSinh", danhSachThiSinh);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/ui/danhsachdangky.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void danhSachThiSinhCungTinhThanh(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
        TinhThanh tinhThanh = tinhThanhService.timTheoID(id);
        request.setAttribute("tinhThanh", tinhThanh);

        List<ThiSinh> danhSachThiSinh;

        danhSachThiSinh = thiSinhService.timTheoTinhThanh(id);
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        if (danhSachThiSinh.size() == 0)
            request.setAttribute("message", "404");

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/ui/danhsachdangky.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void danhSachThiSinhCungDanToc(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        List<ThiSinh> danhSachThiSinh;

        int id = Integer.parseInt(request.getParameter("id"));

        DanToc danToc = danTocService.timTheoID(id);
        request.setAttribute("danToc", danToc);

        danhSachThiSinh = thiSinhService.timTheoDanToc(id);
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        if (danhSachThiSinh.size() == 0)
            request.setAttribute("message", "404");

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/ui/danhsachdangky.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void danhSachThiSinhTimTheoTen(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String ten = request.getParameter("ten");

        List<ThiSinh> danhSachThiSinh = thiSinhService.timTheoTen(ten);
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        if (danhSachThiSinh.size() == 0)
            request.setAttribute("message", "404");

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/ui/danhsachdangky.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
