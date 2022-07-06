package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.golflearn.dto.Lesson;
import com.golflearn.exception.AddException;
import com.golflearn.exception.FindException;
import com.golflearn.sql.MyConnection;

public class LessonOracleRepository implements LessonRepository{

	@Override
	public void insert(Lesson lesson) throws AddException {
		
	}

	@Override
	public Lesson selectByLsnNo(String userId, int lsnNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectLsnNoSQL = "SELECT l.lsn_no, l.lsn_title, l.lsn_intro, l.lsn_star_ppl_cnt, \r\n"
							  		 + "l.user_id, l.lsn_lv, l.lsn_price, \r\n"
							  		 + "l.lsn_per_time, l.lsn_days, l.lsn_star_sum / l.lsn_star_ppl_cnt, \r\n"
							  		 + "(SELECT SUM(lsn_star_sum/lsn_star_ppl_cnt)/COUNT(lsn_no) \r\n"
							  		  + "FROM lesson \r\n"
							  		  + "WHERE user_id= ?), \r\n"
							  		 + "ui.user_name, \r\n"
							  		 + "lc.loc_sido, lc.loc_sigungu, \r\n"
							  		 + "pi.pro_career, \r\n"
							  		 + "ll.user_id, \r\n"
							  		 + "lr.review, \r\n"
							  		 + "lr.review_dt \r\n"
							  + "FROM lesson l JOIN user_info ui ON (l.user_id = ui.user_id)\r\n"
							  				+ "JOIN pro_info pi ON (ui.user_id = pi.user_id)\r\n"
							  				+ "JOIN location lc ON (l.loc_no = lc.loc_no)\r\n"
							  				+ "JOIN lesson_line ll ON (l.lsn_no = ll.lsn_no)\r\n"
							  				+ "JOIN lesson_review lr ON (ll.lsn_line_no = lr.lsn_line_no)\r\n"
							  + "WHERE l.lsn_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLsnNoSQL);
			pstmt.setString(1, userId);
			pstmt.setInt(2, lsnNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 1번이슈 : !!lesson이 아닌 JOIN 해온 다른 테이블은 getString해 올 값의 명명 알아보기
				// 2번이슈 : !!lsnStarScore나 proStarScore처럼 getInt해 올 값이 서브쿼리문으로 가는게 맞는지??
				// 3번이슈 : !!locSido나 locSigungu처럼 DB에서는 숫자로 넘어오는값을
				//			 int형으로 get할지, 웹으로 뿌려지는 값은 String값이니 String으로 get할지
				String lsnTitle = rs.getString("lsn_Title");
				String lsnIntro = rs.getString("lsn_intro");
				int lsnReviewCnt = rs.getInt("lsn_star_ppl_cnt");
				String lsnUserId = rs.getString("user_id");
				int lsnLv = rs.getInt("lsn_lv");
				int lsnPrice = rs.getInt("lsn_price");
				int lsnPerTime = rs.getInt("lsn_per_time");
				int lsnDays = rs.getInt("lsn_days");
				int lsnStarScore = rs.getInt("lsn_star_sum / lsn_star_ppl_cnt"); //2
				int proStarScore = rs.getInt("SELECT SUM(lsn_star_sum/lsn_star_ppl_cnt)/COUNT(lsn_no) \r\n" //2
										   + "FROM lesson\r\n"
										   + "WHERE user_id= ?");
				String proName = rs.getString("user_name");
				String locSido = rs.getString("loc_sido");	//3
				String locSigungu = rs.getString("locSigungu"); //3
				String proIntro = rs.getString("pro_career");
				String ReviewUserId = rs.getString("user_id");
				String review = rs.getString("review");
				java.sql.Date reviewDt = rs.getDate("reviewDt");
				
				Lesson l = new Lesson(lsnNo,
									  lsnTitle,
									  lsnReviewCnt,
									  lsnUserId,
									  lsnLv,
									  lsnPrice,
									  lsnPerTime,
									  lsnDays,
									  lsnStarScore,
									  proStarScore,
									  proName,
									  locSido,
									  locSigungu,
									  proIntro,
									  ReviewUserId,
									  review,
									  reviewDt);
				return l;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException("레슨이 존재하지 않습니다");
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
}
