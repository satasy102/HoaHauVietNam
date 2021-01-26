package com.ChanhTin.model;

import java.util.List;
import java.util.Objects;

public class TinhThanh {
    private int idTinh;
    private String tenTinh;
    List<ThiSinh> danhSachThiSinh;


    public TinhThanh() {}

    public TinhThanh(int idTinh, String tenTinh) {
        this.idTinh = idTinh;
        this.tenTinh = tenTinh;
    }

    public TinhThanh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public int getIdTinh() { return idTinh;}

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public List<ThiSinh> getDanhSachThiSinh() { return danhSachThiSinh;}

    public void setDanhSachThiSinh(List<ThiSinh> danhSachThiSinh) { this.danhSachThiSinh = danhSachThiSinh;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TinhThanh tinhThanh = (TinhThanh) o;
        return tenTinh.equals(tinhThanh.tenTinh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenTinh);
    }
}
