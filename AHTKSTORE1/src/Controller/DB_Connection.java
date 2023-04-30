/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DB_Connection {
//    public static final String connectioneUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLCuaHangMayTinhText;user=sa;password=songlong";
    public static Connection getCon(){
      
        try{
            String url="jdbc:sqlserver://LAPTOP-96ECOHOM\\SQLEXPRESS;encrypt=false";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LKDienTu","sa","songlong");
        }
        catch(Exception e){
            System.out.println("Error: "+e);
             return null;
        }
       
        
    }
    public static void main(String[] args) {
        System.out.println(getCon());
    }
}

