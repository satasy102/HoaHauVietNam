package com.ChanhTin.controller.admin.baiviet;

import com.ChanhTin.model.BaiViet;
import com.ChanhTin.model.TrinhDoVanHoa;
import com.ChanhTin.service.BaiVietService;

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

@WebServlet(name = "BaiVietServlet", urlPatterns = "/showbaiviet")
public class HienThiBaiVietServlet extends HttpServlet {
    BaiVietService baiVietService=new BaiVietService();
    String username;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            int id=Integer.parseInt(request.getParameter("id"));
            request.setAttribute("message",baiVietService.xoa(id));
            danhSachBaiViet(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e){
            try {
                danhSachBaiViet(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void danhSachBaiViet(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<BaiViet> danhSachBaiViet;
        danhSachBaiViet = baiVietService.danhSach();
        double soTrang=Math.ceil(danhSachBaiViet.size()/5.0);
        request.setAttribute("soTrang",soTrang);
        int idTrang=0;
        int soBaiVietMotTrang = 5;
        try {
            idTrang= Integer.parseInt(request.getParameter("idTrang"));
            if (idTrang != 1) {
                idTrang = idTrang - 1;
                idTrang = idTrang * soBaiVietMotTrang + 1;
            }

        } catch (NumberFormatException e){
            idTrang=1;
        }
        finally {
            danhSachBaiViet=baiVietService.danhSachCoPhanTrang(idTrang,soBaiVietMotTrang);
            request.setAttribute("danhSachBaiViet", danhSachBaiViet);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/baiviet/baiviet.jsp");
            dispatcher.forward(request, response);
        }
    }
}
