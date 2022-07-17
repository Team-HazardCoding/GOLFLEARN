package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonLine;
import com.golflearn.dto.LessonReview;
import com.golflearn.exception.FindException;
import com.golflearn.sql.MyConnection;

public class LessonReviewRepository {

	
	//수강후기 작성 페이지 로딩 시 -> 내가클릭한 레슨번호를 받아서 대조
	//viewreview servlet
	public Lesson selectLsnTitleByLsnLineNo(int lessonLineNo) throws FindException{
		
		Lesson lesson = new Lesson(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//이벤트로 내가 클릭한 lsn_line_no를 가져오기 
		//lsn_line_no가 같은 lesson테이블에서 lsn_title을 lesson_line테이블에서 lsn_line_no를 가져오기 
		String selectLessonTitleSQL = 
				"SELECT ll.lsn_line_no, le.lsn_title\r\n"
				+ "FROM lesson le JOIN lesson_line ll ON (le.lsn_no = ll.lsn_no)\r\n"
				+ "WHERE ll.lsn_line_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonTitleSQL);
			pstmt.setInt(1, lessonLineNo); //클릭된 lsn_line_no와 같은
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int lsnLineNo = rs.getInt("lsn_line_no");
				String lsnTitle = rs.getString("lsn_title");
				LessonLine ll = new LessonLine();
				ll.setLsnLineNo(lsnLineNo);
				
				lesson.setLsnTitle(lsnTitle);
				lesson.setLsnLine(ll);
				return lesson;
			}
			throw new FindException(lessonLineNo +"번 레슨내역이 없습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		
		
//		return null;
	}
	//수강후기 정정 페이지 
	//viewreviewservlet
	public LessonLine selectReviewByLsnLineNo(int lessonLineNo) throws FindException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//이벤트로 내가 클릭한 lsn_line_no를 가져오기 
		//lsn_line_no가 같은 테이블들에서 정보 가져오기, 별점 및 후기등등  
		String selectLessonReviewSQL = 
				"SELECT ll.lsn_line_no, le.lsn_title, lr.my_star_score, lr.review\r\n"
				+ "FROM lesson_line ll JOIN lesson_review lr ON (lr.lsn_line_no = ll.lsn_line_no)\r\n"
				+ "                    JOIN lesson le ON (le.lsn_no = ll.lsn_no)\r\n"
				+ "WHERE ll.lsn_line_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonReviewSQL);
			pstmt.setInt(1, lessonLineNo); //클릭된 레슨번호와 같은 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int lsnLineNo = rs.getInt("lsn_line_no");
				String lsnTitle = rs.getString("lsn_title");
				int myStarScore = rs.getInt("my_star_score");
				String review = rs.getString("review");
				
				Lesson l = new Lesson();
				l.setLsnTitle(lsnTitle); 
				
				LessonReview lr = new LessonReview();
				lr.setLsnLineNo(lsnLineNo);
				lr.setMyStarScore(myStarScore);
				lr.setReview(review);
				
				//각 객체에 담은뒤 다시 담기 
				LessonLine line = new LessonLine();
				line.setLsn(l);
				line.setLsnReview(lr);
//				System.out.println(line.toString(l,lr.toString(lsnLineNo,myStarScore,review))); //테스트용 
				return line;
			}
			throw new FindException(lessonLineNo +"번 레슨내역이 없습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	//수강후기 작성후 제출 시 
	//AddReviewServlet
	public void insertReview(int lsnLineNo, int myStarScore, String review) {
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		String insertReviewSQL = 
				"INSERT INTO lesson_review(lsn_line_no, review, review_dt, review_edit_dt, my_star_score) VALUES(?, ?, sysdate, sysdate, ?)";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertReviewSQL);
			
			pstmt.setInt(1, lsnLineNo);
			pstmt.setString(2, review);
			pstmt.setInt(3, myStarScore);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, pstmt, con);
		}
	}
	
	//수강후기 수정 후 제출 시 
	//ModifyReviewServlet
	public void modifyReview(String review, int myStarScore, int lsnLineNo) {
		Connection con = null; 
		PreparedStatement pstmt = null;	
		String modifyReviewSQL = 
					"UPDATE lesson_review \r\n"
					+ "SET review = ?, review_edit_dt = sysdate, my_star_score = ?\r\n"
					+ "WHERE lsn_line_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(modifyReviewSQL);
			
			pstmt.setString(1, review);
			pstmt.setInt(2, myStarScore);
			pstmt.setInt(3, lsnLineNo);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, pstmt, con);
		}
	
	}
}
//	public void insertReviewTrigger(int lessonNo, int myStarScores) {
//		Connection con = null; 
//		PreparedStatement pstmtTrig = null;
//		
//		String insertReviewTrigSQL = 
//				"CREATE OR REPLACE TRIGGER lsn_review_insert_trig \r\n"
//				+ "AFTER INSERT ON lesson_review\r\n"
//				+ "FOR EACH ROW\r\n"
//				+ "BEGIN\r\n"
//				+ "    UPDATE lesson \r\n"
//				+ "    SET LSN_STAR_SUM = LSN_STAR_SUM + ?, LSN_STAR_PPL_CNT=LSN_STAR_PPL_CNT+1\r\n"
//				+ "    WHERE lsn_no = ?;\r\n"
//				+ "END";
//		System.out.println(insertReviewTrigSQL);
//		int lsnNo = lessonNo;
//		int myStarScore = myStarScores;
//		try {
//			con = MyConnection.getConnection();
//			pstmtTrig = con.prepareStatement(insertReviewTrigSQL);
//			
//			System.out.println("여기?");
//			pstmtTrig.setInt(1, myStarScore);
//			pstmtTrig.setInt(2, lsnNo);
//			//트리거 먼저 실행 
//			pstmtTrig.executeUpdate();
//			System.out.println("아니면여기?");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MyConnection.close(null, pstmtTrig, con);
//		}
//		
//		
//	}
	



