package com.golflearn.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonReviewRepository;

@WebServlet("/viewmodifyreview")
public class ViewModifyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//후기수정 페이지 로딩될 때 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/plain;charset = utf-8");
		//후기수정을 누른 레슨내역번호 전달 
		int lsnLineNo = Integer.parseInt(request.getParameter("lsn_line_no"));
		LessonReviewRepository lrrepo = new LessonReviewRepository();
		try {
			lrrepo.selectReviewByLsnLineNo(lsnLineNo);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
