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

@WebServlet("/lessonstatus")
public class LessonStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		//수강생레슨상태라는 걸 업데이트 해주고 그걸 받아오는? 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setContentType("application/json;charset = utf-8");
		int lsnLineNo = Integer.parseInt(request.getParameter("lsnline_o"));
		
		LessonLineRepository llrepo = new LessonLineRepository();
		try {
			llrepo.selectLsnStatus(lsnLineNo);
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		
	}

}
