package com.golflearn.control;

import java.sql.SQLException;

import com.golflearn.sql.MyConnection;

public class DbTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
