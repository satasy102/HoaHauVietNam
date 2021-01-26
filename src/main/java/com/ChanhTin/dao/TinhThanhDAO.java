package com.ChanhTin.dao;

import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.model.TinhThanh;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TinhThanhDAO extends DAOHelper implements BaseDAO<TinhThanh> {
    private static final String SELECT_ALL_TINHTHANH_SQL;
    private static final String INSERT_TINHTHANHPHO_SQL;
    private static final String UPDATE_TINHTHANH_SQL;
    private static final String DELETE_TINHTHANH_SQL;
    private static final String GET_TINHTHANH_BYID_SQL;

    static {
        SELECT_ALL_TINHTHANH_SQL = "select * from public.tinhthanhpho where \"trangThaiXoa\"=false " ;
        INSERT_TINHTHANHPHO_SQL = "INSERT INTO public.tinhthanhpho (\"tenTP\") VALUES (?);";
        UPDATE_TINHTHANH_SQL = "update public.tinhthanhpho set \"tenTP\" = ? where \"maTP\" = ?;";
        DELETE_TINHTHANH_SQL = "update public.tinhthanhpho set \"trangThaiXoa\"=true where \"maTP\" = ?;";
        GET_TINHTHANH_BYID_SQL = "SELECT * FROM public.tinhthanhpho where \"maTP\" = ? and \"trangThaiXoa\"=false;";
    }

    public TinhThanhDAO() {
    }


    @Override
    public List<TinhThanh> getAll() throws SQLException {

        List<TinhThanh> danhSachTinhThanh = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TINHTHANH_SQL);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maTP = rs.getInt("maTP");
            String tenTP = rs.getString("tenTP");
            danhSachTinhThanh.add(new TinhThanh(maTP, tenTP));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachTinhThanh;
    }

    @Override
    public List<TinhThanh> getAllPagination(int start, int total) throws SQLException {
        List<TinhThanh> danhSachTinhThanh = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TINHTHANH_SQL+
                " limit " +total+ " offset "+(start-1));
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maTP = rs.getInt("maTP");
            String tenTP = rs.getString("tenTP");
            danhSachTinhThanh.add(new TinhThanh(maTP, tenTP));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachTinhThanh;
    }

    @Override
    public boolean save(TinhThanh tinhThanh) throws SQLException {

        System.out.println(INSERT_TINHTHANHPHO_SQL);

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TINHTHANHPHO_SQL);

        preparedStatement.setString(1, tinhThanh.getTenTinh());

        System.out.println(preparedStatement);

        boolean rowSaved = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowSaved;
    }

    @Override
    public boolean update(int idTinh, TinhThanh tinhThanh) throws SQLException {

        System.out.println(UPDATE_TINHTHANH_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TINHTHANH_SQL);
        preparedStatement.setString(1, tinhThanh.getTenTinh());
        preparedStatement.setInt(2, idTinh);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        System.out.println(DELETE_TINHTHANH_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TINHTHANH_SQL);
        preparedStatement.setInt(1, id);

        boolean rowDeleted=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    @Override
    public TinhThanh findById(int id) throws SQLException {
        TinhThanh tinhThanh = null;

        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_TINHTHANH_BYID_SQL);

        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            String tenTinh = rs.getString("tenTP");

            tinhThanh = new TinhThanh(id, tenTinh);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return tinhThanh;
    }

    @Override
    public List<ThiSinh> danhSachThiSinh() throws SQLException {
        return null;
    }
}
