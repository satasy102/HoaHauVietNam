package com.ChanhTin.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BaseService<G> {

    List<String> result(G object) throws SQLException;

    List<G> danhSach() throws SQLException;

    List<G> danhSachCoPhanTrang(int idTrang, int soHangMotTrang) throws SQLException;

    ArrayList<String> them(G object) throws SQLException;

    ArrayList<String> sua(int id, G object) throws SQLException;

    String xoa(int id) throws SQLException;

    G timTheoID(int id) throws SQLException;

    boolean kiemTraTrungNhau(G object) throws SQLException;

}
