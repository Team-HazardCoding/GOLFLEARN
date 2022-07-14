package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.sql.MyConnection;

@WebServlet("/signuppro")
@MultipartConfig
public class SignupProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 이 코드가 없으면 한글값이 모두 깨져서 저장됨
		response.setContentType("application/json;charset=UTF-8");
		
		String userId = request.getParameter("user_id");
		String userName = request.getParameter("user_name");
		String userPwd = request.getParameter("user_pwd");
		String userEmail = request.getParameter("user_email");
		String userPhone = request.getParameter("user_phone");
		String userSsn = request.getParameter("user_ssn");
		java.sql.Date signupDt = new java.sql.Date(System.currentTimeMillis()); // 현재 날짜를 받아오는 것
		String proCareer = request.getParameter("pro_career");
		
		
		//DB연결
		Connection con = null;
		 
		//SQL 송신
		PreparedStatement pstmt = null;
		int rs = 0;
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "가입실패");
		String signupResult = mapper.writeValueAsString(map);
//		String signupResult= "{\"status\": 0 \"msg\": \" 가입실패 \"}";
		
		System.out.println(userId);
		
		try {
			con = MyConnection.getConnection();
			String insertSignupProSQL = "INSERT INTO user_info(user_id, user_name, user_pwd, user_email,"
										+ "user_phone,user_ssn,user_join_dt, user_quit_dt,user_type)"
										+ "VALUES(?, ?, ?, ?, ?, ?, ?, ? , 1)";
			pstmt = con.prepareStatement(insertSignupProSQL);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, userPwd);
			pstmt.setString(4, userEmail);
			pstmt.setString(5, userPhone);
			pstmt.setString(6, userSsn);
			pstmt.setDate(7, signupDt);
			pstmt.setDate(8, null);
			// 결과값 DB로 전송
			rs = pstmt.executeUpdate();
			
			
			String insertProInfoSQL = "INSERT INTO pro_info(user_id, pro_career) VALUES(?,?)";
			pstmt = con.prepareStatement(insertProInfoSQL);
			pstmt.setString(1, userId);
			pstmt.setString(2, proCareer);
			rs = pstmt.executeUpdate();
					
			System.out.println(userId);
			Upload upload = new Upload();
			upload.uploadFiles(request, userId);
			
			if(rs == 1) {
				map.put("status", 1);
				map.put("msg", "프로 가입성공! 축하합니다");
				signupResult = mapper.writeValueAsString(map);
//				signupResult= "{\"status\": 1 \"msg\": \"프로 가입성공! 축하합니다\"}";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
		
		PrintWriter out = response.getWriter();
		out.print(signupResult);
	}

}
