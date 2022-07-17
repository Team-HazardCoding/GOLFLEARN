package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonClsfc;
import com.golflearn.exception.AddException;
import com.golflearn.sql.MyConnection;

public class AddLessonOracleRepository implements AddLessonRepository {
	//1. 레슨등록 : 레슨의 기타 정보들과 레슨분류정보를 모두 INSERT
	@Override
	public void insert(Lesson lesson, String userId) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			insertLsnInfo(con, lesson, userId); 
			insertLsnClassification(con, lesson.getLsnClsfcs()); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
	}
	//1-1. 레슨등록 : 레슨의 레슨분류정보 외 기타정보 DB에 INSERT
	private void insertLsnInfo(Connection con, Lesson lesson, String userId) throws SQLException {
		PreparedStatement pstmt = null;

		String insertLessonSQL = "INSERT INTO lesson (lsn_no, loc_no, lsn_title, lsn_price, "
				+ "lsn_lv, lsn_cnt_sum, lsn_per_time, lsn_intro, lsn_days, lsn_upload_dt, user_id, lsn_status) "
				+ "VALUES (lsn_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
			pstmt = con.prepareStatement(insertLessonSQL);
			pstmt.setString(1, lesson.getLocNo());
			pstmt.setString(2, lesson.getLsnTitle());
			pstmt.setInt(3, lesson.getLsnPrice());
			pstmt.setString(4, lesson.getLsnLv());
			pstmt.setInt(5, lesson.getLsnCntSum());
			pstmt.setInt(6, lesson.getLsnPerTime());
			pstmt.setString(7, lesson.getLsnIntro());
			pstmt.setInt(8, lesson.getLsnDays());
			pstmt.setDate(9, lesson.getLsnUploadDt());
			pstmt.setString(10, userId);
			
			pstmt.executeUpdate();					
			System.out.println("레슨 등록완료");
	}

	//1-2. 레슨등록 : 레슨의 레슨분류정보 DB에 INSERT (PL/SQL : 프로시저 혹은 batch()써보기)
	private void insertLsnClassification(Connection con, List<LessonClsfc> lsnClsfcs) throws SQLException {
		PreparedStatement pstmt = null;
		
		String insertLsnClassificationSQL = 
				"INSERT INTO lesson_classification (lsn_no, club_no) "
						+ "VALUES (lsn_no_seq.CURRVAL, ?)";
		pstmt = con.prepareStatement(insertLsnClassificationSQL);
		
		for(LessonClsfc lsnClsfc: lsnClsfcs) {
			pstmt.setInt(1, lsnClsfc.getClubNo());
			
			pstmt.addBatch();
		}
		
		pstmt.executeBatch();	
		System.out.println("레슨분류 등록완료");
	}
}
