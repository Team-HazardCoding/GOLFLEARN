package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.repository.LessonReviewRepository;

/**
 * Servlet implementation class ViewReviewServlet
 */
@WebServlet("/viewreview")
public class ViewReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * review 페이지 로딩하여 받아온 정보 보여주기 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//테스트용
		PrintWriter out = response.getWriter();
		//한글설정
		response.setContentType("text/plain;charset = utf-8");
		//로그인된 세션 받아오기 
		HttpSession session = request.getSession();

//		//jquery에서 lsn_line_no 파라미터 받아오기 
//		String lessonLineNo = request.getParameter("lsn_line_no");

		//SQL 구문 받아오기 	
		LessonReviewRepository lrrepo = new LessonReviewRepository();
//		lrrepo.selectLessonTitleByLessonLineNo(lessonLineNo)  //테스트말고진짜로할때
		lrrepo.selectLsnTitleByLsnLineNo("5");
		
		

			






	}

}