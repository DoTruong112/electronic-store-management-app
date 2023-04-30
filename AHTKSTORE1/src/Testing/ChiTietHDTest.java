package Testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import Model.ChiTietHD;
import Model.HoaDon;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import Controller.ChiTietHDDataBase;

public class ChiTietHDTest {
	ChiTietHDDataBase cthddb = new ChiTietHDDataBase();
	//Test số lượng hóa đơn mang mã hoá đơn "HDB20221201|18"
	@Test
	public void getCTHD() {
		ArrayList<ChiTietHD> list = cthddb.getChiTietHD("HDB20221201|18");
		int size = list.size();
		assertTrue(size == 4);
		
	}
	//Test insert chi tiết hóa đơn
	@Test
	public void insertCTHD() {
		ChiTietHD cthd = new ChiTietHD();
		cthd.setMaHD("HDB20201106|8");
		cthd.setMaHang("MH12");
		cthd.setSoLuong(3);
		cthd.setGiamGia(0);
		cthd.setThanhTien(387000);
		boolean newcthd  = cthddb.insertCHTHD(cthd);
		assertTrue(newcthd);
			
	}
	//Test delete chi tiết hóa đơn
	@Test
	public void delCTHD() {
		boolean delCTHD = cthddb.deleteCHTHD("HDB20201106|7");
		assertTrue(delCTHD);
	}

	
	
}
