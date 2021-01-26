package com.ChanhTin.dao;

import com.ChanhTin.model.ThiSinh;

import java.sql.SQLException;
import java.util.List;

public interface IThiSinhDAO extends BaseDAO<ThiSinh> {

    List<ThiSinh> findByName(String ten) throws SQLException;

    List<ThiSinh> findByTinhThanh(int idTinhThanh) throws SQLException;

    List<ThiSinh> findByDanToc(int idDanToc) throws SQLException;

    List<ThiSinh> findByTDVH(int idTDVH) throws SQLException;

    List<ThiSinh> danhSachDaDuyet() throws SQLException;

    List<ThiSinh> danhSachChuaDuyet() throws SQLException;

    List<ThiSinh> danhSachBiLoai() throws SQLException;

    void setTinhThanhDanTocTDVH(int maTDVH, int maDanToc, int maTP, ThiSinh thiSinh) throws SQLException;

}
