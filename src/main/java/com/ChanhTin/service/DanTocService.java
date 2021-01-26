package com.ChanhTin.service;

import com.ChanhTin.dao.DanTocDAO;
import com.ChanhTin.model.DanToc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DanTocService extends ValidateHelper implements BaseService<DanToc> {
    DanTocDAO danTocDAO = new DanTocDAO();


    public DanTocService() {
    }

    @Override
    public ArrayList<String> result(DanToc danToc) throws SQLException {
        ArrayList<String> danhSachLoi = new ArrayList<>();
        if (!regexName(danToc.getTenDanToc(), 80, 1))
            danhSachLoi.add("Lỗi nhập Tên Dân Tộc");
        else danhSachLoi.add("200");
        return danhSachLoi;
    }

    @Override
    public List<DanToc> danhSach() throws SQLException {
        return new ArrayList<>(danTocDAO.getAll());
    }

    @Override
    public List<DanToc> danhSachCoPhanTrang(int idTrang, int soHangMotTrang) throws SQLException {
        return new ArrayList<>(danTocDAO.getAllPagination(idTrang, soHangMotTrang));
    }

    @Override
    public ArrayList<String> them(DanToc danToc) throws SQLException {
        ArrayList<String> danhSachLoi = result(danToc);
        if (danhSachLoi.get(0).equals("200"))
            if (kiemTraTrungNhau(danToc))
                danhSachLoi.set(0, "Dân tộc này đã tồn tại");
            else danTocDAO.save(danToc);
        return danhSachLoi;
    }

    @Override
    public ArrayList<String> sua(int id, DanToc danToc) throws SQLException {
        ArrayList<String> danhSachLoi = result(danToc);
        DanToc danTocBanDau = timTheoID(id);
        if (danhSachLoi.get(0).equals("200"))
            if (danTocBanDau.equals(danToc))
                danhSachLoi.set(0, "Không thay đổi gì");
            else danTocDAO.update(id, danToc);
        return danhSachLoi;
    }

    @Override
    public String xoa(int id) throws SQLException {
        if (danTocDAO.delete(id))
            return "Xóa thành công";
        return "Xóa không thành công";
    }

    @Override
    public DanToc timTheoID(int id) throws SQLException {
        return danTocDAO.findById(id);
    }

    @Override
    public boolean kiemTraTrungNhau(DanToc danToc) throws SQLException {
        ArrayList<DanToc> danhSachDanToc = new ArrayList<>(danhSach());
        for (DanToc danTocBanDau : danhSachDanToc)
            if (danTocBanDau.equals(danToc))
                return true;
        return false;
    }
}
