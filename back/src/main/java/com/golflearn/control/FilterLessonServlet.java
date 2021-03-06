package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.dto.Lesson;
import com.golflearn.dto.User;
import com.golflearn.repository.LessonOracleRepository;
import com.golflearn.repository.LessonRepository;
import com.golflearn.sql.MyConnection;

/**
 * Servlet implementation class FilterLessonServlet
 */
@WebServlet("/filterlesson")
public class FilterLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		String []siguArr = request.getParameterValues("sigu");// 여러개의 sido값들이 들오기때문에 배열로 받아야할 것

		String result = "";

		// json 컨텐츠를 java 객체로 역직렬화하거나 json으로 직렬화할때 사용하는 라이브러리임
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		Map<String, Object> map = new HashMap<>();
		List<Lesson> lsnList = new ArrayList<>();

		Connection con = null;
		try {
			con = MyConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String selectFilterLsnSQL = "SELECT lsn_no, loc_no, lsn_title, lsn_upload_dt, lsn_star_sum, lsn_star_ppl_cnt, ui.user_name 프로명\n"
					+ "FROM lesson l JOIN user_info ui ON(l.user_id = ui.user_id)\n"
					+ "WHERE l.loc_no IN (";
			for(int i=0; i<siguArr.length; i++) {
				if(i>0) {
					selectFilterLsnSQL += ",";
				}
				selectFilterLsnSQL += "?";
			}		
					
			selectFilterLsnSQL += ") \n"
					+ "ORDER BY 1 DESC";
			pstmt = con.prepareStatement(selectFilterLsnSQL);
			for(int i=0; i<siguArr.length; i++) {
				pstmt.setString(i+1, siguArr[i]);
				
			}
			rs = pstmt.executeQuery();
			//				rs.setString(1, );
			while(rs.next()) {
				// 메인의 레슨목록에 필요한 항목들
				int lsnNo = rs.getInt("lsn_no");
				String lsnTitle = rs.getString("lsn_title");
				Date lsnUploadDt =  rs.getDate("lsn_upload_dt");
				int lsnStarSum = rs.getInt("lsn_star_sum");
				int lsnStarPplCnt = rs.getInt("lsn_star_ppl_cnt");
				int lsnStarPoint = 0;
				if (lsnStarPplCnt != 0) {
					lsnStarPoint = Math.round(lsnStarSum/lsnStarPplCnt);
				} else {
					lsnStarPoint = 0;
				}
				String userName = rs.getString("프로명");
				String locNo = rs.getString("loc_no");

				//레슨 한줄한줄을 읽어서 레슨객체에 저장함.
				User user = new User(userName);				

				Lesson lsn = new Lesson(lsnNo, lsnTitle, lsnUploadDt, lsnStarPoint, user, locNo); // 생성자로 고칠수 있는 부분
				// 레슨객체를 레슨리스트객체에 추가시킴
				lsnList.add(lsn);
				System.out.println("lsn객체 만들어짐");
			}

			map.put("lsns", lsnList);
			request.setAttribute("lsns", lsnList);
			
			// String타입으로 변환
			String jsonValue = mapper.writeValueAsString(map);
			System.out.println("jsonValue :"+ jsonValue);

			result = mapper.writeValueAsString(map);
			//			System.out.println("result :" + result);

			out.print(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}

	}

}
