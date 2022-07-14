package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.golflearn.sql.MyConnection;

public class UserSearchInfo {
	public String selectEmail() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = MyConnection.getConnection();
			String selectEmailSQL = "SELECT user_email FROM user_info WHERE user_id=? AND user_email=?";
		} catch (SQLException e) {
			
		}
		return null;
		
	}

}
