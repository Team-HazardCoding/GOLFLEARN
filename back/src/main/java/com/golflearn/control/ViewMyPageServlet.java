package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.dto.LessonLine;
import com.golflearn.repository.LessonLineRepository;

/**
 * Servlet implementation class ViewMyPageServlet
 */
@WebServlet("/viewmypage")
public class ViewMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/plain;charset = utf-8");
		
		LessonLineRepository llrepo = new LessonLineRepository();
		PrintWriter out = response.getWriter();
		List<LessonLine> l = llrepo.selectByLsnLineNo("5");
		out.print(l.get(0));
		out.print(llrepo.selectByLsnLineNo("5"));
	}

}
