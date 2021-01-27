package com.ChanhTin.dao;

import com.ChanhTin.model.BaiViet;
import com.ChanhTin.model.ThiSinh;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaiVietDAO extends DAOHelper implements BaseDAO<BaiViet> {
    private static final String SELECT_ALL_BAIVIET_SQL;
    private static final String INSERT_BAIVIET_SQL;
    private static final String UPDATE_BAIVIET_SQL;
    private static final String DELETE_BAIVIET_SQL;
    private static final String GET_BAIVIET_BYID_SQL;
    private static final String GET_BAIVIET_SOMNHAT_SQL;

    static {
        SELECT_ALL_BAIVIET_SQL = "select * from public.baiviet where \"trangThaiXoa\"=false order by \"maBaiViet\" DESC ";
        INSERT_BAIVIET_SQL = "insert into public.baiviet (\"tieuDe\",\"noiDungTomTat\",\"noiDung\",\"anhTieuDe\") VALUES (?,?,?,?);";
        UPDATE_BAIVIET_SQL = "update public.baiviet set \"tieuDe\" = ?,\"noiDungTomTat\"=?,\"noiDung\"=?,\"anhTieuDe\"=? where \"maBaiViet\" = ?;";
        DELETE_BAIVIET_SQL = "update public.baiviet set \"trangThaiXoa\"=true where \"maBaiViet\" = ?;";
        GET_BAIVIET_BYID_SQL = "select * from public.baiviet where \"maBaiViet\"=? and \"trangThaiXoa\"=false;";
        GET_BAIVIET_SOMNHAT_SQL = "select * from public.baiviet where \"trangThaiXoa\"=false order by \"maBaiViet\" desc limit 10;";
    }

    @Override
    public List<BaiViet> getAll() throws SQLException {
        List<BaiViet> danhSachBaiViet = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BAIVIET_SQL);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maBaiViet = rs.getInt("maBaiViet");
            String tieuDe = rs.getString("tieuDe");
            String noiDungTomTat= rs.getString("noiDungTomTat");
            String noiDung=rs.getString("noiDung");
            String anhTieuDe=rs.getString("anhTieuDe");
            danhSachBaiViet.add(new BaiViet(maBaiViet, tieuDe,noiDungTomTat,noiDung, anhTieuDe));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachBaiViet;
    }

    @Override
    public List<BaiViet> getAllPagination(int start, int total) throws SQLException {
        List<BaiViet> danhSachBaiViet = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BAIVIET_SQL +
                " limit " +total+ " offset "+(start-1));
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maBaiViet = rs.getInt("maBaiViet");
            String tieuDe = rs.getString("tieuDe");
            String noiDungTomTat= rs.getString("noiDungTomTat");
            String noiDung=rs.getString("noiDung");
            String anhTieuDe=rs.getString("anhTieuDe");
            danhSachBaiViet.add(new BaiViet(maBaiViet, tieuDe,noiDungTomTat,noiDung, anhTieuDe));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachBaiViet;
    }

    @Override
    public boolean save(BaiViet baiViet) throws SQLException {
        System.out.println(INSERT_BAIVIET_SQL);

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BAIVIET_SQL);

        preparedStatement.setString(1, baiViet.getTieuDe());
        preparedStatement.setString(2, baiViet.getNoiDungTomTat());
        preparedStatement.setString(3, baiViet.getNoiDung());
        preparedStatement.setString(4, baiViet.getAnhTieuDe());
        System.out.println(preparedStatement);

        boolean rowSaved=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowSaved;
    }

    @Override
    public boolean update(int id, BaiViet baiViet) throws SQLException {
        System.out.println(UPDATE_BAIVIET_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BAIVIET_SQL);
        preparedStatement.setString(1, baiViet.getTieuDe());
        preparedStatement.setString(2, baiViet.getNoiDungTomTat());
        preparedStatement.setString(3, baiViet.getNoiDung());
        preparedStatement.setString(4, baiViet.getAnhTieuDe());
        preparedStatement.setInt(5, baiViet.getMaBaiViet());

        boolean rowUpdated=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        System.out.println(DELETE_BAIVIET_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BAIVIET_SQL);
        preparedStatement.setInt(1, id);

        boolean rowDeleted=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    @Override
    public BaiViet findById(int id) throws SQLException {
        BaiViet baiViet = null;

        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_BAIVIET_BYID_SQL);

        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maBaiViet=rs.getInt("maBaiViet");
            String tieuDe = rs.getString("tieuDe");
            String noiDungTomTat= rs.getString("noiDungTomTat");
            String noiDung=rs.getString("noiDung");
            String anhTieuDe=rs.getString("anhTieuDe");
            baiViet=new BaiViet(maBaiViet,tieuDe,noiDungTomTat,noiDung,anhTieuDe);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return baiViet;
    }

    @Override
    public List<ThiSinh> danhSachThiSinh() throws SQLException {
        return null;
    }

    public List<BaiViet> danhSachBaiVietMoiNhat() throws SQLException{
        List<BaiViet> danhSachBaiViet = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BAIVIET_SOMNHAT_SQL);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maBaiViet = rs.getInt("maBaiViet");
            String tieuDe = rs.getString("tieuDe");
            String noiDungTomTat= rs.getString("noiDungTomTat");
            String noiDung=rs.getString("noiDung");
            String anhTieuDe=rs.getString("anhTieuDe");
            danhSachBaiViet.add(new BaiViet(maBaiViet, tieuDe,noiDungTomTat,noiDung, anhTieuDe));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachBaiViet;
    }
}
