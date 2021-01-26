package com.ChanhTin.model;

import java.sql.Date;
import java.util.Objects;

public class ThiSinh {
    private int idThiSinh;
    private String hoTen;
    private Date ngaySinh;
    private String diaChiCuTru;
    private String sdt;
    private String email;
    private String chungMinhNhanDan;
    private String ngheNghiep;
    private int idDanToc;
    private String donViCongTac;
    private float chieuCao;
    private float canNang;
    private String nangKhieuKhac;
    private String anhCaNhan;
    private String trangThaiDuyet = "Chờ duyệt";
    private int idTinh;
    private int idTrinhDoVH;
    private TinhThanh tinhThanh;
    private TrinhDoVanHoa trinhDoVanHoa;
    private DanToc danToc;

    public ThiSinh() {
    }

    public ThiSinh(int idThiSinh, String hoTen, Date ngaySinh, String diaChiCuTru, String sdt, String email,
                   String chungMinhNhanDan, String ngheNghiep, int idTrinhDoVH, int idDanToc,
                   String donViCongTac, float chieuCao, float canNang, String nangKhieuKhac,
                   String anhCaNhan, int idTinh, String trangThaiDuyet) {
        this.idThiSinh = idThiSinh;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChiCuTru = diaChiCuTru;
        this.sdt = sdt;
        this.email = email;
        this.chungMinhNhanDan = chungMinhNhanDan;
        this.ngheNghiep = ngheNghiep;
        this.idTrinhDoVH = idTrinhDoVH;
        this.idDanToc = idDanToc;
        this.donViCongTac = donViCongTac;
        this.chieuCao = chieuCao;
        this.canNang = canNang;
        this.nangKhieuKhac = nangKhieuKhac;
        this.anhCaNhan = anhCaNhan;
        this.idTinh = idTinh;
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public ThiSinh(int idThiSinh, String hoTen, Date ngaySinh, String diaChiCuTru, String sdt, String email,
                   String chungMinhNhanDan, String ngheNghiep, int idTrinhDoVH, int idDanToc,
                   String donViCongTac, float chieuCao, float canNang, String nangKhieuKhac,
                   String anhCaNhan, int idTinh) {
        this.idThiSinh = idThiSinh;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChiCuTru = diaChiCuTru;
        this.sdt = sdt;
        this.email = email;
        this.chungMinhNhanDan = chungMinhNhanDan;
        this.ngheNghiep = ngheNghiep;
        this.idTrinhDoVH = idTrinhDoVH;
        this.idDanToc = idDanToc;
        this.donViCongTac = donViCongTac;
        this.chieuCao = chieuCao;
        this.canNang = canNang;
        this.nangKhieuKhac = nangKhieuKhac;
        this.anhCaNhan = anhCaNhan;
        this.idTinh = idTinh;
    }

    public ThiSinh(String hoTen, Date ngaySinh, String diaChiCuTru, String sdt, String email,
                   String chungMinhNhanDan, String ngheNghiep, int idTrinhDoVH, int idDanToc,
                   String donViCongTac, float chieuCao, float canNang, String nangKhieuKhac,
                   String anhCaNhan, int idTinh, String trangThaiDuyet) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChiCuTru = diaChiCuTru;
        this.sdt = sdt;
        this.email = email;
        this.chungMinhNhanDan = chungMinhNhanDan;
        this.ngheNghiep = ngheNghiep;
        this.idTrinhDoVH = idTrinhDoVH;
        this.idDanToc = idDanToc;
        this.donViCongTac = donViCongTac;
        this.chieuCao = chieuCao;
        this.canNang = canNang;
        this.nangKhieuKhac = nangKhieuKhac;
        this.anhCaNhan = anhCaNhan;
        this.idTinh = idTinh;
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public ThiSinh(String hoTen, Date ngaySinh, String diaChiCuTru, String sdt, String email,
                   String chungMinhNhanDan, String ngheNghiep, int idTrinhDoVH, int idDanToc,
                   String donViCongTac, float chieuCao, float canNang, String nangKhieuKhac,
                   String anhCaNhan, int idTinh) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChiCuTru = diaChiCuTru;
        this.sdt = sdt;
        this.email = email;
        this.chungMinhNhanDan = chungMinhNhanDan;
        this.ngheNghiep = ngheNghiep;
        this.idTrinhDoVH = idTrinhDoVH;
        this.idDanToc = idDanToc;
        this.donViCongTac = donViCongTac;
        this.chieuCao = chieuCao;
        this.canNang = canNang;
        this.nangKhieuKhac = nangKhieuKhac;
        this.anhCaNhan = anhCaNhan;
        this.idTinh = idTinh;
    }

