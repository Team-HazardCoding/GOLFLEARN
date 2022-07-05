package com.golflearn.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.sql.MyConnection;

@WebServlet("/findid")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		String userName = request.getParameter("user_name");
		String userEmail = request.getParameter("user_email");
		
		//DB연결
		Connection con = null;
		//SQL 송신
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HttpSession session = request.getSession();
		session.removeAttribute("finIdInfo");
		
		try {
			con = MyConnection.getConnection();
			String selectIdSQL = "SELECT user_id FROM user_info WHERE user_name=? AND user_email=?";
			pstmt = con.prepareStatement(selectIdSQL);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.executeQuery();
			rs = pstmt.executeQuery();
			if()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
