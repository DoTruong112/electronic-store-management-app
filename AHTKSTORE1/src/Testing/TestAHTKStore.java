package Testing;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import Controller.DB_Process;
import View.Login;
import junit.framework.AssertionFailedError;

public class TestAHTKStore {
	DB_Process p = new DB_Process();
//	Test nhập đúng thông tin tài khoản
	@Test()
	public void Run01() throws ClassNotFoundException, SQLException{
		String username ="TCLKT2";
		String pass = "0977019702";
		String chucvu="Quản lý";
		boolean check = p.isUser(username, pass,chucvu);
		assertTrue(check);
	}	
//	Test nhập sai thông tin tài khoản
	@Test()
	public void Run02() throws ClassNotFoundException, SQLException{
		String username ="TCLKT22";
		String pass = "0977019702";
		String chucvu="Quản lý";
		boolean check = p.isUser(username, pass,chucvu);
		assertTrue(check);
	}
//	Test bỏ trống thông tin tài khoản
	@Test()
	public void Run03() throws ClassNotFoundException, SQLException{
		String username ="TCLKT3";
		String pass = "";
		String chucvu="Quản lý";
		boolean check = p.isUser(username, pass,chucvu);
		assertTrue(check);
	}
// Test kiểm tra phân quyền
	@Test()
	public void Run04() throws ClassNotFoundException, SQLException{
		String username ="TCLKT4";
		String pass = "0976555676";
		String chucvu="Quản lý";
		boolean check = p.isUser(username, pass,chucvu);
		assertTrue(check);
		
	}
}
