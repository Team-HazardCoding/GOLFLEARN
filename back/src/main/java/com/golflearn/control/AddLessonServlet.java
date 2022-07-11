package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonClsfc;
import com.golflearn.exception.AddException;
import com.golflearn.repository.AddLessonOracleRepository;
import com.golflearn.repository.AddLessonRepository;

@WebServlet("/addlesson")
public class AddLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 겟으로 할지 포스트로 할지 고민
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
//		HttpSession session = request.getSession();
		
		//입력받은 레슨정보를 레슨객체에 저장
		Lesson l = new Lesson();
		l.setLocationNo(Integer.parseInt(request.getParameter("loc_no")));
		l.setLsnTitle(request.getParameter("lsn_title"));
		l.setLsnPrice(Integer.parseInt(request.getParameter("lsn_price")));
		l.setLsnLv(Integer.parseInt(request.getParameter("lsn_lv")));
		l.setLsnCntSum(Integer.parseInt(request.getParameter("lsn_cnt_sum")));
		l.setLsnPerTime(Integer.parseInt(request.getParameter("lsn_per_time")));
		l.setLsnIntro(request.getParameter("lsn_intro"));
		l.setLsnDays(Integer.parseInt(request.getParameter("lsn_Days")));
		l.setLsnUploadDt(new java.sql.Date(System.currentTimeMillis()));
		//입력받은 레슨분류 정보를 레슨분류객체에 저장
		LessonClsfc lc = new LessonClsfc();
		lc.setClubNo(Integer.parseInt(request.getParameter("club_no")));
		
		try {
			// 로그인된 사용자인지 검사
//			String loginedId = (String)session.getAttribute("loginInfo");
//			if(loginedId == null) {
//				Map<String, Object> map = new HashMap<>();
//				map.put("status", 0);
//				map.put("msg", "로그인하세요");
//				result = mapper.writeValueAsString(map);
//			}else {
				//로그인 되면
//				if(repository.selectTypeById(loginedId) == 1) {	// user_type이 1인지 검사
					AddLessonRepository repository = new AddLessonOracleRepository();
					repository.insert(l);	//레슨등록
//				repository.insertLsnInfo2(l);
					Map<String, Object> map = new HashMap<>();
					map.put("status", 1);
					map.put("user_type", 1); // 이것도 추가하면 좋을까?
					map.put("msg", "등록성공");
					result = mapper.writeValueAsString(map);
//				}
//			}
		} catch (AddException e) {
			e.printStackTrace();
		}
		out.print(result);
	}
}
