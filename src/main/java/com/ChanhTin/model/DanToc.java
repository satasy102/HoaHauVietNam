package com.ChanhTin.model;

import java.util.List;
import java.util.Objects;

public class DanToc {
    private int idDanToc;
    private String tenDanToc;
    private List<ThiSinh> danhSachThiSinh;

    public DanToc() {}

    public DanToc(int idDanToc, String tenDanToc) {
        this.idDanToc = idDanToc;
        this.tenDanToc = tenDanToc;
    }

    public DanToc(String tenDanToc){
        this.tenDanToc=tenDanToc;
    }

    public int getIdDanToc() {
        return idDanToc;
    }

    public String getTenDanToc() {
        return tenDanToc;
    }

    public void setTenDanToc(String tenDanToc) {
        this.tenDanToc = tenDanToc;
    }

    public List<ThiSinh> getDanhSachThiSinh() {
        return danhSachThiSinh;
    }

    public void setDanhSachThiSinh(List<ThiSinh> danhSachThiSinh) {
        this.danhSachThiSinh = danhSachThiSinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DanToc danToc = (DanToc) o;
        return tenDanToc.equals(danToc.tenDanToc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenDanToc);
    }
}
