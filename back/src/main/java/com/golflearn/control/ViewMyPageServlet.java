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
import com.golflearn.dto.User;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonLineRepository;
import com.golflearn.repository.LessonReviewRepository;

/**
 * Servlet implementation class ViewMyPageServlet
 */
@WebServlet("/viewmypage")
public class ViewMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/plain;charset = utf-8");
		
		String userId = (String)session.getAttribute("loginInfo");
		//★★★★★★구현해야됨. selectreviewbylsnlineno? jquery에서 화면에 나타날 레슨라인넘버들 보내주고 그것들을 list나 map으로 받나? 
		//JSON으로 오는지 어떻게 오는지 판단하고 작성
		int addOrModify = Integer.parseInt(request.getParameter("??"));
		
		LessonLineRepository llrepo = new LessonLineRepository();
		
		try {
			//userid가 0(수강생)인지 1(프로)인지 확인
			if(llrepo.selectTypeById(userId) == 0) {
				//수강생이면 수강생ID로찾고
				llrepo.selectById(userId);
			}else {
				//프로면 프로 ID로 찾고
				llrepo.selectByProId(userId);
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
