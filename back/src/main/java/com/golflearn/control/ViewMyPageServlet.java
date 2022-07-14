<<<<<<< HEAD
package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonLine;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonLineRepository;


/**
 * Servlet implementation class ViewMyPageServlet
 */
@WebServlet("/viewmypage")
public class ViewMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("loginInfo");
//		String userId = "gyeorekuku";
//		String userId = "younghee1234";
		// String userId = "93saewoo";
		response.setContentType("application/json;charset = utf-8");
		PrintWriter out = response.getWriter();		

		LessonLineRepository llrepo = new LessonLineRepository();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		
		//레슨라인들을 받아와서 반환해줌 
		List<LessonLine> lsnLines;
		List<Lesson> lsns;
				
		try {
			if(llrepo.selectTypeById(userId) == 0) {
				lsnLines = llrepo.selectById(userId);
				Map<String, Object> map = new HashMap<>();
				
				map.put("ll", lsnLines);
				result = mapper.writeValueAsString(map);
				System.out.println("결과" + result);
				out.print(result);
			}
			else {
				lsns = llrepo.selectByProId(userId);
				result = mapper.writeValueAsString(lsns);
				System.out.println("결과" + result);
				out.print(result);
			}
		} catch (FindException e1) {
			e1.printStackTrace();
		}
		
	
		
//		List list = new ArrayList<>();
//		
////		for(String  lsnLines : lsnLine);
//		
//		String jsonTest = mapper.writeValueAsString(lsnLine);
//		System.out.println(jsonTest);
//		
//		

	}

}
