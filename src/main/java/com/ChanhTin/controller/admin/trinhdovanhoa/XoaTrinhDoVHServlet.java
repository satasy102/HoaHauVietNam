package com.ChanhTin.controller.admin.trinhdovanhoa;


import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.model.TrinhDoVanHoa;
import com.ChanhTin.service.ThiSinhService;
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

@WebServlet(name = "XoaTrinhDoVHServlet", urlPatterns = "/xoatdvh")
public class XoaTrinhDoVHServlet extends HttpServlet {
    ThiSinhService thiSinhService=new ThiSinhService();
    TrinhDoVanHoaService trinhDoVanHoaService=new TrinhDoVanHoaService();
    String username;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id=Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();

        username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()||!username.equalsIgnoreCase("admin")) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("username", username);
        try {
            List<ThiSinh> danhSachThiSinh=thiSinhService.timTheoTrinhDoVH(id);
            if(danhSachThiSinh.isEmpty()){
                request.setAttribute("message", trinhDoVanHoaService.xoa(id));

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
            } else {
                request.setAttribute("danhSachThiSinh", danhSachThiSinh);

                request.setAttribute("message", "Xin hãy xóa các thí sinh có Trình độ văn hóa này trước.");

                List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();
                request.setAttribute("danhSachTDVH", danhSachTDVH);

                TrinhDoVanHoa trinhDoVanHoa = trinhDoVanHoaService.timTheoID(id);
                request.setAttribute("trinhDoVanHoa", trinhDoVanHoa);

                RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/thisinh/cungtdvh.jsp");
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
