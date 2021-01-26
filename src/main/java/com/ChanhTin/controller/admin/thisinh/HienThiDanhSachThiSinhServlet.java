package com.ChanhTin.controller.admin.thisinh;

import com.ChanhTin.model.DanToc;
import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.model.TinhThanh;
import com.ChanhTin.model.TrinhDoVanHoa;
import com.ChanhTin.service.DanTocService;
import com.ChanhTin.service.ThiSinhService;
import com.ChanhTin.service.TinhThanhService;
import com.ChanhTin.service.TrinhDoVanHoaService;

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

@WebServlet(name = "HienThiDanhSachServlet", urlPatterns = "/admin")
public class HienThiDanhSachThiSinhServlet extends HttpServlet {
    String username;
    ThiSinhService thiSinhService = new ThiSinhService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty() || !username.equalsIgnoreCase("admin")) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);

        String action = request.getParameter("action");

        if (action == null) action = "";
        try {
            switch (action) {
                case "cungtinhthanh":
                    danhSachThiSinhCungTinhThanh(request, response);
                    break;
                case "cungdantoc":
                    danhSachThiSinhCungDanToc(request, response);
                    break;
                case "cungtdvh":
                    danhSachThiSinhCungTrinhDoVanHoa(request, response);
                    break;
                case "timtheoten":
                    danhSachThiSinhTimTheoTen(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        String action = request.getParameter("action");

        if (action == null) action = "";
        try {
            switch (action) {
                case "daduyet":
                    danhSachThiSinhDaDuyet(request, response);
                    break;
                case "chuaduyet":
                    danhSachThiSinhChuaDuyet(request, response);
                    break;
                case "biloai":
                    danhSachThiSinhBiLoai(request, response);
                    break;
                case "cungtinhthanh":
                    formDanhSachThiSinhCungTinhThanh(request, response);
                    break;
                case "cungdantoc":
                    formDanhSachThiSinhCungDanToc(request, response);
                    break;
                case "cungtdvh":
                    formDanhSachThiSinhCungTDVH(request, response);
                    break;
                default:
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        request.setAttribute("message", thiSinhService.xoa(id));
                        danhSachThiSinh(request, response);
                    } catch (Exception ex) {
                        danhSachThiSinh(request, response);
                    }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void danhSachThiSinh(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        List<ThiSinh> danhSachThiSinh;
        danhSachThiSinh = thiSinhService.danhSach();
        double soTrang = Math.ceil(danhSachThiSinh.size() / 5.0);
        request.setAttribute("soTrang", soTrang);
        int idTrang = 0;
        int soHoSoThiSinhMotTrang = 5;
        try {
            idTrang = Integer.parseInt(request.getParameter("idTrang"));
            if (idTrang != 1) {
                idTrang = idTrang - 1;
                idTrang = idTrang * soHoSoThiSinhMotTrang + 1;
            }

        } catch (NumberFormatException e) {
            idTrang = 1;
        } finally {
            try {
                danhSachThiSinh = thiSinhService.danhSachCoPhanTrang(idTrang, soHoSoThiSinhMotTrang);
                request.setAttribute("danhSachThiSinh", danhSachThiSinh);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/trangchuAdmin.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void danhSachThiSinhDaDuyet(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        List<ThiSinh> danhSachThiSinh;

        danhSachThiSinh = thiSinhService.danhSachDaDuyet();
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/daduyet.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void danhSachThiSinhChuaDuyet(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        List<ThiSinh> danhSachThiSinh;

        danhSachThiSinh = thiSinhService.danhSachChuaDuyet();
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/chuaduyet.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void danhSachThiSinhBiLoai(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        List<ThiSinh> danhSachThiSinh;

        danhSachThiSinh = thiSinhService.danhSachBiLoai();
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/biloai.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void formDanhSachThiSinhCungTinhThanh(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        TinhThanhService tinhThanhService = new TinhThanhService();
        List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();

        request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungtinhthanh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void danhSachThiSinhCungTinhThanh(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        TinhThanhService tinhThanhService = new TinhThanhService();
        List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
        request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

        int id = Integer.parseInt(request.getParameter("id"));
        TinhThanh tinhThanh = tinhThanhService.timTheoID(id);
        request.setAttribute("tinhThanh", tinhThanh);

        List<ThiSinh> danhSachThiSinh;

        danhSachThiSinh = thiSinhService.timTheoTinhThanh(id);
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);
        if (danhSachThiSinh.size() == 0) request.setAttribute("message", "Không tìm thấy");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungtinhthanh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void formDanhSachThiSinhCungDanToc(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        DanTocService danTocService = new DanTocService();
        List<DanToc> danhSachDanToc = danTocService.danhSach();

        request.setAttribute("danhSachDanToc", danhSachDanToc);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungdantoc.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void danhSachThiSinhCungDanToc(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        DanTocService danTocService = new DanTocService();
        List<DanToc> danhSachDanToc = danTocService.danhSach();

        request.setAttribute("danhSachDanToc", danhSachDanToc);

        List<ThiSinh> danhSachThiSinh;

        int id = Integer.parseInt(request.getParameter("id"));

        DanToc danToc = danTocService.timTheoID(id);
        request.setAttribute("danToc", danToc);

        danhSachThiSinh = thiSinhService.timTheoDanToc(id);
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);

        if (danhSachThiSinh.size() == 0) request.setAttribute("message", "Không tìm thấy");

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungdantoc.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void formDanhSachThiSinhCungTDVH(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        TrinhDoVanHoaService trinhDoVanHoaService = new TrinhDoVanHoaService();
        List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();

        request.setAttribute("danhSachTDVH", danhSachTDVH);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungtdvh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void danhSachThiSinhCungTrinhDoVanHoa(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        TrinhDoVanHoaService trinhDoVanHoaService = new TrinhDoVanHoaService();
        List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();

        request.setAttribute("danhSachTDVH", danhSachTDVH);

        List<ThiSinh> danhSachThiSinh;

        int id = Integer.parseInt(request.getParameter("id"));

        TrinhDoVanHoa trinhDoVanHoa = trinhDoVanHoaService.timTheoID(id);
        request.setAttribute("trinhDoVanHoa", trinhDoVanHoa);

        danhSachThiSinh = thiSinhService.timTheoTrinhDoVH(id);
        request.setAttribute("danhSachThiSinh", danhSachThiSinh);
        if (danhSachThiSinh.size() == 0) request.setAttribute("message", "Không tìm thấy");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungtdvh.jsp");
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

        if (danhSachThiSinh.size() == 0) request.setAttribute("message", "Không tìm thấy");

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/timthisinh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


}
