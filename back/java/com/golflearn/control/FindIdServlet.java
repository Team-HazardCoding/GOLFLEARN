package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.sql.MyConnection;

@WebServlet("/findid")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user_name");
		String userEmail = request.getParameter("user_email");
		
		//DB연결
		Connection con = null;
		//SQL 송신
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//기본 result값 = status 0 (id조회 실패)
		ObjectMapper mapper = new ObjectMapper();
		String result = "{\"status\": 0, \"msg\": \"id조회실패\"}";

		
		try {
			con = MyConnection.getConnection();
			String selectIdSQL = "SELECT user_id FROM user_info WHERE user_name=? AND user_email=?";
			pstmt = con.prepareStatement(selectIdSQL);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.executeQuery();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Map map = new LinkedHashMap<>();
				String userId = rs.getString("user_id");
				map.put("status",1);
				map.put("id",userId);
				//조건문 true일 경우 status=1, id조회성공
//				result = "{\"status\": 1, \"msg\":" + userId +"}";
				result = mapper.writeValueAsString(map);
				request.setAttribute("msg", "아이디 조회에 성공했습니다");
				request.setAttribute("loc", "");
				request.getRequestDispatcher("/login").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		System.out.println(result);
	}
}