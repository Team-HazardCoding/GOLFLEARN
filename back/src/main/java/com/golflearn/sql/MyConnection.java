package com.golflearn.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	static { //MyConnection mc; 클래스 로드시 static블럭 자동 호출됨
		// 한번만 호출될 때 static {} 사용 가능
		//public MyConnection(){ //생성자. 객체가 생성될 때 생성자 자동 호출됨
		//2. JDBC 드라이버 클래스 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("jdbc driver 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	public static Connection getConnection() throws SQLException { //non-static 은 객체가 먼저 생성 되어야 사용할 수 있다.
		// static으로 선언되는 method는 클래스 이름. 으로 사용하는게 best

		//3. DB와 연결
		Connection con = null ;
//		String url = "jdbc:oracle:thin:@localhost:1124:xe"; 
		String url = "jdbc:oracle:thin:@jongwoo_medium?TNS_ADMIN=C://Golflearn_lib/전자지갑/Wallet_Jongwoo";
		String user = "admin";
		String password = "Hazard11241124";

		con = DriverManager.getConnection(url, user, password);
		return con; 
		
	}
	public static void close(ResultSet rs, Statement stmt, Connection con) {
		//7. DB 연결해제
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt !=null) {
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
	public static void close(Statement stmt, Connection con) {
		close(null, stmt, con);
		// 이 한 줄 코드로 Statement와 Connection 다 해결 할 수 있음
	}
}


