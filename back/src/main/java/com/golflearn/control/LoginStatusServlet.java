package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/loginstatus")
public class LoginStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 클라이언트용 세션 객체 찾기
		HttpSession session = request.getSession();
	
		//session의 로그인 된 상태 확인
		String loginedId = (String) session.getAttribute("loginInfo");
		ObjectMapper mapper = new ObjectMapper();
		Map <String, Object> map = new HashMap<>();
		if(loginedId == null) {
			map.put("status", 0); // 로그인 안 되었을 때
		}else {
			map.put("status", 1); // 로그인 되었을 때
		}
		// 결과 주석으로 달아두기
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString(map));
	}
}
