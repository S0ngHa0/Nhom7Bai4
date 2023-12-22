package com.example.nhom7bai4;

public class SanPham {
    private String maSP;
    private String tenSP;
    private int soLuong;
    private String loaiSP;

    public SanPham(String maSP, String tenSP, int soLuong, String loaiSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.loaiSP = loaiSP;
    }

    public SanPham() {

    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = Integer.parseInt(soLuong);
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    @Override
    public String toString() {
        return "Mã SP: " + maSP + ", Tên SP: " + tenSP + ", Số lượng: " + soLuong + ", Loại SP: " + loaiSP;
    }
}
