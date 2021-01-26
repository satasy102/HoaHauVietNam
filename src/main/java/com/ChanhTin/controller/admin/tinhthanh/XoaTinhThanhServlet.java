package com.ChanhTin.controller.admin.tinhthanh;

import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.model.TinhThanh;
import com.ChanhTin.service.ThiSinhService;
import com.ChanhTin.service.TinhThanhService;

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

@WebServlet(name = "XoaTinhThanhServlet", urlPatterns = "/xoatinhthanh")
public class XoaTinhThanhServlet extends HttpServlet {
    ThiSinhService thiSinhService = new ThiSinhService();
    TinhThanhService tinhThanhService = new TinhThanhService();
    String username;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty() || !username.equalsIgnoreCase("admin")) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            List<ThiSinh> danhSachThiSinh = thiSinhService.timTheoTinhThanh(id);
            if (danhSachThiSinh.isEmpty()) {
                request.setAttribute("message", tinhThanhService.xoa(id));
                List<TinhThanh> danhSachTinhThanh;
                danhSachTinhThanh = tinhThanhService.danhSach();
                double soTrang=Math.ceil(danhSachTinhThanh.size()/10.0);
                request.setAttribute("soTrang",soTrang);
                int idTrang=0;
                int soTinhThanhMotTrang = 10;
                try {
                    idTrang= Integer.parseInt(request.getParameter("idTrang"));
                    if (idTrang != 1) {
                        idTrang = idTrang - 1;
                        idTrang = idTrang * soTinhThanhMotTrang + 1;
                    }

                } catch (Exception e){
                    idTrang=1;
                }
                finally {
                    danhSachTinhThanh=tinhThanhService.danhSachCoPhanTrang(idTrang,soTinhThanhMotTrang);
                    request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/tinhthanh/tinhthanh.jsp");
                    dispatcher.forward(request, response);
                }
            } else {

                request.setAttribute("danhSachThiSinh", danhSachThiSinh);

                request.setAttribute("message", "Xin hãy xóa các thí sinh có trong Tỉnh thành này trước.");

                TinhThanh tinhThanh = tinhThanhService.timTheoID(id);
                request.setAttribute("tinhThanh", tinhThanh);

                List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();

                request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

                RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungtinhthanh.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
