package com.golflearn.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.repository.LessonReviewRepository;

@WebServlet("/modifyreview")
public class ModifyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//리뷰수정페이지에서 전송하기 -> UPDATE
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/plain;charset = utf-8");
		
		//jquery에서 파라메터 받기 
		String review = request.getParameter("review");
		int myStarScore = Integer.parseInt(request.getParameter("my_star_score"));
		int lsnLineNo = Integer.parseInt(request.getParameter("lsn_line_no"));
		
		LessonReviewRepository lrrepo = new LessonReviewRepository();
		lrrepo.modifyReview(review, myStarScore, lsnLineNo);
	}

}
