package com.ChanhTin.model;

import java.util.List;
import java.util.Objects;

public class TrinhDoVanHoa {
    private int idTrinhDoVanHoa;
    private String tenTrinhDoVanHoa;

    private List<ThiSinh> danhSachThiSinh;

    public TrinhDoVanHoa() {
    }

    public TrinhDoVanHoa(int idTrinhDoVanHoa, String tenTrinhDoVanHoa) {
        this.idTrinhDoVanHoa = idTrinhDoVanHoa;
        this.tenTrinhDoVanHoa = tenTrinhDoVanHoa;
    }

    public TrinhDoVanHoa(String tenTrinhDoVanHoa) {
        this.tenTrinhDoVanHoa = tenTrinhDoVanHoa;
    }


    public int getIdTrinhDoVanHoa() {
        return idTrinhDoVanHoa;
    }

    public String getTenTrinhDoVanHoa() {
        return tenTrinhDoVanHoa;
    }

    public void setTenTrinhDoVanHoa(String tenTrinhDoVanHoa) {
        this.tenTrinhDoVanHoa = tenTrinhDoVanHoa;
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
        TrinhDoVanHoa that = (TrinhDoVanHoa) o;
        return tenTrinhDoVanHoa.equals(that.tenTrinhDoVanHoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenTrinhDoVanHoa);
    }
}
