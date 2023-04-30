package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Controller.KhachHangDatabase;
import Model.KhachHang;

public class KhachHangTest {
	KhachHangDatabase khdb = new KhachHangDatabase();
	//Test hiển thị danh sách khách hàng
	@Test
	public void getKH() {
		ArrayList<KhachHang> list = khdb.getKhachHang();
		assertTrue(list!=null);
	}
	//Test insert Khách hàng
	@Test
	public void insertKH() {
		KhachHang kh = new KhachHang();
		kh.setMaKhach("KH23");
		kh.setTenKhach("Noo Phước Thịnh");
		kh.setGioiTinh("Nam");
		kh.setDiaChi("Tp HCM");
		kh.setSDT("0378864137");
		khdb.InsertKhachHang(kh);
		assertEquals(khdb.getTenKhachHang("KH22"), "Noo Phước Thịnh");
	}
	//Test del khách hàng
	@Test
	public void delKH() {
		boolean delKH = khdb.DeleteKhachHang("KH23");
		assertTrue(delKH);
	}
	//Test đếm số lượng khách hàng
	@Test 
	public void countKH() {
		assertEquals(khdb.IndexKhachHang(), 23);
	}
}
