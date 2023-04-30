/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Hang {
    protected String MaHang;
    protected String TenHang;
    protected String MaHangSanXuat;
    protected int SoLuong;
    protected int DonGiaNhap;
    protected int DonGiaBan; 
    private Date NgayNhap;
    private String AnhHang;
    public Hang() {
    }

    public Hang(String MaHang, String TenHang, String MaHangSanXuat, int SoLuong, int DonGiaNhap, int DonGiaBan, Date NgayNhap, String AnhHang) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.MaHangSanXuat = MaHangSanXuat;
        this.SoLuong = SoLuong;
        this.DonGiaNhap = DonGiaNhap;
        this.DonGiaBan = DonGiaBan;
        this.NgayNhap = NgayNhap;
        this.AnhHang = AnhHang;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getMaHangSanXuat() {
        return MaHangSanXuat;
    }

    public void setMaHangSanXuat(String MaHangSanXuat) {
        this.MaHangSanXuat = MaHangSanXuat;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getDonGiaNhap() {
        return DonGiaNhap;
    }

    public void setDonGiaNhap(int DonGiaNhap) {
        this.DonGiaNhap = DonGiaNhap;
    }

    public int getDonGiaBan() {
        return DonGiaBan;
    }

    public void setDonGiaBan(int DonGiaBan) {
        this.DonGiaBan = DonGiaBan;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public String getAnhHang() {
        return AnhHang;
    }

    public void setAnhHang(String AnhHang) {
        this.AnhHang = AnhHang;
    }

   
    
}
