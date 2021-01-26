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

@WebServlet(name = "SuaTrinhDoVHServlet", urlPatterns = "/suatdvh")
public class SuaTrinhDoVHServlet extends HttpServlet {
    TrinhDoVanHoaService trinhDoVanHoaService=new TrinhDoVanHoaService();
    String username;
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
        try {
            suaTDVH(request, response);
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
            formSuaTDVH(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void formSuaTDVH(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        TrinhDoVanHoa trinhDoVanHoa = trinhDoVanHoaService.timTheoID(id);
        request.setAttribute("trinhDoVanHoa", trinhDoVanHoa);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/trinhdovanhoa/suatdvh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void suaTDVH(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        int id=Integer.parseInt(request.getParameter("id"));
        String tenTrinhDoVH=request.getParameter("tenTrinhDoVH");

        TrinhDoVanHoa trinhDoVanHoa=new TrinhDoVanHoa(id,tenTrinhDoVH);

        request.setAttribute("message", trinhDoVanHoaService.sua(trinhDoVanHoa.getIdTrinhDoVanHoa(), trinhDoVanHoa));

        request.setAttribute("trinhDoVanHoa", trinhDoVanHoa);

        request.setAttribute("danhSachTrinhDoVH", trinhDoVanHoaService.danhSach());

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/trinhdovanhoa/suatdvh.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
