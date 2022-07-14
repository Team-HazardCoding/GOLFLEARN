package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		Lesson lesson = new Lesson();
		lesson.setLocNo(request.getParameter("loc_no"));
		lesson.setLsnTitle(request.getParameter("lsn_title"));
		lesson.setLsnPrice(Integer.parseInt(request.getParameter("lsn_price")));
		lesson.setLsnLv(Integer.parseInt(request.getParameter("lsn_lv")));
		lesson.setLsnCntSum(Integer.parseInt(request.getParameter("lsn_cnt_sum")));
		lesson.setLsnPerTime(Integer.parseInt(request.getParameter("lsn_per_time")));
		lesson.setLsnIntro(request.getParameter("lsn_intro"));
		lesson.setLsnDays(Integer.parseInt(request.getParameter("lsn_Days")));
		lesson.setLsnUploadDt(new java.sql.Date(System.currentTimeMillis()));
		
		String[] clubNos = request.getParameterValues("club_no");
		List<LessonClsfc> lsnClsfcs = new ArrayList<LessonClsfc>();
		
		for(int i=0; i<clubNos.length; i++) {
			LessonClsfc lsnClsfc = new LessonClsfc();
			lsnClsfc.setClubNo(Integer.parseInt(clubNos[i]));
			lesson.setLsnClsfcs(lsnClsfcs);
			
			lsnClsfcs.add(lsnClsfc);
		}
		
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
					repository.insert(lesson);	//레슨정보 등록
					Map<String, Object> map = new HashMap<>();
					map.put("status", 1);
					map.put("user_type", 1);
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
