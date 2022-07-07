package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonClsfc;
import com.golflearn.dto.LessonLine;
import com.golflearn.dto.LessonReview;
import com.golflearn.dto.Location;
import com.golflearn.dto.Pro;
import com.golflearn.dto.User;
import com.golflearn.exception.AddException;
import com.golflearn.exception.FindException;
import com.golflearn.sql.MyConnection;

public class LessonOracleRepository implements LessonRepository {
	//레슨등록 : 레슨의 기타 정보들과 레슨분류를 한꺼번에 insert
	@Override
	public void insert(Lesson l) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			insertLsnInfo(con, l);
			insertLsnClassification(con, l.getLsnClfc());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//레슨등록 : 레슨의 기타 정보
	private void insertLsnInfo(Connection con, Lesson l) throws SQLException {
		PreparedStatement pstmt = null;
		String insertLessonSQL = "INSERT INTO lesson (lsn_no, loc_sido, loc_sigungu, lsn_title, "
				+ "lsn_price, lsn_lv, lsn_cnt_sum, lsn_per_time, lsn_intro, lsn_days), "
				+ "VALUES (lsn_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(insertLessonSQL);
		pstmt.setString(1, l.getLsnTitle());
		pstmt.setInt(2, l.getLsnPrice());
		pstmt.setInt(3, l.getLsnLv());
		pstmt.setInt(4, l.getLsnCntSum());
		pstmt.setInt(5, l.getLsnPerTime());
		pstmt.setString(6, l.getLsnIntro());
		pstmt.setInt(7, l.getLsnDays());
	}
	//레슨등록 : 레슨의 레슨분류 정보(PL/SQL 사용해보기)
	private void insertLsnClassification(Connection con, ArrayList<LessonClsfc> lsnClsfc) throws SQLException {
		PreparedStatement pstmt = null;
		String insertLsnClassificationSQL = 
				"INSERT INTO lesson_classification(lsn_no, club_no) VALUES (?, ?)";
		
	}
	
	//레슨상세보기 : 해당 레슨번호에 해당하는 레슨조회
	@Override
	public Lesson selectByLsnNo(String lsnNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectLsnNoSQL = "SELECT l.lsn_no, l.lsn_title, l.lsn_intro, l.lsn_star_ppl_cnt, "
							  		 + "l.lsn_lv, l.lsn_price, "
							  		 + "l.lsn_per_time, l.lsn_days, l.lsn_star_sum / l.lsn_star_ppl_cnt 레슨별점, "
							  		 + "(SELECT SUM(lsn_star_sum/lsn_star_ppl_cnt)/COUNT(lsn_no) "
							  		  + "FROM lesson "
							  		  + "WHERE user_id= l.user_id) 프로별점, "
							  		 + "ui.user_name, "
							  		 + "lc.loc_sido, lc.loc_sigungu, "
							  		 + "pi.pro_career, "
							  		 + "ll.user_id, "
							  		 + "lr.review, "
							  		 + "lr.review_dt "
							  + "FROM lesson l JOIN user_info ui ON (l.user_id = ui.user_id) "
							  				+ "JOIN pro_info pi ON (ui.user_id = pi.user_id) "
							  				+ "JOIN location lc ON (l.loc_no = lc.loc_no) "
							  				+ "JOIN lesson_line ll ON (l.lsn_no = ll.lsn_no) "
							  				+ "JOIN lesson_review lr ON (ll.lsn_line_no = lr.lsn_line_no) "
							  + "WHERE l.lsn_no = ?";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLsnNoSQL);
			pstmt.setString(1, lsnNo);	//parseString
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String lsnTitle = rs.getString("lsn_title");
				String lsnIntro = rs.getString("lsn_intro");
				int lsnReviewCnt = rs.getInt("lsn_star_ppl_cnt");
				int lsnLv = rs.getInt("lsn_lv");
				int lsnPrice = rs.getInt("lsn_price");
				int lsnPerTime = rs.getInt("lsn_per_time");
				int lsnDays = rs.getInt("lsn_days");
				float lsnStarScore = rs.getFloat("레슨별점");
				float proStarScore = rs.getFloat("프로별점");
				// 서브쿼리 대안 : 서브쿼리 내 lsn_star_sum과 lsn_star_ppl_cnt를 따로 받는다
				// + int lsnStarSum = rs.getInt("lsn_star_sum");
				// + int lsnStarPplCnt = rs.getInt("lsn_star_ppl_cnt");
				// 받은 후 lsnStarScore에 대한 계산은 자바단에서 따로한다.
				// ex) int lsnStarScore = rs.getInt(lsnStarSum / lsnStarPplCnt) -- dto를 따로 만드는 방법
				String proName = rs.getString("user_name");
				String locSido = rs.getString("loc_sido"); //2
				String locSigungu = rs.getString("loc_sigungu"); //2
				String proIntro = rs.getString("pro_career");
				String reviewUserId = rs.getString("user_id");
				String review = rs.getString("review");
				java.sql.Date reviewDt = rs.getDate("review_dt");
				
				//1. Lesson.java DTO에 있는 생성자 호출
				//Lesson lsn = new Lesson(lsnNo, lsnTitle, lsnIntro, lsnReviewCnt, lsnLv, lsnPrice,
				//		lsnPerTime, lsnDays, lsnStarScore, proStarScore, proName, locSido,
				//		locSigungu, proIntro, reviewUserId, review, reviewDt);
				
				//2. 생성자 호출필요없이 레포지토리에 직접 선언
				User ui = new User();
				ui.setUserName(proName);
				Location lc = new Location();
				lc.setLocSido(locSido);
				lc.setLocSigungu(locSigungu);
				Pro pr = new Pro();
				pr.setProCareer(proIntro);
				LessonLine ll = new LessonLine();
				ll.setUser(ui); // 타고 타는 경우 
				LessonReview lr = new LessonReview();
				lr.setReview(review);
				lr.setReviewDt(reviewDt);
				Lesson l = new Lesson();
				l.setLsnNo(lsnNo);	// ??
				l.setLsnTitle(lsnTitle);
				l.setLsnIntro(lsnIntro);
				l.setLsnStarPplCnt(lsnReviewCnt);
				l.setLsnLv(lsnLv);
				l.setLsnPrice(lsnPrice);
				l.setLsnPerTime(lsnPerTime);
				l.setLsnDays(lsnDays);
				l.setLsnStarScore(lsnStarScore);
				l.setProStarScore(proStarScore);
				l.setUser(ui);
				l.setLocation(lc);
				l.setPro(pr);
				l.setLsnLine(ll);
				l.setLsnReview(lr);
//				l.toString(
//						//레슨클래스에 tostring오버라이딩
//						//매개변수는 레슨제외한 객체들의 반환값이야 
				
				return l;
			} else {
				throw new FindException(lsnNo + "레슨번호가 존재하지 않습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
}
