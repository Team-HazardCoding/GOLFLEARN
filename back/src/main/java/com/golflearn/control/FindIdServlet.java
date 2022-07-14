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
		Map <String, Object> map= new LinkedHashMap<>();
		map.put("status", 0);
		map.put("msg", "id조회 실패");
		String result = mapper.writeValueAsString(map);
		
		try {
			con = MyConnection.getConnection();
			String selectIdSQL = "SELECT user_id FROM user_info WHERE user_name=? AND user_email=?";
			pstmt = con.prepareStatement(selectIdSQL);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.executeQuery();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				map = new LinkedHashMap<>();
				String userId = rs.getString("user_id");
				map.put("status",1);
				map.put("id",userId);
				map.put("msg", "고객님의 Id는" + userId + "입니다" );
				result = mapper.writeValueAsString(map);
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