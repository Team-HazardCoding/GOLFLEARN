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
import com.golflearn.dto.Lesson;
import com.golflearn.exception.AddException;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonOracleRepository;
import com.golflearn.repository.LessonRepository;

@WebServlet("/addlesson")
public class AddLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		HttpSession session = request.getSession();

		//로그인된 사용자인지 확인 (레슨등록 버튼은 모든 사용자가 보이도록 할것임. 그래서 사용자인지 확인)
		String loginedId = (String)session.getAttribute("loginInfo");
		if(loginedId == null) {	//로그인이 되있지 않다면
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", "로그인하세요");
			result = mapper.writeValueAsString(map);
		} else {	// 로그인이 되있다면
			try {
				LessonRepository repository = new LessonOracleRepository();
				Lesson l = new Lesson();
				
				if(repository.selectTypeById(loginedId) == 1) {
					//loginedId = 프로의 아이디(레슨의 userId).
					l.setUserId(loginedId);
					repository.insert(l); // 레슨추가
					
					Map<String, Object> map = new HashMap<>();
					map.put("status", 1);
					map.put("msg", "등록성공");
					result = mapper.writeValueAsString(map);
				}
			} catch (AddException e) {
				e.printStackTrace();
			} catch (FindException e) {
				e.printStackTrace();
			}
		}
	}
}
