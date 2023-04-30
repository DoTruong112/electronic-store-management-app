package Testing;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;

import Controller.HangDatabase;
import Model.Hang;

public class HangTest {
	HangDatabase hdb = new HangDatabase();
	//Test hiển thị danh sách hàng hóa
	@Test
	public void getHang() {
		ArrayList<Hang> list = hdb.GetHang();
		assertTrue(list!=null);
	}
	//Test chức năng update hàng hóa
	@Test
	public void updateHang() {
		Hang h = hdb.GetHang("MH10");
		h.setSoLuong(10);
		hdb.updateHang(h);
		assertEquals(hdb.GetHang("MH10").getSoLuong(), 10);
	}
	//Test chức năng tìm kiếm hàng
	@Test 
	public void findHang() {
		Hang h = hdb.GetHang("MH10");
		assertEquals(h.getTenHang(), "Màn hình Asus VG279Q");
		
	}
	//Test chức năng xóa hàng hóa
	@Test
	public void delHang() {
		String h = "MH22";
		boolean del = hdb.deleteHang(h);
		assertTrue(del);
		
	}
	//Test insert Product
	@Test 
	public void insertHang() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
		Hang h = new Hang();
		h.setMaHang("MH22");
		h.setTenHang("Chuot khong day");
		h.setMaHangSanXuat("HSX9");
		h.setSoLuong(10);
		h.setDonGiaNhap(250000);
		h.setDonGiaBan(270000);
		try {
			h.setNgayNhap(fm.parse("2023/12/01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		h.setAnhHang("Không có");
		hdb.insertHang(h);
		assertEquals(hdb.GetHang("MH22").getTenHang(),"Chuot khong day");
		
	}
	//Test hiển thị số lượng tồn của Hàng Hóa trong kho
	@Test 
	public void soLuongton() {
		assertEquals(hdb.getSoLuongTon("MH10"), 8);
	}

}
