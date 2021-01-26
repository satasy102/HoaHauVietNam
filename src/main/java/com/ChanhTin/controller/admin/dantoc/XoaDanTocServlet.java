package com.ChanhTin.controller.admin.dantoc;

import com.ChanhTin.model.DanToc;
import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.service.DanTocService;
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

@WebServlet(name = "XoaDanTocServlet", urlPatterns = "/xoadantoc")
public class XoaDanTocServlet extends HttpServlet {
    String username;
    DanTocService danTocService = new DanTocService();
    ThiSinhService thiSinhService = new ThiSinhService();

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
            List<ThiSinh> danhSachThiSinh = thiSinhService.timTheoDanToc(id);
            if (danhSachThiSinh.isEmpty()) {
                request.setAttribute("message", danTocService.xoa(id));
                List<DanToc> danhSachDanToc;
                danhSachDanToc = danTocService.danhSach();
                double soTrang = Math.ceil(danhSachDanToc.size() / 10.0);
                request.setAttribute("soTrang", soTrang);
                int idTrang = 0;
                int soDanTocMotTrang = 10;
                try {
                    idTrang = Integer.parseInt(request.getParameter("idTrang"));
                    if (idTrang != 1) {
                        idTrang = idTrang - 1;
                        idTrang = idTrang * soDanTocMotTrang + 1;
                    }

                } catch (NumberFormatException e) {
                    idTrang = 1;
                } finally {
                    danhSachDanToc = danTocService.danhSachCoPhanTrang(idTrang, soDanTocMotTrang);
                    request.setAttribute("danhSachDanToc", danhSachDanToc);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/dantoc/dantoc.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("danhSachThiSinh", danhSachThiSinh);

                request.setAttribute("message", "Xin hãy xóa các thí sinh có trong Dân tộc này trước.");

                List<DanToc> danhSachDanToc = danTocService.danhSach();
                request.setAttribute("danhSachDanToc", danhSachDanToc);

                DanToc danToc = danTocService.timTheoID(id);
                request.setAttribute("danToc", danToc);

                danhSachThiSinh = thiSinhService.timTheoDanToc(id);
                request.setAttribute("danhSachThiSinh", danhSachThiSinh);


                RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungdantoc.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
