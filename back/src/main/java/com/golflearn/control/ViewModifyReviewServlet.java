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
import com.golflearn.dto.LessonLine;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonReviewRepository;

@WebServlet("/viewmodifyreview")
public class ViewModifyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//후기수정 페이지 로딩될 때 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json;charset = utf-8");
		PrintWriter out = response.getWriter();
		//후기수정을 누른 레슨내역번호 전달 
		int lsnLineNo = Integer.parseInt(request.getParameter("lsn_line_no"));
		ObjectMapper mapper = new ObjectMapper();
//		int lsnLineNo = 2;
		LessonLine ll = null;
		String result = null;
		
		
		LessonReviewRepository lrrepo = new LessonReviewRepository();
		try {
			ll = lrrepo.selectReviewByLsnLineNo(lsnLineNo);
			result = mapper.writeValueAsString(ll);
			System.out.println("결과" + result);
			out.print(result);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
