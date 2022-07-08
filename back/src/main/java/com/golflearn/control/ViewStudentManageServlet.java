package com.golflearn.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonHistoryRepository;

@WebServlet("/viewstudentmanage")
public class ViewStudentManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	//프로레슨내역에서 수강생관리 버튼을 클릭해 수강생관리 페이지 로딩되었을 때 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/plain;charset = utf-8");
		//클릭한 레슨 번호 받아오기 
		int selectedLsnNo = Integer.parseInt(request.getParameter("lsn_no"));
		LessonHistoryRepository lhrepo = new LessonHistoryRepository();
		try {
			lhrepo.selectByLsnNo(selectedLsnNo);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