    public int getIdThiSinh() {
        return idThiSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChiCuTru() {
        return diaChiCuTru;
    }

    public void setDiaChiCuTru(String diaChiCuTru) {
        this.diaChiCuTru = diaChiCuTru;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChungMinhNhanDan() {
        return chungMinhNhanDan;
    }

    public void setChungMinhNhanDan(String chungMinhNhanDan) {
        this.chungMinhNhanDan = chungMinhNhanDan;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public int getIdTrinhDoVH() {
        return idTrinhDoVH;
    }

    public void setIdTrinhDoVH(int idTrinhDoVH) {
        this.idTrinhDoVH = idTrinhDoVH;
    }

    public int getIdDanToc() {
        return idDanToc;
    }

    public void setIdDanToc(int idDanToc) {
        this.idDanToc = idDanToc;
    }

    public String getDonViCongTac() {
        return donViCongTac;
    }

    public void setDonViCongTac(String donViCongTac) {
        this.donViCongTac = donViCongTac;
    }

    public float getChieuCao() {
        return chieuCao;
    }

    public void setChieuCao(float chieuCao) {
        this.chieuCao = chieuCao;
    }

    public float getCanNang() {
        return canNang;
    }

    public void setCanNang(float canNang) {
        this.canNang = canNang;
    }

    public String getNangKhieuKhac() {
        return nangKhieuKhac;
    }

    public void setNangKhieuKhac(String nangKhieuKhac) {
        this.nangKhieuKhac = nangKhieuKhac;
    }

    public String getAnhCaNhan() {
        return anhCaNhan;
    }

    public void setAnhCaNhan(String anhCaNhan) {
        this.anhCaNhan = anhCaNhan;
    }

    public int getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(int idTinh) {
        this.idTinh = idTinh;
    }

    public String getTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(String trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public TinhThanh getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(TinhThanh tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public TrinhDoVanHoa getTrinhDoVanHoa() {
        return trinhDoVanHoa;
    }

    public void setTrinhDoVanHoa(TrinhDoVanHoa trinhDoVanHoa) {
        this.trinhDoVanHoa = trinhDoVanHoa;
    }

    public DanToc getDanToc() {
        return danToc;
    }

    public void setDanToc(DanToc danToc) {
        this.danToc = danToc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThiSinh thiSinh = (ThiSinh) o;
        return  idDanToc == thiSinh.idDanToc &&
                Float.compare(thiSinh.chieuCao, chieuCao) == 0 &&
                Float.compare(thiSinh.canNang, canNang) == 0 &&
                idTinh == thiSinh.idTinh &&
                idTrinhDoVH == thiSinh.idTrinhDoVH &&
                hoTen.equalsIgnoreCase(thiSinh.hoTen) &&
                ngaySinh.equals(thiSinh.ngaySinh) &&
                diaChiCuTru.equalsIgnoreCase(thiSinh.diaChiCuTru) &&
                sdt.equalsIgnoreCase(thiSinh.sdt) &&
                email.equalsIgnoreCase(thiSinh.email) &&
                chungMinhNhanDan.equalsIgnoreCase(thiSinh.chungMinhNhanDan) &&
                ngheNghiep.equalsIgnoreCase(thiSinh.ngheNghiep) &&
                donViCongTac.equalsIgnoreCase(thiSinh.donViCongTac) &&
                nangKhieuKhac.equalsIgnoreCase(thiSinh.nangKhieuKhac) &&
                anhCaNhan.equalsIgnoreCase(thiSinh.anhCaNhan)&&
                trangThaiDuyet.equalsIgnoreCase(thiSinh.trangThaiDuyet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoTen, ngaySinh, diaChiCuTru, sdt, email, chungMinhNhanDan, ngheNghiep, idDanToc,
                donViCongTac, chieuCao, canNang, nangKhieuKhac, anhCaNhan, idTinh, idTrinhDoVH);
    }
}
