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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.LessonLine;
import com.golflearn.exception.FindException;
import com.golflearn.repository.LessonHistoryRepository;

@WebServlet("/viewstudentmanage")
public class ViewStudentManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	//프로레슨내역에서 수강생관리 버튼을 클릭해 수강생관리 페이지 로딩되었을 때 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json;charset = utf-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		int selectedLsnNo = Integer.parseInt(request.getParameter("lsn_no"));
//		System.out.println(selectedLsnNo + "는 이거다! ");
		List<LessonLine> ll = null;
		String result = null;
		
		//클릭한 레슨 번호 받아오기 
		LessonHistoryRepository lhrepo = new LessonHistoryRepository();
		try {
			ll = lhrepo.selectByLsnNo(selectedLsnNo);
//			System.out.println(selectedLsnNo + "는 이거다! 바로! ");
			result = mapper.writeValueAsString(ll);
			System.out.println("결과" + result);
			out.print(result);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
