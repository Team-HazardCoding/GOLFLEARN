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

import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonOracleRepository;
import com.golflearn.repository.LessonRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;

@WebServlet("/viewlesson")
public class ViewLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";

		//요청전달데이터 얻어오기
		int lsn_no = Integer.parseInt(request.getParameter("lsn_no"));   
	}

		//DB에서 레슨검색
		LessonRepository repository = new LessonOracleRepository();

		//로그인상태이든 아니든 레슨은 검색되도록 함
		Lesson l = new Lesson();
		try {
			l = repository.selectByLsnNo(lsn_no);
			Map<String, Object> map = new HashMap<>();
			map.put("status", 1);
			map.put("lesson", l);

			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);

			out.print(result);
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", e.getMessage());

			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);
			out.print(result);
		}
	}
}
