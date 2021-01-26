package com.ChanhTin.service;

import com.ChanhTin.dao.TrinhDoVanHoaDAO;
import com.ChanhTin.model.TrinhDoVanHoa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrinhDoVanHoaService extends ValidateHelper implements BaseService<TrinhDoVanHoa> {
    TrinhDoVanHoaDAO trinhDoVanHoaDAO = new TrinhDoVanHoaDAO();

    public TrinhDoVanHoaService() {
    }

    @Override
    public ArrayList<String> result(TrinhDoVanHoa trinhDoVanHoa) throws SQLException {
        ArrayList<String> danhSachLoi = new ArrayList<>();
        if (!regexName(trinhDoVanHoa.getTenTrinhDoVanHoa(), 100, 1))
            danhSachLoi.add("Lỗi nhập tên Trình độ văn hóa");
        else danhSachLoi.add("200");
        return danhSachLoi;
    }

    @Override
    public List<TrinhDoVanHoa> danhSach() throws SQLException {
        return new ArrayList<>(trinhDoVanHoaDAO.getAll());
    }

    @Override
    public List<TrinhDoVanHoa> danhSachCoPhanTrang(int idTrang, int soHangMotTrang) throws SQLException {
        return new ArrayList<>(trinhDoVanHoaDAO.getAllPagination(idTrang, soHangMotTrang));
    }

    @Override
    public ArrayList<String> them(TrinhDoVanHoa trinhDoVanHoa) throws SQLException {
        ArrayList<String> danhSachLoi = result(trinhDoVanHoa);
        if (danhSachLoi.get(0).equals("200"))
            if (kiemTraTrungNhau(trinhDoVanHoa))
                danhSachLoi.set(0, "Trình độ văn hóa này đã tồn tại");
            else trinhDoVanHoaDAO.save(trinhDoVanHoa);

        return danhSachLoi;
    }

    @Override
    public ArrayList<String> sua(int id, TrinhDoVanHoa trinhDoVanHoa) throws SQLException {
        ArrayList<String> danhSachLoi = result(trinhDoVanHoa);
        TrinhDoVanHoa trinhDoVanHoaBanDau = timTheoID(id);
        if (danhSachLoi.get(0).equals("200"))
            if (trinhDoVanHoaBanDau.equals(trinhDoVanHoa))
                danhSachLoi.set(0, "Không thay đổi gì");
            else trinhDoVanHoaDAO.update(id, trinhDoVanHoa);
        return danhSachLoi;
    }

    @Override
    public String xoa(int id) throws SQLException {
        if (trinhDoVanHoaDAO.delete(id))
            return "Xóa thành công";
        return "Xóa không thành công";
    }

    @Override
    public TrinhDoVanHoa timTheoID(int id) throws SQLException {
        return trinhDoVanHoaDAO.findById(id);
    }

    @Override
    public boolean kiemTraTrungNhau(TrinhDoVanHoa trinhDoVanHoa) throws SQLException {
        ArrayList<TrinhDoVanHoa> danhSachTrinhDoVanHoa = new ArrayList<>(danhSach());
        for (TrinhDoVanHoa trinhDoVanHoaBanDau : danhSachTrinhDoVanHoa)
            if (trinhDoVanHoaBanDau.equals(trinhDoVanHoa))
                return true;
        return false;
    }
}
