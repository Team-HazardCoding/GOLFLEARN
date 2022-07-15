package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonClsfc;
import com.golflearn.exception.AddException;
import com.golflearn.exception.FindException;
import com.golflearn.repository.AddLessonOracleRepository;
import com.golflearn.repository.AddLessonRepository;
import com.golflearn.repository.LessonOracleRepository;
import com.golflearn.repository.LessonRepository;

@WebServlet("/addlesson")
@MultipartConfig
public class AddLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		HttpSession session = request.getSession();
		
		//입력받은 레슨정보를 레슨객체에 저장
		Lesson lesson = new Lesson();
		lesson.setLocNo(request.getParameter("sgg"));
		lesson.setLsnTitle(request.getParameter("lsn_title"));
		lesson.setLsnPrice(Integer.parseInt(request.getParameter("lsn_price")));
		lesson.setLsnLv(request.getParameter("lsn_lv"));
		lesson.setLsnCntSum(Integer.parseInt(request.getParameter("lsn_cnt_sum")));
		lesson.setLsnPerTime(Integer.parseInt(request.getParameter("lsn_per_time")));
		lesson.setLsnIntro(request.getParameter("lsn_intro"));
		lesson.setLsnDays(Integer.parseInt(request.getParameter("lsn_days")));
		lesson.setLsnUploadDt(new java.sql.Date(System.currentTimeMillis()));
		
		String[] clubNos = request.getParameterValues("club_no");
		List<LessonClsfc> lsnClsfcs = new ArrayList<LessonClsfc>();
		
		for(int i=0; i<clubNos.length; i++) {
			LessonClsfc lsnClsfc = new LessonClsfc();
			lsnClsfc.setClubNo(Integer.parseInt(clubNos[i]));
			lesson.setLsnClsfcs(lsnClsfcs);
			
			lsnClsfcs.add(lsnClsfc);
		}
		
		String userId = (String)session.getAttribute("loginInfo");
//		String userType = (String)session.getAttribute("userType");
		
		//입력받은 데이터 저장 
		try {
			// 로그인된 사용자인지 검사
			if(userId == null) {
				Map<String, Object> map = new HashMap<>();
				map.put("status", 0);
				map.put("msg", "로그인하세요");
				result = mapper.writeValueAsString(map);
			}else {
				//로그인 되면
				LessonRepository LsnRepository = new LessonOracleRepository();	
				if(LsnRepository.selectTypeById(userId) == 1) {	// user_type이 1인지 검사
					AddLessonRepository repository = new AddLessonOracleRepository();
					repository.insert(lesson, userId);	//레슨정보 등록
					Map<String, Object> map = new HashMap<>();
					map.put("status", 1);
					map.put("userType", 1);
					map.put("msg", "등록성공");
					result = mapper.writeValueAsString(map);
					//이미지 파일 업로드
					Upload upload = new Upload();
					upload.uploadFiles(request, userId);
				}
			}				
		} catch (AddException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("status", -1);
			map.put("msg", "등록실패");
			result = mapper.writeValueAsString(map);
		} catch (FindException e) {
			e.printStackTrace();
		}
		out.print(result);
	}
}