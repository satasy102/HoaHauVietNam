package com.ChanhTin.controller.ui;

import com.ChanhTin.model.*;
import com.ChanhTin.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    ThiSinhService thiSinhService=new ThiSinhService();
    BaiVietService baiVietService=new BaiVietService();
    DanTocService danTocService= new DanTocService();
    TinhThanhService tinhThanhService = new TinhThanhService();
    TrinhDoVanHoaService trinhDoVanHoaService=new TrinhDoVanHoaService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ThiSinh> danhSachThiSinh=thiSinhService.danhSach();
            request.setAttribute("danhSachThiSinh",danhSachThiSinh);

            List<ThiSinh> danhSachDaDuyet=thiSinhService.danhSachDaDuyet();
            request.setAttribute("danhSachDaDuyet",danhSachDaDuyet);

            List<BaiViet> danhSachBaiViet=baiVietService.danhSachBaiVietMoiNhat();
            request.setAttribute("danhSachBaiViet",danhSachBaiViet);

            List<TinhThanh> danhSachTinhThanh = tinhThanhService.danhSach();
            request.setAttribute("danhSachTinhThanh", danhSachTinhThanh);

            List<DanToc> danhSachDanToc = danTocService.danhSach();
            request.setAttribute("danhSachDanToc", danhSachDanToc);

            List<TrinhDoVanHoa> danhSachTDVH = trinhDoVanHoaService.danhSach();
            request.setAttribute("danhSachTDVH", danhSachTDVH);

            RequestDispatcher requestDispatcher= request.getRequestDispatcher("views/ui/index.jsp");
            requestDispatcher.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
