package Testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Controller.NhanVienDataBase;
import Model.NhanVien;

public class NhanVienTest {
	NhanVienDataBase nvdb = new NhanVienDataBase();
	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

	// Test hiển thị toàn bộ danh sách nhân viên
	@Test
	public void getNhanVien() {
		ArrayList<NhanVien> list = nvdb.GetNhanVien();
		assertTrue(list != null);
	}

	// Test tìm kiếm nhân viên
	@Test
	public void findNV() {
		String nv = nvdb.getTenNhanVien("TCLKT2");
		assertEquals(nv, "Hoàng Thị Mai");
	}

	// Test xóa nhân viên
	@Test
	public void deleteNV() {
		String maNV = "TCLKT9";
		boolean nvdel = nvdb.DeleteNhanVien(maNV);
		assertTrue(nvdel);
	}

	// Test add Nhân viên
	@Test
	public void addNV() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
		NhanVien nv = new NhanVien();
		nv.setMaNV("TCLKT9");
		nv.setTenNV("Bùi Hoàng Bảo Uyên");
		nv.setDiaChi("Cao Bằng");
		nv.setGioiTinh("Nữ");
		nv.setAnhNV("NV2.png");
		try {
			nv.setNgaySinh(fm.parse("2023/12/01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nv.setSDT("0375589624");
		nv.setChucVu("Nhân viên");
		nvdb.InsertNhanVien(nv);
		assertEquals(nvdb.getTenNhanVien("TCLKT9"), "Bùi Hoàng Bảo Uyên");

	}

	// Hiển thị số nhân viên trong danh sách
	@Test
	public void countNV() {
		int index = 8;
		int countNV = nvdb.IndexNhanVien();
		assertEquals(index, countNV);
	}

	// Test chức năng update Nhân viên
	@Test
	public void updateNV() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
		NhanVien nv;
		try {
			nv = new NhanVien("TCLKT1", "Nguyễn Quốc Hiệu", "Nam", fm.parse("2000/11/13"), "Hà Nội", "0339657164",
					"Nhân Viên", "NV2.png");
			nv.setTenNV("Nguyễn Trung Hiếu");
			nvdb.UpdateNhanVien(nv);
			assertEquals(nvdb.getTenNhanVien("TCLKT1"), "Nguyễn Trung Hiếu");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
