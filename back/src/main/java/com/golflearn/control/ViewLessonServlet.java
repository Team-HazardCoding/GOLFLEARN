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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonOracleRepository;
import com.golflearn.repository.LessonRepository;

@WebServlet("/viewlesson")
public class ViewLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		
		// 1.요청전달데이터 얻어오기
		String lsn_no = request.getParameter("lsn_no");
		
		// 2.DB에서 레슨검색
		LessonRepository repository = new LessonOracleRepository();
		
		// 3.로그인상태이든 아니든 레슨은 검색되도록 해야됨
		Lesson lsn;
		try {
			lsn = repository.selectByLsnNo(lsn_no);
			Map<String, Object> map = new HashMap<>();
			map.put("lsn", lsn);
			
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);
			
			System.out.println("jsonValue : " + result);  //자바영역에서 잘 전송되는지 확인
			out.print(result);	//웹에 전송 잘 됬는지 확인
		} catch (FindException e) {
			e.printStackTrace();
		}
		
//		try {
//			Lesson lsn = repository.selectByLsnNo(lsn_no);
//			
//			Map<String, Object> map = new HashMap<>();
//			map.put("status", 1);	// 회원 로그인 상태 - 0:로그아웃상태, 1:로그인상태
//			map.put("lsn", lsn);
//			
//			ObjectMapper mapper = new ObjectMapper();
//			result = mapper.writeValueAsString(map);
//			
//			System.out.println("jsonValue : " + result);  //자바단에서 전송되는지 확인
//			out.print(result);	//웹에 전송 잘 됬는지 확인
//		} catch (FindException e) {	// 로그인이 안되어있을때
//			e.printStackTrace();
//			
//			Map<String, Object> map = new HashMap<>();
//			map.put("status", 0);
//			map.put("msg", e.getMessage());
//			
//			ObjectMapper mapper = new ObjectMapper();
//			result = mapper.writeValueAsString(map);
//			
//			System.out.println("jsonValue : " + result);  //자바단에서 전송되는지 확인
//			out.print(result);	//웹에 전송 잘 됬는지 확인
//		}
	}
}
