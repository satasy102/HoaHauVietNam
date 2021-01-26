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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ThemThiSinhServlet", urlPatterns = "/themthisinh")
public class ThemThiSinhServlet extends HttpServlet {
    String username;
    TinhThanhService tinhThanhService = new TinhThanhService();
    ThiSinhService thiSinhService = new ThiSinhService();
    DanTocService danTocService=new DanTocService();
    TrinhDoVanHoaService trinhDoVanHoaService=new TrinhDoVanHoaService();

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
        createThiSinh(request, response);
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
            formthemthisinh(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void formthemthisinh(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
        request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

        List<DanToc> danhSachDanToc = danTocService.danhSach();
        request.setAttribute("danhSachDanToc", danhSachDanToc);

        List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();
        request.setAttribute("danhSachTDVH", danhSachTDVH);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/themthisinh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createThiSinh(HttpServletRequest request, HttpServletResponse response) {
        try {
            ThiSinh thiSinh = getThiSinh(request);

            ArrayList<String> message=thiSinhService.them(thiSinh);
            request.setAttribute("message", message);

            if(!message.get(0).equals("200"))
                request.setAttribute("thiSinh",thiSinh);

            List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
            request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

            List<DanToc> danhSachDanToc = danTocService.danhSach();
            request.setAttribute("danhSachDanToc", danhSachDanToc);

            List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();
            request.setAttribute("danhSachTDVH", danhSachTDVH);

            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/themthisinh.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    private ThiSinh getThiSinh(HttpServletRequest request) throws SQLException {

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

        ThiSinh thiSinh=new ThiSinh(ten, ngaySinh, diaChiCuTru, sdt, email, cmt, ngheNghiep, maTrinhDoVH, maDanToc,
                donViCongTac, chieuCao, canNang, nangKieuKhac, anhCaNhan, maTinh);

        thiSinhService.setTinhThanhDanTocTDVH(maTrinhDoVH,maDanToc,maTinh,thiSinh);

        return thiSinh;
    }

}
