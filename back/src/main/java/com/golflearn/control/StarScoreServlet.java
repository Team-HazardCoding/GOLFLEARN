package com.golflearn.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/starscore")
public class StarScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String score = request.getParameter("my_star_score");
		
		int lsnStarSum = 0;
		int lsnStarPplCnt = 0;  
		
		lsnStarSum += Integer.parseInt(score);
		lsnStarPplCnt += 1;

		
	}

}
