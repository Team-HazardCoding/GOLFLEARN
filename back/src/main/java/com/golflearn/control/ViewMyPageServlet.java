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
		String userId = (String)session.getAttribute("loginInfo");
		response.setContentType("text/plain;charset = utf-8");
		PrintWriter out = response.getWriter();
		
		
		LessonLineRepository llrepo = new LessonLineRepository();
		//userstatus가 1인거랑 0인거를 담을 객체를 만들고 그거에서 for문돌리기? 
		
		//테스트용
//		try {
//			llrepo.selectTypeById("93saewoo");
//		} catch (FindException e1) {
//			e1.printStackTrace();
//		}
		
		
		try {
			if(llrepo.selectTypeById(userId) == 0) {
				llrepo.selectById(userId);
			}else {
				llrepo.selectByProId(userId);
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
