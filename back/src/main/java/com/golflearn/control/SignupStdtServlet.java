package com.golflearn.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.golflearn.sql.MyConnection;

@WebServlet("/signupstdt")
public class SignupStdtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id"); // html의 name 지정시 참고
		String userName = request.getParameter("user_name");
		String userPwd = request.getParameter("user_pwd");
		String userEmail = request.getParameter("user_email");
		String userPhone = request.getParameter("user_phone");
		String userSsn = request.getParameter("user_ssn");
		String sysDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		String signupResult = "{\"status\":0, \"msg\": \"가입실패\"}";
				
		// DB와 연결
		Connection con = null;
		
		//SQL 송신
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			con = MyConnection.getConnection();
			String insertStdtInfoSQL= "INSERT INTO user_info(user_id, user_name, user_pwd, user_email,"
					+ "user_phone,user_ssn,user_join_dt, user_quit_dt,user_type)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ? , 0)";
			pstmt = con.prepareStatement(insertStdtInfoSQL);
			pstmt.setString(1, "userId");
			pstmt.setString(2, "userName");
			pstmt.setString(3, "userPwd");
			pstmt.setString(4, "userEmail");
			pstmt.setString(5, "userPhone");
			pstmt.setString(6, "userSsn");
			pstmt.setString(7, "sysDate");
			pstmt.setString(8, null);
			rs = pstmt.executeUpdate(); // DB에 결과값 보내줌
			
			if(rs == 1) { // rs = true이면
				signupResult = "{\"status\":1, \"msg\": \"가입성공\"}";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt,con); // DB연결 해제
		}
		
	
		
	}

}
