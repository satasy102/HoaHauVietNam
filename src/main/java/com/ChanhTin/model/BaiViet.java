package com.ChanhTin.model;

import java.util.Objects;

public class BaiViet {
    private int maBaiViet;
    private String tieuDe;
    private String noiDungTomTat;
    private String noiDung;
    private String anhTieuDe;

    public BaiViet() {
    }

    public BaiViet(int maBaiViet, String tieuDe, String noiDungTomTat, String noiDung, String anhTieuDe) {
        this.maBaiViet = maBaiViet;
        this.tieuDe = tieuDe;
        this.noiDungTomTat = noiDungTomTat;
        this.noiDung = noiDung;
        this.anhTieuDe= anhTieuDe;
    }

    public BaiViet(String tieuDe, String noiDungTomTat, String noiDung, String anhTieuDe) {
        this.tieuDe = tieuDe;
        this.noiDungTomTat = noiDungTomTat;
        this.noiDung = noiDung;
        this.anhTieuDe= anhTieuDe;
    }

    public int getMaBaiViet() {
        return maBaiViet;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDungTomTat() {
        return noiDungTomTat;
    }

    public void setNoiDungTomTat(String noiDungTomTat) {
        this.noiDungTomTat = noiDungTomTat;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getAnhTieuDe() {
        return anhTieuDe;
    }

    public void setAnhTieuDe(String anhTieuDe) {
        this.anhTieuDe = anhTieuDe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaiViet baiViet = (BaiViet) o;
        return  tieuDe.equals(baiViet.tieuDe) &&
                noiDungTomTat.equals(baiViet.noiDungTomTat) &&
                noiDung.equals(baiViet.noiDung) &&
                anhTieuDe.equals(baiViet.anhTieuDe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tieuDe, noiDungTomTat, noiDung, anhTieuDe);
    }
}
