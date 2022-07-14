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
import com.golflearn.repository.LessonReviewRepository;

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/addreview")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("application/json;charset = utf-8");
		ObjectMapper mapper = new ObjectMapper();
		
		//jquery에서 파라메터 받기 
		String review = request.getParameter("review");
		int myStarScore = Integer.parseInt(request.getParameter("my_star_score"));
		int lsnLineNo = Integer.parseInt(request.getParameter("lsn_line_no"));		
		
		LessonReviewRepository lrrepo = new LessonReviewRepository();
		//클릭된 레슨번호, 레슨내역번호, 별점, 리뷰 받아와서 넣기  
		//lrrepo.insertReviewTrigger(666, 5);
		lrrepo.insertReview(lsnLineNo, myStarScore, review);
		
		
	}

}
