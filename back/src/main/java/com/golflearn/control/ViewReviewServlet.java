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
		//review라는 파라메터를 받아오기 -> 후기작성인지 수정인지 구별하기 위해 
		int reviewExist = Integer.parseInt(request.getParameter("review"));
		//lsn_line_no라는 파라메터를 받아오기 -> int로변환
		int lsnLineNo = Integer.parseInt(request.getParameter("lsn_line_no"));

//		//jquery에서 lsn_line_no 파라미터 받아오기 
//		String lessonLineNo = request.getParameter("lsn_line_no");

		//SQL 구문 받아오기 	
		LessonReviewRepository lrrepo = new LessonReviewRepository();
//		lrrepo.selectLessonTitleByLessonLineNo(lessonLineNo)  //테스트말고진짜로할때'
		
		if(reviewExist == 0) {
			lrrepo.selectLsnTitleByLsnLineNo(lsnLineNo); //정상작동 코드 
		}
		else {
			try {
				lrrepo.selectReviewByLsnLineNo(lsnLineNo);
			} catch (FindException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		

			






	}

}