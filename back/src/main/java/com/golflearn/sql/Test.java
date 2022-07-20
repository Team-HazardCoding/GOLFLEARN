package com.golflearn.sql;

import java.sql.SQLException;

public class Test {
	public static void main(String[] args) {
		MyConnection my = new MyConnection();
		try {
			my.getConnection();
			System.out.println("hi");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
