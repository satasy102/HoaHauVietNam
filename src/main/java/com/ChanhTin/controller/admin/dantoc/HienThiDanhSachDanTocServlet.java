package com.ChanhTin.controller.admin.dantoc;

import com.ChanhTin.model.DanToc;
import com.ChanhTin.service.DanTocService;

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

@WebServlet(name = "HienThiDanhSachDanTocServlet",urlPatterns = "/showdantoc")
public class HienThiDanhSachDanTocServlet extends HttpServlet {
    String username;
    DanTocService danTocService=new DanTocService();
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
            danhSachDanToc(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void danhSachDanToc(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<DanToc> danhSachDanToc;
        danhSachDanToc = danTocService.danhSach();
        double soTrang=Math.ceil(danhSachDanToc.size()/10.0);
        request.setAttribute("soTrang",soTrang);
        int idTrang=0;
        int soDanTocMotTrang = 10;
        try {
            idTrang= Integer.parseInt(request.getParameter("idTrang"));
            if (idTrang != 1) {
                idTrang = idTrang - 1;
                idTrang = idTrang * soDanTocMotTrang + 1;
            }

        } catch (NumberFormatException e){
            idTrang=1;
        }
        finally {
            danhSachDanToc=danTocService.danhSachCoPhanTrang(idTrang,soDanTocMotTrang);
            request.setAttribute("danhSachDanToc", danhSachDanToc);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/dantoc/dantoc.jsp");
            dispatcher.forward(request, response);
        }
    }

}
