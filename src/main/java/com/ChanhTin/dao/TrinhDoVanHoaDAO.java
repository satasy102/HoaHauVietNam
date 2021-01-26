package com.ChanhTin.dao;

import com.ChanhTin.model.ThiSinh;
import com.ChanhTin.model.TrinhDoVanHoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrinhDoVanHoaDAO extends DAOHelper implements BaseDAO<TrinhDoVanHoa> {

    private static final String SELECT_ALL_TRINHDOVANHOA_SQL;
    private static final String INSERT_TRINHDOVANHOA_SQL;
    private static final String UPDATE_TRINHDOVANHOA_SQL;
    private static final String DELETE_TRINHDOVANHOA_SQL;
    private static final String GET_TRINHDOVANHOA_BYID_SQL;

    static {

        SELECT_ALL_TRINHDOVANHOA_SQL = "select * from public.trinhdovanhoa where \"trangThaiXoa\"=false";
        INSERT_TRINHDOVANHOA_SQL = "insert into public.trinhdovanhoa (\"tenTDVH\") VALUES (?);";
        UPDATE_TRINHDOVANHOA_SQL = "update public.trinhdovanhoa set \"tenTDVH\" = ? where \"maTDVH\" = ?;";
        DELETE_TRINHDOVANHOA_SQL = "update public.trinhdovanhoa set \"trangThaiXoa\"=true where \"maTDVH\" = ?;";
        GET_TRINHDOVANHOA_BYID_SQL = "SELECT * FROM public.trinhdovanhoa where \"maTDVH\" = ? and \"trangThaiXoa\"=false;";
    }

    public TrinhDoVanHoaDAO() {
    }


    @Override
    public List<TrinhDoVanHoa> getAll() throws SQLException {

        List<TrinhDoVanHoa> trinhDoVanHoa = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRINHDOVANHOA_SQL);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maTDVH = rs.getInt("maTDVH");
            String tenTDVH = rs.getString("tenTDVH");
            trinhDoVanHoa.add(new TrinhDoVanHoa(maTDVH, tenTDVH));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return trinhDoVanHoa;
    }

    @Override
    public List<TrinhDoVanHoa> getAllPagination(int start, int total) throws SQLException {
        List<TrinhDoVanHoa> trinhDoVanHoa = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRINHDOVANHOA_SQL +
                " limit " +total+ " offset "+(start-1));
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int maTDVH = rs.getInt("maTDVH");
            String tenTDVH = rs.getString("tenTDVH");
            trinhDoVanHoa.add(new TrinhDoVanHoa(maTDVH, tenTDVH));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return trinhDoVanHoa;
    }

    @Override
    public boolean save(TrinhDoVanHoa trinhDoVanHoa) throws SQLException {

        System.out.println(INSERT_TRINHDOVANHOA_SQL);

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRINHDOVANHOA_SQL);

        preparedStatement.setString(1, trinhDoVanHoa.getTenTrinhDoVanHoa());

        System.out.println(preparedStatement);

        boolean rowSaved = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowSaved;

    }

    @Override
    public boolean update(int id, TrinhDoVanHoa trinhDoVanHoa) throws SQLException {
        System.out.println(UPDATE_TRINHDOVANHOA_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRINHDOVANHOA_SQL);
        preparedStatement.setString(1, trinhDoVanHoa.getTenTrinhDoVanHoa());
        preparedStatement.setInt(2, trinhDoVanHoa.getIdTrinhDoVanHoa());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        System.out.println(DELETE_TRINHDOVANHOA_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRINHDOVANHOA_SQL);
        preparedStatement.setInt(1, id);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    @Override
    public TrinhDoVanHoa findById(int id) throws SQLException {
        TrinhDoVanHoa trinhDoVanHoa = null;

        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_TRINHDOVANHOA_BYID_SQL);

        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            String tenTDVH = rs.getString("tenTDVH");

            trinhDoVanHoa = new TrinhDoVanHoa(id, tenTDVH);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return trinhDoVanHoa;
    }

    @Override
    public List<ThiSinh> danhSachThiSinh() throws SQLException {
        return null;
    }
}
