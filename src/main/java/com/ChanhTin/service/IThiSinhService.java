package com.ChanhTin.service;

import com.ChanhTin.model.ThiSinh;

import java.sql.SQLException;
import java.util.List;

public interface IThiSinhService extends BaseService<ThiSinh> {

    List<ThiSinh> timTheoTen(String ten) throws SQLException;

    List<ThiSinh> timTheoTinhThanh(int idTinh) throws SQLException;

    List<ThiSinh> timTheoDanToc(int idTinh) throws SQLException;

    List<ThiSinh> timTheoTrinhDoVH(int idTinh) throws SQLException;

    List<ThiSinh> danhSachDaDuyet() throws SQLException;

    List<ThiSinh> danhSachChuaDuyet() throws SQLException;

    List<ThiSinh> danhSachBiLoai() throws SQLException;

    void setTinhThanhDanTocTDVH(int maTDVH, int maDanToc, int maTP, ThiSinh thiSinh) throws SQLException;
}
