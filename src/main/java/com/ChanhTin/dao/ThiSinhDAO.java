package com.ChanhTin.dao;

import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.service.DanTocService;
import com.ChanhTin.service.TinhThanhService;
import com.ChanhTin.service.TrinhDoVanHoaService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThiSinhDAO extends DAOHelper implements IThiSinhDAO {
    private static final String SELECT_ALL_THISINH_SQL;
    private static final String INSERT_THISINH_SQL;
    private static final String UPDATE_THISINH_SQL;
    private static final String DELETE_THISINH_SQL;
    private static final String GET_THISINH_BYID_SQL;
    private static final String CALL_GET_THISINH_BY_NAME;
    private static final String CALL_GET_THISINH_BY_TINHTHANH;
    private static final String CALL_GET_THISINH_BY_DANTOC;
    private static final String CALL_GET_THISINH_BY_TDVH;
    private static final String CALL_GET_THISINH_BY_STATUS_SQL;

    static {
        SELECT_ALL_THISINH_SQL = "select * from public.hosothisinh where \"trangThaiXoa\"=false ";
        INSERT_THISINH_SQL = "INSERT INTO public.hosothisinh (\"HoTen\", \"NgaySinh\", \"DiaChiCuTru\", \"SoDienThoai\", " +
                "\"Email\", \"SoCMT/HoChieu\", \"NgheNghiep\", \"maTDVH\", \"maDanToc\", \"DonViCongTac/HocTap\", \"ChieuCao\", " +
                "\"CanNang\", \"NangKhieu\", \"AnhCaNhan\", \"maTP\")  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        UPDATE_THISINH_SQL = "update public.hosothisinh set \"HoTen\"=?, \"NgaySinh\"=?, \"DiaChiCuTru\"=?, \"SoDienThoai\"=?, " +
                "\"Email\"=?, \"SoCMT/HoChieu\"=?,  \"NgheNghiep\"=?, \"maTDVH\"=?, \"maDanToc\"=?, \"DonViCongTac/HocTap\"=?, " +
                "\"ChieuCao\"=?, \"CanNang\"=?, \"NangKhieu\"=?, \"AnhCaNhan\"=?, \"maTP\"=?, \"TrangThai\"=?  where (\"maHSTS\" = ?);";
        DELETE_THISINH_SQL = "update public.hosothisinh set \"trangThaiXoa\"=true where \"maHSTS\" = ?;";
        GET_THISINH_BYID_SQL = "SELECT * FROM public.hosothisinh where \"maHSTS\" = ? and \"trangThaiXoa\"=false;";
        CALL_GET_THISINH_BY_NAME = "{call public.get_thisinh_by_name(?)}";
        CALL_GET_THISINH_BY_TINHTHANH = "SELECT * FROM public.hosothisinh where \"maTP\"=? and \"trangThaiXoa\"=false;";
        CALL_GET_THISINH_BY_DANTOC = "select * from public.hosothisinh where \"maDanToc\"=? and \"trangThaiXoa\"=false;";
        CALL_GET_THISINH_BY_TDVH = "select * from public.hosothisinh where \"maTDVH\"=? and \"trangThaiXoa\"=false;";
        CALL_GET_THISINH_BY_STATUS_SQL = "select * from public.hosothisinh where \"TrangThai\"= ? and \"trangThaiXoa\"=false;";

    }

    public ThiSinhDAO() {
    }


    @Override
    public List<ThiSinh> getAll() throws SQLException {
        System.out.println(SELECT_ALL_THISINH_SQL);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_THISINH_SQL);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maHSTS = Integer.parseInt(rs.getString("maHSTS"));
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();
        return danhSachThiSinh;
    }

    @Override
    public List<ThiSinh> getAllPagination(int start, int total) throws SQLException {
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_THISINH_SQL+
                "limit " + total +" offset " +(start-1));
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maHSTS = Integer.parseInt(rs.getString("maHSTS"));
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();
        return danhSachThiSinh;
    }

    @Override
    public boolean save(ThiSinh thiSinh) throws SQLException {
        System.out.println(INSERT_THISINH_SQL);

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_THISINH_SQL);

        preparedStatement.setString(1, thiSinh.getHoTen());
        preparedStatement.setDate(2, thiSinh.getNgaySinh());
        preparedStatement.setString(3, thiSinh.getDiaChiCuTru());
        preparedStatement.setString(4, thiSinh.getSdt());
        preparedStatement.setString(5, thiSinh.getEmail());
        preparedStatement.setString(6, thiSinh.getChungMinhNhanDan());
        preparedStatement.setString(7, thiSinh.getNgheNghiep());
        preparedStatement.setInt(8, thiSinh.getIdTrinhDoVH());
        preparedStatement.setInt(9, thiSinh.getIdDanToc());
        preparedStatement.setString(10, thiSinh.getDonViCongTac());
        preparedStatement.setFloat(11, thiSinh.getChieuCao());
        preparedStatement.setFloat(12, thiSinh.getCanNang());
        preparedStatement.setString(13, thiSinh.getNangKhieuKhac());
        preparedStatement.setString(14, thiSinh.getAnhCaNhan());
        preparedStatement.setInt(15, thiSinh.getIdTinh());

        System.out.println(preparedStatement);
        boolean rowSaved=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowSaved;
    }

    @Override
    public boolean update(int id, ThiSinh thiSinh) throws SQLException {

        System.out.println(UPDATE_THISINH_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_THISINH_SQL);

        preparedStatement.setString(1, thiSinh.getHoTen());
        preparedStatement.setDate(2, thiSinh.getNgaySinh());
        preparedStatement.setString(3, thiSinh.getDiaChiCuTru());
        preparedStatement.setString(4, thiSinh.getSdt());
        preparedStatement.setString(5, thiSinh.getEmail());
        preparedStatement.setString(6, thiSinh.getChungMinhNhanDan());
        preparedStatement.setString(7, thiSinh.getNgheNghiep());
        preparedStatement.setInt(8, thiSinh.getIdTrinhDoVH());
        preparedStatement.setInt(9, thiSinh.getIdDanToc());
        preparedStatement.setString(10, thiSinh.getDonViCongTac());
        preparedStatement.setFloat(11, thiSinh.getChieuCao());
        preparedStatement.setFloat(12, thiSinh.getCanNang());
        preparedStatement.setString(13, thiSinh.getNangKhieuKhac());
        preparedStatement.setString(14, thiSinh.getAnhCaNhan());
        preparedStatement.setInt(15, thiSinh.getIdTinh());
        preparedStatement.setString(16,thiSinh.getTrangThaiDuyet());
        preparedStatement.setInt(17,thiSinh.getIdThiSinh());

        System.out.println(preparedStatement);

        boolean rowUpdated=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        System.out.println(DELETE_THISINH_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_THISINH_SQL);
        preparedStatement.setInt(1, id);

        boolean rowDeleted=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    @Override
    public ThiSinh findById(int id) throws SQLException {

        System.out.println(GET_THISINH_BYID_SQL);
        ThiSinh thiSinh = null;

        connection();

        CallableStatement callableStatement = connection.prepareCall(GET_THISINH_BYID_SQL);

        callableStatement.setInt(1, id);

        ResultSet rs = callableStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
        }

        rs.close();
        callableStatement.close();
        disconnect();

        return thiSinh;
    }

    @Override
    public List<ThiSinh> danhSachThiSinh() throws SQLException {
        return new ArrayList<>(getAll());
    }

    @Override
    public List<ThiSinh> findByName(String tenThiSinh) throws SQLException {

        System.out.println(CALL_GET_THISINH_BY_NAME);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();
        connection();

        CallableStatement callableStatement = connection.prepareCall(CALL_GET_THISINH_BY_NAME);

        callableStatement.setString(1, tenThiSinh);

        ResultSet rs = callableStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        callableStatement.close();
        disconnect();

        return danhSachThiSinh;
    }

    @Override
    public List<ThiSinh> findByTinhThanh(int idTinhThanh) throws SQLException {
        System.out.println(CALL_GET_THISINH_BY_TINHTHANH);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(CALL_GET_THISINH_BY_TINHTHANH);

        preparedStatement.setInt(1, idTinhThanh);

        ResultSet rs = preparedStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachThiSinh;

    }

    @Override
    public List<ThiSinh> findByDanToc(int idDanToc) throws SQLException {
        System.out.println(CALL_GET_THISINH_BY_DANTOC);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();
        connection();

        CallableStatement callableStatement = connection.prepareCall(CALL_GET_THISINH_BY_DANTOC);

        callableStatement.setInt(1, idDanToc);

        ResultSet rs = callableStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        callableStatement.close();
        disconnect();

        return danhSachThiSinh;
    }

    @Override
    public List<ThiSinh> findByTDVH(int idTDVH) throws SQLException {
        System.out.println(CALL_GET_THISINH_BY_TDVH);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(CALL_GET_THISINH_BY_TDVH);

        preparedStatement.setInt(1, idTDVH);

        ResultSet rs = preparedStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachThiSinh;
    }

    @Override
    public List<ThiSinh> danhSachDaDuyet() throws SQLException {
        System.out.println(CALL_GET_THISINH_BY_STATUS_SQL);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(CALL_GET_THISINH_BY_STATUS_SQL);

        preparedStatement.setString(1, "Đã duyệt");

        ResultSet rs = preparedStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachThiSinh;
    }

    @Override
    public List<ThiSinh> danhSachChuaDuyet() throws SQLException {
        System.out.println(CALL_GET_THISINH_BY_STATUS_SQL);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(CALL_GET_THISINH_BY_STATUS_SQL);

        preparedStatement.setString(1, "Chờ duyệt");

        ResultSet rs = preparedStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachThiSinh;
    }

    @Override
    public List<ThiSinh> danhSachBiLoai() throws SQLException {
        System.out.println(CALL_GET_THISINH_BY_STATUS_SQL);
        List<ThiSinh> danhSachThiSinh = new ArrayList<>();
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(CALL_GET_THISINH_BY_STATUS_SQL);

        preparedStatement.setString(1, "Bị loại");

        ResultSet rs = preparedStatement.executeQuery();


        while (rs.next()) {
            int maHSTS = rs.getInt("maHSTS");
            String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChiCuTru = rs.getString("DiaChiCuTru");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String soCMT = rs.getString("SoCMT/HoChieu");
            String ngheNghiep = rs.getString("NgheNghiep");
            int maTDVH = Integer.parseInt(rs.getString("maTDVH"));
            int maDanToc = Integer.parseInt(rs.getString("maDanToc"));
            String donViCongTac = rs.getString("DonViCongTac/HocTap");
            float chieuCao = Float.parseFloat(rs.getString("ChieuCao"));
            float canNang = Float.parseFloat(rs.getString("CanNang"));
            String nangKhieu = rs.getString("NangKhieu");
            String anhCaNhan = rs.getString("AnhCaNhan");
            int maTP = Integer.parseInt(rs.getString("maTP"));
            String trangThai = rs.getString("TrangThai");

            ThiSinh thiSinh=new ThiSinh(maHSTS, hoTen, ngaySinh, diaChiCuTru, soDienThoai, email, soCMT, ngheNghiep,
                    maTDVH, maDanToc, donViCongTac, chieuCao, canNang, nangKhieu, anhCaNhan, maTP, trangThai);

            setTinhThanhDanTocTDVH(maTDVH, maDanToc, maTP, thiSinh);
            danhSachThiSinh.add(thiSinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachThiSinh;
    }

    @Override
    public void setTinhThanhDanTocTDVH(int maTDVH, int maDanToc, int maTP, ThiSinh thiSinh) throws SQLException {
        TinhThanhDAO tinhThanhDAO = new TinhThanhDAO();
        thiSinh.setTinhThanh(tinhThanhDAO.findById(maTP));

        DanTocDAO danTocDAO = new DanTocDAO();
        thiSinh.setDanToc(danTocDAO.findById(maDanToc));

        TrinhDoVanHoaDAO trinhDoVanHoaDAO = new TrinhDoVanHoaDAO();
        thiSinh.setTrinhDoVanHoa(trinhDoVanHoaDAO.findById(maTDVH));
    }
}
