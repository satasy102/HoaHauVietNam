package com.ChanhTin.service;

import com.ChanhTin.dao.BaiVietDAO;
import com.ChanhTin.model.BaiViet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaiVietService extends ValidateHelper implements BaseService<BaiViet> {
    BaiVietDAO baiVietDAO = new BaiVietDAO();

    public BaiVietService() {
    }

    @Override
    public ArrayList<String> result(BaiViet baiViet) throws SQLException {
        ArrayList<String> danhSachLoi = new ArrayList<>();
        if (!validateString(baiViet.getTieuDe(), 300, 1))
            danhSachLoi.add("Lỗi nhập tiêu đề Bài viết");
        else if (!validateString(baiViet.getNoiDungTomTat(), 800, 1))
            danhSachLoi.add("Lỗi nhập nội dung tóm tắt");
        else danhSachLoi.add("200");
        return danhSachLoi;
    }

    @Override
    public List<BaiViet> danhSach() throws SQLException {
        return new ArrayList<>(baiVietDAO.getAll());
    }

    @Override
    public List<BaiViet> danhSachCoPhanTrang(int idTrang, int soHangMotTrang) throws SQLException {
        return new ArrayList<>(baiVietDAO.getAllPagination(idTrang, soHangMotTrang));
    }

    @Override
    public ArrayList<String> them(BaiViet baiViet) throws SQLException {
        ArrayList<String> danhSachLoi = result(baiViet);
        if (danhSachLoi.get(0).equals("200"))
            if (kiemTraTrungNhau(baiViet))
                danhSachLoi.set(0, "Bài viết này đã tồn tại");
            else baiVietDAO.save(baiViet);
        return danhSachLoi;
    }

    @Override
    public ArrayList<String> sua(int id, BaiViet baiViet) throws SQLException {
        ArrayList<String> danhSachLoi = result(baiViet);
        BaiViet baiVietBanDau = timTheoID(id);
        if (danhSachLoi.get(0).equals("200"))
            if (baiVietBanDau.equals(baiViet))
                danhSachLoi.set(0, "Không thay đổi gì");
            else baiVietDAO.update(id, baiViet);
        return danhSachLoi;
    }

    @Override
    public String xoa(int id) throws SQLException {
        if (baiVietDAO.delete(id))
            return "Xóa thành công";
        return "Xóa không thành công";
    }

    @Override
    public BaiViet timTheoID(int id) throws SQLException {
        return baiVietDAO.findById(id);
    }

    @Override
    public boolean kiemTraTrungNhau(BaiViet baiViet) throws SQLException {
        ArrayList<BaiViet> danhSachBaiViet = new ArrayList<>(danhSach());
        for (BaiViet baiVietBanDau : danhSachBaiViet)
            if (baiVietBanDau.equals(baiViet))
                return true;
        return false;
    }
    
    public List<BaiViet> danhSachBaiVietMoiNhat() throws SQLException{
        return baiVietDAO.danhSachBaiVietMoiNhat();
    }
}
