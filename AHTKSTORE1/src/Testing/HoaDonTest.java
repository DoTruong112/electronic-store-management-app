package Testing;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;

import Controller.HoaDonDataBase;
import Controller.KhachHangDatabase;
import Model.HoaDon;
import Model.KhachHang;

public class HoaDonTest {
	HoaDonDataBase hddb = new HoaDonDataBase();
	//Test hiển thị danh sách hóa đơn
	@Test 
	public void getHoaDon() {
		ArrayList<HoaDon> list = hddb.getHoaDon();
		assertTrue(list != null);
	}
	//Test insert hóa đơn
	@Test 
	public void insertHD() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
		HoaDon hd = new HoaDon();
		hd.setMaHD("HDB20230325|22");
		hd.setMaNV("TCLKT3");
		hd.setMaKhach("KH1");
		try {
			hd.setNgayBan(fm.parse("2023/03/05"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hd.setTongTien(0);
		hddb.insertHoaDon(hd);
		assertEquals(hddb.getHoaDon("HDB20230325|22").getMaNV(), "TCLKT3");
	}
	//Test del hóa đơn
	@Test
	public void delHD() {
		boolean delHD = hddb.deleteHD("HDB20230325|22");
		assertTrue(delHD);
	}
	//Test tìm kiếm hóa đơn
	@Test 
	public void getHD() {
		HoaDon hd = hddb.getHoaDon("HDB20221216|20");
		assertEquals(hddb.getHoaDon("HDB20221216|20").getMaKhach(), "KH9");
	}
	//Test update hóa đơn
	@Test
	public void updateHD() {
		HoaDon hd = hddb.getHoaDon("HDB20221203|19");
		hd.setMaKhach("KH2");
		hddb.UpdateHD(hd);
		assertEquals(hddb.getHoaDon("HDB20221203|19").getMaKhach(),"KH2");
	}
	//Test hiển thị tổng số hóa đơn
	@Test
	public void tongHD() {
		int tongHD = hddb.TongSoHD("2000/01/01", "2023/01/01");
		assertEquals(tongHD,19);
	}
	//Test hiển thị doanh thu của Hóa đơn
	@Test
	public void DoanhThu() {
		int doanhthu = hddb.DoanhThu("2000/01/01", "2023/01/01");
		assertEquals(doanhthu,714180150);
	}
	//Test hiển thị lợi nhuận hóa đơn
	@Test
	public void loiNhuan() {
		int loinhuan = hddb.LoiNhuan("2000/01/01", "2023/01/01");
		assertEquals(loinhuan,124870150);
	}
	
	public void DeleteBillHD(){
		boolean delHD = hddb.deleteHD("HDB20230325|22");
		assertTrue(delHD);
	}

}
