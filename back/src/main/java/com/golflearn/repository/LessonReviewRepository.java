package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonReview;
import com.golflearn.sql.MyConnection;

public class LessonReviewRepository {

	
	//수강후기 작성 페이지 로딩 시 -> 내가클릭한 레슨번호를 받아서 대조
	public Lesson selectLsnTitleByLsnLineNo(String lessonLineNo){
		
		Lesson lesson = new Lesson(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String test = lessonLineNo;
		
		//이벤트로 내가 클릭한 lsn_line_no를 가져오기 
		//lsn_line_no가 같은 lesson테이블에서 lsn_title을 lesson_line테이블에서 lsn_line_no를 가져오기 
		String selectLessonTitle = 
				"SELECT ll.lsn_line_no, le.lsn_title\r\n"
				+ "FROM lesson le JOIN lesson_line ll ON (le.lsn_no = ll.lsn_no)\r\n"
				+ "WHERE ll.lsn_line_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonTitle);
			pstmt.setString(1, test);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String lsnTitle = rs.getString("lsn_title");
				System.out.println("레슨명은 : " + lsnTitle);
				
				lesson.setLsnTitle(lsnTitle);
				System.out.println("lessons가 갑니다" + lesson);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lesson;
		
//		return null;
	}
	
	public List<LessonReview> selectReviewByLsnLineNo(String lessonLineNo){
		
		List<LessonReview> reviews = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String test = lessonLineNo;
		
		//이벤트로 내가 클릭한 lsn_line_no를 가져오기 
		//lsn_line_no가 같은 테이블들에서 정보 가져오기, 별점 및 후기등등  
		String selectLessonReview = 
				"SELECT ll.lsn_line_no, le.lsn_title, lr.my_star_score, lr.review\r\n"
				+ "FROM lesson_line ll JOIN lesson_review lr ON (lr.lsn_line_no = ll.lsn_line_no)\r\n"
				+ "                    JOIN lesson le ON (le.lsn_no = ll.lsn_no)\r\n"
				+ "WHERE ll.lsn_line_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonReview);
			pstmt.setString(1, test);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int lsnLineNo = rs.getInt("lsn_line_no");
				String lsnTitle = rs.getString("lsn_title");
				int myStarScore = rs.getInt("my_star_score");
				String review = rs.getString("review");
				
				System.out.println("레슨명은 : " + lsnTitle +
								   "내가준별점은 : " + myStarScore +
								   "작성중이던 리뷰는 : " + review);
				LessonReview lr = new LessonReview();
				Lesson l = new Lesson();
				lr.setLsnLineNo(lsnLineNo);
//				l.setLsnTitle(lsnTitle); // ????? 
				lr.setMyStarScore(myStarScore);
				lr.setReview(review);
				reviews.add(lr);
//				reviews.add(l); //lesson review에다가 lesson객체형식 변수선언 해보기 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reviews;
		
	}
}
