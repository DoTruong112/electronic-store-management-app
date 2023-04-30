/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hang;
import Model.HangSanXuat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HangDatabase {
    DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
    public Date addDaysToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, hours);
        return calendar.getTime();
    }
    public ArrayList<Hang> GetHang(){
        ArrayList<Hang> list=new ArrayList<>();
        Connection cn=DB_Connection.getCon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement("select *from Hang");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Date date=addDaysToJavaUtilDate(rs.getDate("NgayNhapHang"), 0);
                Hang h=new Hang(rs.getString("MaHang"), rs.getString("TenHang"),rs.getString("MaHangSanXuat"),
                        rs.getInt("SoLuongTon"), rs.getInt("DonGiaNhap"), rs.getInt("DonGiaBan")
                        ,date,rs.getString("AnhHang"));
                list.add(h);
            }
            cn.close();
        } catch (Exception e) {
        }   
        return list;
    }
    
    public Hang GetHang(String MaHang){
        Hang hang=new Hang();
        Connection cn=DB_Connection.getCon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement("select * from Hang where MaHang='"+MaHang+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Date date=addDaysToJavaUtilDate(rs.getDate("NgayNhapHang"), 0);
                Hang h=new Hang(rs.getString("MaHang"), rs.getString("TenHang"),rs.getString("MaHangSanXuat"),
                        rs.getInt("SoLuongTon"), rs.getInt("DonGiaNhap"), rs.getInt("DonGiaBan"),
                        date,rs.getString("AnhHang"));
                hang=h;
            }
            cn.close();
        } catch (Exception e) {
        }   
        return hang;
    }
    
    
    public boolean deleteHang(String MaHang){
        Connection cn=DB_Connection.getCon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement("delete from Hang where MaHang = '"+MaHang+"'");
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean updateHang(Hang h){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("update Hang set TenHang =N'%s',SoLuongTon =%d,DonGiaNhap=%d,DonGiaBan=%d,MaHangSanXuat='%s',AnhHang=N'%s' where MaHang = '%s'",
                h.getTenHang(),h.getSoLuong(),h.getDonGiaNhap(),h.getDonGiaBan(),h.getMaHangSanXuat(),h.getAnhHang(),h.getMaHang());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true; 
        } catch (Exception e) {
        }
        return false;
    }
    
    public void insertHang(Hang h){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("insert into Hang values('%s',N'%s','%s',%d,%d,%d,'%s',N'%s')"
                ,h.getMaHang(),h.getTenHang(),h.getMaHangSanXuat(),h.getSoLuong(),h.getDonGiaNhap(),h.getDonGiaBan(),df.format(h.getNgayNhap()),h.getAnhHang());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
        } catch (Exception e) {
        }
    }
    
    public int getSoLuongTon(String MaHang){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("exec sp_SoLuongTon '%s'", MaHang);
        int SoLuongTon=0;
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                SoLuongTon=rs.getInt("SoLuongTon");
            }
        } catch (Exception e) {
            
        }
        return SoLuongTon;
    }
    
    
}
