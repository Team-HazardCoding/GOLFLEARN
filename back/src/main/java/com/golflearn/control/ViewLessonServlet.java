package com.golflearn.control;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonOracleRepository;
import com.golflearn.repository.LessonRepository;

/**
 * Servlet implementation class ViewLessonServlet
 */
@WebServlet("/viewlesson")
public class ViewLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답되어지는 데이터의 형태를 json 형태로 설정함 
		response.setContentType("application/json;charset=UTF-8");
		// 클라이언트에게 텍스트로 보내기 위해 getWriter메서드를 쓴다.
		PrintWriter out = response.getWriter();
		
		String result = "";
		// 1. 요청전달데이터 얻기
		String prod_no = request.getParameter("prod_no");
		
		//2. DB에서 상품을 검색
//		LessonRepository repository = new LessonOracleRepository();
//		try {
//			repository.selectAll();
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
	}

}
