package com.ChanhTin.service;

import com.ChanhTin.dao.LoginDAO;
import com.ChanhTin.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginService extends ValidateHelper {

    LoginDAO loginDAO = new LoginDAO();

    public LoginService() {
    }

    public ArrayList<String> result(User user) throws SQLException {
        ArrayList<String> danhSachLoi = new ArrayList<>();
        if (!validateString(user.getUserName(), 45, 5))
            danhSachLoi.add("Lỗi nhập User Name");
        if (!validateString(user.getPassword(), 45, 5)) ;
        danhSachLoi.add("Lỗi nhập Password");

        if (danhSachLoi.size() == 0)
            danhSachLoi.add("200");
        return danhSachLoi;
    }

    public List<User> danhSach() throws SQLException {
        return new ArrayList<>(loginDAO.getAll());
    }

    public ArrayList<String> them(User user) throws SQLException {
        ArrayList<String> danhSachLoi = result(user);
        if (danhSachLoi.get(0).equals("200"))
            if (kiemTraTrungNhau(user))
                danhSachLoi.set(0, "User này đã tồn tại");
            else loginDAO.save(user);
        return danhSachLoi;
    }

    public ArrayList<String> sua(User user) throws SQLException {
        ArrayList<String> danhSachLoi = result(user);
        User userBanDau = timTheoUserName(user.getUserName());
        if (danhSachLoi.get(0).equals("200"))
            if (userBanDau.equals(user))
                danhSachLoi.set(0, "Không thay đổi gì");
            else loginDAO.update(user);
        return danhSachLoi;
    }

    public String xoa(String userName) throws SQLException {
        if (loginDAO.delete(userName))
            return "Xóa thành công";
        return "Xóa không thành công";
    }

    public User timTheoUserName(String userName) throws SQLException{
        return loginDAO.findByUserName(userName);
    }

    public User timTheoUserNamePassword(String userName, String passWord) throws SQLException {
        return loginDAO.findByUserNamePassWord(userName, passWord);
    }

    public boolean kiemTraTrungNhau(User user) throws SQLException {
        ArrayList<User> danhSachUser = new ArrayList<>(danhSach());
        for (User userBanDau : danhSachUser)
            if (userBanDau.equals(user))
                return true;
        return false;
    }
}
