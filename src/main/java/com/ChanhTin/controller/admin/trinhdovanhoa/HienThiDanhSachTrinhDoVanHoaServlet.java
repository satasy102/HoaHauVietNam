package com.ChanhTin.controller.admin.trinhdovanhoa;

import com.ChanhTin.model.TrinhDoVanHoa;
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

@WebServlet(name = "HienThiDanhSachTrinhDoVanHoaServlet", urlPatterns = "/showTDVH")
public class HienThiDanhSachTrinhDoVanHoaServlet extends HttpServlet {
    String username;
    TrinhDoVanHoaService trinhDoVanHoaService = new TrinhDoVanHoaService();

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
            danhSachTDVH(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void danhSachTDVH(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        List<TrinhDoVanHoa> danhSachTrinhDoVH;
        danhSachTrinhDoVH = trinhDoVanHoaService.danhSach();
        double soTrang=Math.ceil(danhSachTrinhDoVH.size()/5.0);
        request.setAttribute("soTrang",soTrang);
        int idTrang=0;
        int soTDVHMotTrang = 5;
        try {
            idTrang= Integer.parseInt(request.getParameter("idTrang"));
            if (idTrang != 1) {
                idTrang = idTrang - 1;
                idTrang = idTrang * soTDVHMotTrang + 1;
            }

        } catch (NumberFormatException e){
            idTrang=1;
        }
        finally {
            danhSachTrinhDoVH=trinhDoVanHoaService.danhSachCoPhanTrang(idTrang,soTDVHMotTrang);
            request.setAttribute("danhSachTrinhDoVH", danhSachTrinhDoVH);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/trinhdovanhoa/trinhdovh.jsp");
            dispatcher.forward(request, response);
        }
    }
}
