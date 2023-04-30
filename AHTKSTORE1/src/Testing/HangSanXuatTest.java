package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Controller.HangSanXuatDataBase;
import Model.HangSanXuat;

public class HangSanXuatTest {
	HangSanXuatDataBase hxsdb = new HangSanXuatDataBase();
	//Test chức năng hiển thị danh sách hãng sản xuất
	@Test
	public void getHSX() {
		ArrayList<HangSanXuat> list = hxsdb.getHangSanXuat();
		assertTrue(list != null);
	}
	//Test chức năng insert hãng sản xuất 1: pass
	@Test
	public void insertHSX() {
		HangSanXuat hsx = new HangSanXuat(null, null);
		hsx.setMaHangSanXuat("HSX20");
		hsx.setTenangSanXuat("Realme");
		hxsdb.InsertHangSanXuat(hsx);
		assertEquals(hxsdb.getTenHangSanXuat("HSX20"),"Realme");
	}
	//Test chức năng insert hãng sản xuất 2: failed
	@Test
	public void insertHSX1() {
		HangSanXuat hsx = new HangSanXuat(null, null);
		hsx.setMaHangSanXuat("HSX19");
		hsx.setTenangSanXuat("Nokia");
		hxsdb.InsertHangSanXuat(hsx);
		assertEquals(hxsdb.getTenHangSanXuat("HSX19"),"Nokia");
	}
	//Test chức năng del hãng sản xuất 1: pass
	@Test
	public void delHSX() {
		boolean delHSX=hxsdb.DeleteHangSanXuat("HSX19");
		assertEquals(hxsdb.getTenHangSanXuat("HSX19"), "");
	}
	//Test chức năng update hãng sản xuất
	@Test
	public void updateHSX() {
		HangSanXuat hsx = new HangSanXuat("HSX1", "Acer");
		hsx.setTenangSanXuat("Accer");
		hxsdb.UpdateHangSanXuat(hsx);
		assertEquals(hxsdb.getTenHangSanXuat("HSX1"), "Accer");
	}
}
