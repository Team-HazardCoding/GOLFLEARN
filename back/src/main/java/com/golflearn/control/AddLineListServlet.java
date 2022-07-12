package com.golflearn.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonLineRepository;

@WebServlet("/addlinelist")
public class AddLineListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lsnLineNo = Integer.parseInt(request.getParameter("lsn_line_no"));
		String userId = request.getParameter("user_id");
		
		LessonLineRepository llrepo = new LessonLineRepository();
		
		try {
			int stdtLsnStatus = llrepo.selectLsnStatus(lsnLineNo);
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
	
	}

}
