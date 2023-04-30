/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 84375
 */
public class ConnectionUtil {
     public static void main(String[] args) {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=LKDienTu;user=sa;password=songlong;encrypt=true;trusServerCertificate=true;sslProtocol=TLSv1.2";

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String SQL = "select*from nhanvien";
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString("MaNV"));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("Eror: "+e);
        }
    }
}
