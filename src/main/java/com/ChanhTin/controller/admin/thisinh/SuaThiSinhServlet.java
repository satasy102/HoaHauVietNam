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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SuaThiSinhServlet", urlPatterns = "/suathisinh")
public class SuaThiSinhServlet extends HttpServlet {
    String username;
    TinhThanhService tinhThanhService = new TinhThanhService();
    ThiSinhService thiSinhService = new ThiSinhService();
    DanTocService danTocService = new DanTocService();
    TrinhDoVanHoaService trinhDoVanHoaService = new TrinhDoVanHoaService();

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
        try {
            suaThiSinh(request, response);
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

        try {
            formSuaThiSinh(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void formSuaThiSinh(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        ThiSinh thiSinh = thiSinhService.timTheoID(id);
        request.setAttribute("thiSinh", thiSinh);

        List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
        request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

        List<DanToc> danhSachDanToc = danTocService.danhSach();
        request.setAttribute("danhSachDanToc", danhSachDanToc);

        List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();
        request.setAttribute("danhSachTDVH", danhSachTDVH);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/suathisinh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void suaThiSinh(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ThiSinh thiSinh = getThiSinh(request);

        request.setAttribute("message", thiSinhService.sua(thiSinh.getIdThiSinh(), thiSinh));

        request.setAttribute("thiSinh", thiSinh);

        List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
        request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

        List<DanToc> danhSachDanToc = danTocService.danhSach();
        request.setAttribute("danhSachDanToc", danhSachDanToc);

        List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();
        request.setAttribute("danhSachTDVH", danhSachTDVH);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/suathisinh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private ThiSinh getThiSinh(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        Date ngaySinh = Date.valueOf(request.getParameter("ngaySinh"));
        String diaChiCuTru = request.getParameter("diaChiCuTru");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
        String cmt = request.getParameter("cmt");
        String ngheNghiep = request.getParameter("ngheNghiep");
        int maTrinhDoVH = Integer.parseInt(request.getParameter("trinhDoVH"));
        int maDanToc = Integer.parseInt(request.getParameter("danToc"));
        String donViCongTac = request.getParameter("donViCongTac");
        float chieuCao = Float.parseFloat(request.getParameter("chieuCao"));
        float canNang = Float.parseFloat(request.getParameter("canNang"));
        String nangKieuKhac = request.getParameter("nangKieuKhac");
        String anhCaNhan = request.getParameter("anhCaNhan");
        int maTinh = Integer.parseInt(request.getParameter("daiDienTinhThanh"));
        String trangThaiDuyet = request.getParameter("trangThaiDuyet");

        ThiSinh thiSinh = new ThiSinh(id, ten, ngaySinh, diaChiCuTru, sdt, email, cmt, ngheNghiep, maTrinhDoVH, maDanToc,
                donViCongTac, chieuCao, canNang, nangKieuKhac, anhCaNhan, maTinh);

        thiSinh.setTrangThaiDuyet(trangThaiDuyet);
        thiSinhService.setTinhThanhDanTocTDVH(maTrinhDoVH, maDanToc, maTinh, thiSinh);
        return thiSinh;
    }
}
