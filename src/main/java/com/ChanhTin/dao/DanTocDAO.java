package com.ChanhTin.dao;

import com.ChanhTin.model.DanToc;
import com.ChanhTin.model.ThiSinh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanTocDAO extends DAOHelper implements BaseDAO<DanToc> {
    private static final String SELECT_ALL_DANTOC_SQL;
    private static final String INSERT_DANTOC_SQL;
    private static final String UPDATE_DANTOC_SQL;
    private static final String DELETE_DANTOC_SQL;
    private static final String GET_DANTOC_BYID_SQL;

    static {
        SELECT_ALL_DANTOC_SQL = "select * from public.dantoc where \"trangThaiXoa\"=false";
        INSERT_DANTOC_SQL = "INSERT INTO public.dantoc (\"tenDanToc\") VALUES (?);";
        UPDATE_DANTOC_SQL = "update public.dantoc set tenDanToc = ? where \"maDanToc\" = ?;";
        DELETE_DANTOC_SQL = "update public.dantoc set \"trangThaiXoa\"=true where \"maDanToc\" = ?;";
        GET_DANTOC_BYID_SQL = "select * from public.dantoc where \"maDanToc\"=? and \"trangThaiXoa\"=false;";
    }

    public DanTocDAO() {
    }


    @Override
    public List<DanToc> getAll() throws SQLException {

        List<DanToc> danhSachDanToc = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DANTOC_SQL);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maDanToc = rs.getInt("maDanToc");
            String tenDanToc = rs.getString("tenDanToc");
            danhSachDanToc.add(new DanToc(maDanToc, tenDanToc));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachDanToc;
    }

    @Override
    public List<DanToc> getAllPagination(int start, int total) throws SQLException {
        List<DanToc> danhSachDanToc = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DANTOC_SQL +
                " limit " +total+ " offset "+(start-1));
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maDanToc = rs.getInt("maDanToc");
            String tenDanToc = rs.getString("tenDanToc");
            danhSachDanToc.add(new DanToc(maDanToc, tenDanToc));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachDanToc;
    }

    @Override
    public boolean save(DanToc danToc) throws SQLException {

        System.out.println(INSERT_DANTOC_SQL);

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DANTOC_SQL);

        preparedStatement.setString(1, danToc.getTenDanToc());

        System.out.println(preparedStatement);

        boolean rowSaved=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowSaved;
    }

    @Override
    public boolean update(int id, DanToc danToc) throws SQLException {
        System.out.println(UPDATE_DANTOC_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DANTOC_SQL);
        preparedStatement.setString(1, danToc.getTenDanToc());
        preparedStatement.setInt(2, danToc.getIdDanToc());

        boolean rowUpdated=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        System.out.println(DELETE_DANTOC_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DANTOC_SQL);
        preparedStatement.setInt(1, id);

        boolean rowDeleted=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    @Override
    public DanToc findById(int id) throws SQLException {
        DanToc danToc = null;

        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_DANTOC_BYID_SQL);

        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            String tenDanToc = rs.getString("tenDanToc");

            danToc = new DanToc(id, tenDanToc);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danToc;
    }

    @Override
    public List<ThiSinh> danhSachThiSinh() throws SQLException{
        return null;
    }
}
