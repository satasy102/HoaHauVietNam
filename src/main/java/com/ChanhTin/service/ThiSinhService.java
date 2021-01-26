package com.ChanhTin.service;

import com.ChanhTin.dao.ThiSinhDAO;
import com.ChanhTin.model.ThiSinh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThiSinhService extends ValidateHelper implements IThiSinhService {

    ThiSinhDAO thiSinhDAO = new ThiSinhDAO();

    public ThiSinhService() {
    }

    @Override
    public ArrayList<String> result(ThiSinh thiSinh) throws SQLException {

        ArrayList<String> danhSachLoi = new ArrayList<>();
        if (!regexName(thiSinh.getHoTen(), 150, 1))
            danhSachLoi.add("Lỗi nhập Họ tên");
        if (!validateString(thiSinh.getDiaChiCuTru(), 200, 1))
            danhSachLoi.add("Lỗi nhập địa chỉ");
        if (!regexPhoneNum(thiSinh.getSdt()))
            danhSachLoi.add("Lỗi nhập số điện thoại");
        if (!validateString(thiSinh.getEmail(), 50, 1) ||
                !regexEmail(thiSinh.getEmail()))
            danhSachLoi.add("Lỗi nhập Email");
        if (!regexPeopleID(thiSinh.getChungMinhNhanDan()))
            danhSachLoi.add("Lỗi nhập Chứng minh nhân dân, hộ chiếu");
        if (!regexName(thiSinh.getNgheNghiep(), 45, 1))
            danhSachLoi.add("Lỗi nhập Nghề nghiệp");
        if (!regexName(thiSinh.getDonViCongTac(), 200, 1))
            danhSachLoi.add("Lỗi nhập Đơn vị công tác");
        if (!validatePositiveNum(thiSinh.getChieuCao()))
            danhSachLoi.add("Lỗi nhập Chiều cao");
        if (!validatePositiveNum(thiSinh.getCanNang()))
            danhSachLoi.add("Lỗi nhập Cân nặng");
        if (!regexName(thiSinh.getNangKhieuKhac(), 45, 1))
            danhSachLoi.add("Lỗi nhập Năng khiếu khác");
        if (!validateString(thiSinh.getAnhCaNhan(), 400, 1) ||
                !regexURL(thiSinh.getAnhCaNhan()))
            danhSachLoi.add("Lỗi nhập Ảnh cá nhân");
        if (danhSachLoi.size() == 0)
            danhSachLoi.add("200");
        return danhSachLoi;
    }

    @Override
    public List<ThiSinh> danhSach() throws SQLException {
        return new ArrayList<>(thiSinhDAO.getAll());
    }

    @Override
    public List<ThiSinh> danhSachCoPhanTrang(int idTrang, int soHangMotTrang) throws SQLException {
        return new ArrayList<>(thiSinhDAO.getAllPagination(idTrang, soHangMotTrang));
    }

    @Override
    public ArrayList<String> them(ThiSinh thiSinh) throws SQLException {
        ArrayList<String> danhSachLoi = result(thiSinh);
        ArrayList<ThiSinh> danhSachThiSinh=new ArrayList<>(danhSach());

        for(ThiSinh thiSinhBanDau: danhSachThiSinh){
            if(thiSinhBanDau.getSdt().equals(thiSinh.getSdt()))
                danhSachLoi.add("Số điện thoại này đã có người sử dụng");
            if(thiSinhBanDau.getChungMinhNhanDan().equals(thiSinh.getChungMinhNhanDan()))
                danhSachLoi.add("Chứng minh nhân dân này đã có người sử dụng");
            if(thiSinhBanDau.getEmail().equalsIgnoreCase(thiSinh.getEmail()))
                danhSachLoi.add("Email này đã có người sử dụng");
        }

        if (danhSachLoi.size()==1&&danhSachLoi.get(0).equals("200"))
            if (kiemTraTrungNhau(thiSinh)) {
                danhSachLoi.set(0, "Đã tồn tại Thí sinh này");
            } else thiSinhDAO.save(thiSinh);
            else {
                danhSachLoi.remove("200");
        }
        return danhSachLoi;
    }

    @Override
    public ArrayList<String> sua(int id, ThiSinh thiSinh) throws SQLException {
        ThiSinh thiSinhBanDau = thiSinhDAO.findById(id);
        ArrayList<String> danhSachLoi = result(thiSinh);
        if (danhSachLoi.get(0).equals("200"))
            if (thiSinhBanDau.equals(thiSinh)) {
                danhSachLoi.set(0, "Không chỉnh sửa gì.");
            } else thiSinhDAO.update(id, thiSinh);
        return danhSachLoi;
    }

    @Override
    public String xoa(int id) throws SQLException {
        ThiSinh thiSinh=thiSinhDAO.findById(id);
        if(!thiSinh.getTrangThaiDuyet().equalsIgnoreCase("Đã duyệt"))
            if (thiSinhDAO.delete(id))
                return "Xóa thành công";
            else return "Xóa không thành công";
        return "Đã duyệt thí sinh này. Không thể xóa";
    }

    @Override
    public ThiSinh timTheoID(int id) throws SQLException {
        return thiSinhDAO.findById(id);
    }

    @Override
    public List<ThiSinh> timTheoTen(String ten) throws SQLException {
        return thiSinhDAO.findByName(ten);
    }

    @Override
    public boolean kiemTraTrungNhau(ThiSinh thiSinh) throws SQLException {
        ArrayList<ThiSinh> danhSachThiSinh = new ArrayList<>(danhSach());
        for (ThiSinh thiSinhBanDau : danhSachThiSinh)
            if (thiSinhBanDau.equals(thiSinh))
                return true;
        return false;
    }

    @Override
    public List<ThiSinh> timTheoTinhThanh(int id) throws SQLException {
        return thiSinhDAO.findByTinhThanh(id);
    }

    @Override
    public List<ThiSinh> timTheoDanToc(int id) throws SQLException {
        return thiSinhDAO.findByDanToc(id);
    }

    @Override
    public List<ThiSinh> timTheoTrinhDoVH(int id) throws SQLException {
        return thiSinhDAO.findByTDVH(id);
    }

    @Override
    public List<ThiSinh> danhSachDaDuyet() throws SQLException {
        return thiSinhDAO.danhSachDaDuyet();
    }

    @Override
    public List<ThiSinh> danhSachChuaDuyet() throws SQLException {
        return thiSinhDAO.danhSachChuaDuyet();
    }

    @Override
    public List<ThiSinh> danhSachBiLoai() throws SQLException {
        return thiSinhDAO.danhSachBiLoai();
    }

    @Override
    public void setTinhThanhDanTocTDVH(int maTDVH, int maDanToc, int maTP, ThiSinh thiSinh) throws SQLException {
        thiSinhDAO.setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
    }




}
