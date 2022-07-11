package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.mail.Smtp;
import com.golflearn.sql.MyConnection;

@WebServlet("/findpwd")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		String userEmail = request.getParameter("user_email");
		String authenticationKey = null;
		
		//SQL 송신
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ObjectMapper mapper = new ObjectMapper();
		Map <String, Object> map= new LinkedHashMap<>();
		String result = mapper.writeValueAsString(map);

		try {
			con = MyConnection.getConnection();
			String selectEmailSQL = "SELECT user_email FROM user_info WHERE user_id=? AND user_email=?";
			pstmt = con.prepareStatement(selectEmailSQL);
			pstmt.setString(1, userId);
			pstmt.setString(2, userEmail);
			rs=pstmt.executeQuery();
			String email = null;
			if(rs.next()) {
				email = rs.getString("user_email");
				map.put("status", 1);				
				map.put("msg","인증코드 발송");
				Smtp smtp = new Smtp();
				authenticationKey = smtp.gmailSend(request, response, userId, userEmail, authenticationKey);	
				result = mapper.writeValueAsString(map);
				System.out.println(authenticationKey);
			}else {
				map.put("status", 0);
				map.put("msg", "인증코드 발송 실패");
				request.setAttribute("msg", "아이디나 이메일이 일치하지 않습니다");
				result = mapper.writeValueAsString(map);
			}
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(result);
			out.print(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("authenticationKey", authenticationKey);
		saveKey.setAttribute("id", userId);
	}
}
//			//사용자가 입력하지 않은 경우
//			//front에서 required 처리해줘도 상관없음
//			if(userId == null || userEmail == null){
//				request.setAttribute("msg", "아이디나 이메일 정보가 누락됨");
//				request.setAttribute("loc", "/findpwd");
//				request.getRequestDispatcher("/findpwd").forward(request, response);
//				return;
//
//			//사용자가 정확한 값을 넣은 경우
//			} else if (id.equals(userId) && email.equals(userEmail)) {
//				Smtp smtp = new Smtp();
//				smtp.gmailSend(request, response, userId, userEmail);	
////				return;
//			//사용자가 값을 하나라도 잘못 입력한 경우
//			} else {
//				request.setAttribute("msg", "아이디나 이메일이 일치하지 않습니다");
//				request.setAttribute("loc", "/findpwd");
//				response.sendRedirect("/findpwd");
//			}