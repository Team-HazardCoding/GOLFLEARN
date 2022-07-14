package com.golflearn.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
	public static void test(){
		//JDBC드라이버 클래스 로드 
		System.out.println(1);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(1);
		//DB연결
		Connection con = null; 
		String url = "jdbc:oracle:thin:@jongwoo_medium?TNS_ADMIN=C://Golflearn_lib/전자지갑/Wallet_Jongwoo";
		String user = "admin";
		String password = "Hazard11241124";
		
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String user = "hr";
//		String password = "hr";
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(1);
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			String selectSQL = "SELECT * FROM lesson";
//			String selectSQL = "SELECT * FROM employees";
			rs = stmt.executeQuery(selectSQL);
			
			while(rs.next()) {
//				int id = rs.getInt("lsn_no");
				System.out.println("good");
				System.out.println("id");
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		test();
		
	}

}
