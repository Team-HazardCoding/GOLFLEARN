package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.golflearn.sql.MyConnection;

@WebServlet("/iddupchk")
public class IdDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("user_id");
		
		//DB
		Connection con = null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String idChkResult = "{\"status\":0 \" msg \":\" 이미 사용중인 아이디입니다.\"}";
		
		try {
			con = MyConnection.getConnection();
			String selectIdDupChkSQL = "SELECT * FROM user_info WHERE user_id = ?";
			pstmt = con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next() == false) {
				idChkResult = "{\"status\": 1 \" msg \":\" 사용가능한 아이디입니다.\"}";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		out.print(idChkResult);
	}

}
