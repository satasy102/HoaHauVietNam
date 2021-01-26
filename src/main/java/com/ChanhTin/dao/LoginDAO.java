package com.ChanhTin.dao;

import com.ChanhTin.model.User;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO extends DAOHelper {

    private static final String SELECT_ALL_USER_SQL;
    private static final String INSERT_USER_SQL;
    private static final String UPDATE_USER_SQL;
    private static final String DELETE_USER_SQL;
    private static final String GET_USER_BY_USERNAME_SQL;
    private static final String GET_USER_BY_USERNAME_PASSWORD_SQL;

    static {
        SELECT_ALL_USER_SQL = "select * from public.rigistration where \"trangThaiXoa\"=false;";
        INSERT_USER_SQL = "INSERT INTO public.rigistration (\"userName\",\"passWord\", \"isAdmin\")" +
                "VALUES (?,?,?,?);";
        UPDATE_USER_SQL = "update public.rigistration set \"passWord\"= ?, \"isAdmin\"= ?" +
                "where userName = ?;";
        DELETE_USER_SQL = "update public.rigistration set \"trangThaiXoa\"=true where \"userName\" = ?;";
        GET_USER_BY_USERNAME_SQL = "select * from public.rigistration where \"userName\"=? and \"trangThaiXoa\"=false;";
        GET_USER_BY_USERNAME_PASSWORD_SQL="select * from public.rigistration where \"userName\"=? and \"passWord\"=? and \"trangThaiXoa\"=false;";
    }

    public List<User> getAll() throws SQLException {
        List<User> danhSachUser = new ArrayList<>();

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_SQL);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String userName = rs.getString("userName");
            String passWord = rs.getString("passWord");
            boolean isAdmin = Boolean.parseBoolean(rs.getString("passWord"));
            danhSachUser.add(new User(userName, passWord, isAdmin));
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return danhSachUser;
    }

    public boolean save(User user) throws SQLException {
        System.out.println(INSERT_USER_SQL);

        connection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);

        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setBoolean(3, user.isAdmin());

        System.out.println(preparedStatement);

        boolean rowSaved = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowSaved;
    }

    public boolean update(User user) throws SQLException {
        System.out.println(UPDATE_USER_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setBoolean(2, user.isAdmin());
        preparedStatement.setString(3, user.getUserName());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }


    public boolean delete(String userName) throws SQLException {
        System.out.println(DELETE_USER_SQL);
        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
        preparedStatement.setString(1, userName);

        boolean rowDeleted=preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    public User findByUserName(String userName) throws SQLException{
        User user = null;

        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME_SQL);

        preparedStatement.setString(1, userName);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            String passWord = rs.getString("passWord");
            boolean isAdmin = rs.getBoolean("isAdmin");

            user = new User(userName, passWord, isAdmin);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return user;
    }

    public User findByUserNamePassWord(String userName, String pass) throws SQLException{
        User user = null;

        connection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME_PASSWORD_SQL);

        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, pass);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String passWord = rs.getString("passWord");
            boolean isAdmin = rs.getBoolean("isAdmin");
            user = new User(userName, passWord, isAdmin);
        }

        rs.close();
        preparedStatement.close();
        disconnect();

        return user;
    }


}
