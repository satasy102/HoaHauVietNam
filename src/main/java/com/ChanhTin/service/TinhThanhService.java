package com.ChanhTin.service;


import com.ChanhTin.dao.TinhThanhDAO;
import com.ChanhTin.model.TinhThanh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TinhThanhService extends ValidateHelper implements BaseService<TinhThanh> {
    TinhThanhDAO tinhThanhDAO = new TinhThanhDAO();

    public TinhThanhService() {
    }

    @Override
    public ArrayList<String> result(TinhThanh tinhThanh) {
        ArrayList<String> danhSachLoi = new ArrayList<>();
        if (!regexName(tinhThanh.getTenTinh(), 100, 1))
            danhSachLoi.add("Lỗi nhập tên Tỉnh");
        else danhSachLoi.add("200");
        return danhSachLoi;
    }

    @Override
    public List<TinhThanh> danhSach() throws SQLException {
        return new ArrayList<>(tinhThanhDAO.getAll());
    }

    @Override
    public List<TinhThanh> danhSachCoPhanTrang(int idTrang, int soHangMotTrang) throws SQLException {
        return new ArrayList<>(tinhThanhDAO.getAllPagination(idTrang,soHangMotTrang));
    }

    @Override
    public ArrayList<String> them(TinhThanh tinhThanh) throws SQLException {
        ArrayList<String> danhSachLoi = result(tinhThanh);
        if (danhSachLoi.get(0).equals("200"))
            if (kiemTraTrungNhau(tinhThanh))
                danhSachLoi.set(0, "Tỉnh thành này đã tồn tại");
            else tinhThanhDAO.save(tinhThanh);

        return danhSachLoi;
    }

    @Override
    public ArrayList<String> sua(int id, TinhThanh tinhThanh) throws SQLException {
        ArrayList<String> danhSachLoi = result(tinhThanh);
        TinhThanh tinhThanhBanDau = timTheoID(id);
        if (danhSachLoi.get(0).equals("200"))
            if (tinhThanhBanDau.equals(tinhThanh)) {
                danhSachLoi.set(0, "Không chỉnh sửa gì.");
            } else tinhThanhDAO.update(id, tinhThanh);
        return danhSachLoi;
    }

    @Override
    public String xoa(int id) throws SQLException {
        if (tinhThanhDAO.delete(id))
            return "Xóa thành công";
        return "Xóa không thành công";
    }

    @Override
    public TinhThanh timTheoID(int id) throws SQLException {
        return tinhThanhDAO.findById(id);
    }

    @Override
    public boolean kiemTraTrungNhau(TinhThanh tinhThanh) throws SQLException {
        ArrayList<TinhThanh> danhSachTinhThanh = new ArrayList<>(danhSach());
        for (TinhThanh tinhThanhBanDau : danhSachTinhThanh)
            if (tinhThanhBanDau.equals(tinhThanh))
                return true;
        return false;
    }


}
