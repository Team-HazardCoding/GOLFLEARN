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

import com.fasterxml.jackson.databind.ObjectMapper;
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
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "이미 사용중인 아이디입니다.");
		String idChkResult = mapper.writeValueAsString(map);
//		String idChkResult = "{\"status\":0 \" msg \":\" 이미 사용중인 아이디입니다.\"}";
		
		try {
			con = MyConnection.getConnection();
			String selectIdDupChkSQL = "SELECT * FROM user_info WHERE user_id = ?";
			pstmt = con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next() == false) {
				map.put("status", 1);
				map.put("msg", "사용 가능한 아이디입니다.");
				idChkResult = mapper.writeValueAsString(map);
//				idChkResult = "{\"status\": 1 \" msg \":\" 사용가능한 아이디입니다.\"}";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		out.print(idChkResult);
	}

}
