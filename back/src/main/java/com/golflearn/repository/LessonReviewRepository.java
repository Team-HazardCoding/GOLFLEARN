package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonLine;
import com.golflearn.dto.LessonReview;
import com.golflearn.dto.User;
import com.golflearn.exception.FindException;
import com.golflearn.sql.MyConnection;

public class LessonReviewRepository {

	
	//수강후기 작성 페이지 로딩 시 -> 내가클릭한 레슨번호를 받아서 대조
	public Lesson selectLsnTitleByLsnLineNo(int lessonLineNo){
		
		Lesson lesson = new Lesson(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int test = lessonLineNo;
		
		//이벤트로 내가 클릭한 lsn_line_no를 가져오기 
		//lsn_line_no가 같은 lesson테이블에서 lsn_title을 lesson_line테이블에서 lsn_line_no를 가져오기 
		String selectLessonTitle = 
				"SELECT ll.lsn_line_no, le.lsn_title\r\n"
				+ "FROM lesson le JOIN lesson_line ll ON (le.lsn_no = ll.lsn_no)\r\n"
				+ "WHERE ll.lsn_line_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonTitle);
			pstmt.setInt(1, test);
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
	
	public LessonLine selectReviewByLsnLineNo(int lessonLineNo) throws FindException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int test = lessonLineNo;
		
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
			pstmt.setInt(1, test);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int lsnLineNo = rs.getInt("lsn_line_no");
				String lsnTitle = rs.getString("lsn_title");
				int myStarScore = rs.getInt("my_star_score");
				String review = rs.getString("review");
				
//				System.out.println("레슨명은 : " + lsnTitle);
//				System.out.println("내가준별점은 : " + myStarScore);
//				System.out.println("작성중이던 리뷰는 : " + review);
								   
				
				Lesson l = new Lesson();
				l.setLsnTitle(lsnTitle); // ?????  //lesson에 lsnTitle에는 입력됨 
				
				LessonReview lr = new LessonReview();
				lr.setLsnLineNo(lsnLineNo);
				lr.setMyStarScore(myStarScore);
				lr.setReview(review);
				
				LessonLine line = new LessonLine();
				line.setLsn(l);
				line.setLsnReview(lr);
				System.out.println(line.toString(l,lr.toString(lsnLineNo,myStarScore,review))); //테스트용 
				return line;
//				reviews.add(lr);
			}
			throw new FindException(lessonLineNo +"번 레슨내역이 없습니다");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	
	//입력된 id에따라 type값 반환 

	
}
