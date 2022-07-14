package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonOracleRepository;
import com.golflearn.repository.LessonRepository;
import com.golflearn.repository.OpenApi;

@WebServlet("/main")
public class ViewMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
	
		
		String result = "";
		LessonRepository repo = new LessonOracleRepository();
		try {
			// json 컨텐츠를 java 객체로 역직렬화하거나 json으로 직렬화할때 사용하는 라이브러리임
			ObjectMapper mapper = new ObjectMapper(); 
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			
			Map<String, Object> map = new HashMap<>();
			List<Lesson> lsnList = repo.selectAll();
//			map.put("status", 1);
			// 광역시들을 메인에 보여주는 코드
			OpenApi api = new OpenApi();
			List sidoList = new ArrayList();

			try {
				map.put("lsns", lsnList);//
				map.put("sido", api.sidoApi());
				map.put("userType", userType);
				
				request.setAttribute("lsns", lsnList);
				request.setAttribute("sido", api.sidoApi());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// String타입으로 변환
			String jsonValue = mapper.writeValueAsString(map);
//			System.out.println("jsonValue :"+ jsonValue);
			
 			result = mapper.writeValueAsString(map);
//			System.out.println("result :" + result);
			
			out.print(result);
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();

			map.put("msg", e.getMessage());
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);
//			System.out.println("result: " + result);
			out.print(result);
		}
		
	}

}
