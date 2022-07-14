package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonReviewRepository;


/**
 * Servlet implementation class ViewReviewServlet
 */
@WebServlet("/viewreview")
public class ViewReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	//review 페이지 로딩하여 받아온 정보 보여주기 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글설정
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		//로그인된 세션 받아오기 
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		//review라는 파라메터를 받아오기 -> 후기작성인지 수정인지 구별하기 위해 
//		int reviewExist = Integer.parseInt(request.getParameter("review"));
//		//lsn_line_no라는 파라메터를 받아오기 -> int로변환
		int lsnLineNo = Integer.parseInt(request.getParameter("lsn_line_no"));
//		int lsnLineNo = 15;
		Lesson le = null;
		String result = null;
		
		LessonReviewRepository lrrepo = new LessonReviewRepository();
		try {
			le= lrrepo.selectLsnTitleByLsnLineNo(lsnLineNo);
			result = mapper.writeValueAsString(le);
			System.out.println("결과" + result);
			out.print(result);
		} catch (FindException e) {
			e.printStackTrace();
		} //정상작동 코드 
		



	}

}