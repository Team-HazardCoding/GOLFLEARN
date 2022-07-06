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
import javax.servlet.http.HttpSession;

import com.golflearn.sql.MyConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		String userPwd = request.getParameter("user_pwd");
		
		//DB연결
		Connection con = null;
		
		//SQL 연결
		PreparedStatement pstmt = null;
		//송신
		ResultSet rs = null;

		String loginResult = "{\"status\":0 \" msg \":\"로그인 실패\"}";
		
		//세션 객체 얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginId");
		
		try {
			con = MyConnection.getConnection();
			String selectUserIdNPwd = "SELECT * FROM user_info WHERE user_id = ? AND user_pwd = ? ;";
			pstmt = con.prepareStatement(selectUserIdNPwd);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 로그인 성공
				loginResult = "{\"status\":1 \" msg \":\"로그인 성공\"}";
				session.setAttribute("loginId", userId); // loginId 저장
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}

		response.setContentType("application/json:UTF-8");
		PrintWriter out = response.getWriter();
		out.print(loginResult);
		
	}

}
