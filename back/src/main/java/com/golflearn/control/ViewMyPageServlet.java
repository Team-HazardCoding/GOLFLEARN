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
//		HttpSession session = request.getSession();
//		String userId = (String)session.getAttribute("loginInfo");
		String userId = "93saewoo";
		response.setContentType("application/json;charset = utf-8");
		PrintWriter out = response.getWriter();
		
		//★★★★★★구현해야됨. selectreviewbylsnlineno? jquery에서 화면에 나타날 레슨라인넘버들 보내주고 그것들을 list나 map으로 받나? 
		
		//유저아이디를 받아온다고 가정 
		LessonLineRepository llrepo = new LessonLineRepository();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		
		//레슨라인들을 받아와서 반환해줌 
		List<LessonLine> lsnLines;
		LessonLine lsnLine = null;
		Lesson lsn = null;
				
		try {
			lsnLines = llrepo.selectById(userId);
			Map<String, Object> map = new HashMap<>();
			for (int i = 0 ; i < lsnLines.size() ; i++) {
//				lsnLine = lsnLines.get(i);
//				System.out.println(lsnLines.get(i));
//				map.put("ll", lsnLines.get(i));
			}
			
			map.put("ll", lsnLines);
//			map.put("ll", lsnLine);
			result = mapper.writeValueAsString(map);
			System.out.println("결과" + result);
			out.print(result);
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
		
//		try {
//			//userid가 0(수강생)인지 1(프로)인지 확인
//			if(llrepo.selectTypeById(userId) == 0) {
//				//수강생이면 수강생ID로찾고
//				llrepo.selectById(userId); //반환값 : list<LessonLine>
//			}else {
//				//프로면 프로 ID로 찾고
//				llrepo.selectByProId(userId);//반환값 : list<LessonLine>
//			}
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
	}

}
