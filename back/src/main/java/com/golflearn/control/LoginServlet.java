package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.sql.MyConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("user_id");
		String userPwd = request.getParameter("user_pwd");

		//DB연결
		Connection con = null;

		//SQL 연결
		PreparedStatement pstmt = null;
		//송신
		ResultSet rs = null;

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map =  new HashMap<>();
		map.put("status", 0);
		map.put("msg", "아이디와 비밀번호가 일치하지 않습니다.");
		String loginResult = mapper.writeValueAsString(map);
//		String loginResult = "{\"status\":0 \"msg\":\"로그인 실패\"}";

		//세션 객체 얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");

		try {
			con = MyConnection.getConnection();
			String selectUserIdNPwd = "SELECT * FROM user_info WHERE user_id = ? AND user_pwd = ?";
			pstmt = con.prepareStatement(selectUserIdNPwd);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			
//			System.out.println(userId);
//			System.out.println(userPwd);
			if(rs.next()) {// 로그인 성공
				String userType = rs.getString("user_type");
//				System.out.println(userType); 		
				
				map.put("status",1);
				map.put("msg", "로그인 되었습니다.");
				loginResult = mapper.writeValueAsString(map);
				
				
//				loginResult = "{\"status\":1 \"msg\":\"로그인 성공\"}";
//				loginResult = "{\"status\":1}";
				session.setAttribute("loginInfo", userId); // 세션객체 속성으로 로그인 정보 추가
				session.setAttribute("userType", userType); // 세션 객체에 유저타입 정보 추가
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
		out.print(loginResult);
	}
}
