package com.golflearn.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonLine;
import com.golflearn.dto.LessonReview;
import com.golflearn.dto.Pro;
import com.golflearn.dto.User;
import com.golflearn.exception.FindException;
import com.golflearn.sql.MyConnection;

public class LessonOracleRepository implements LessonRepository {
	//레슨상세보기 : 해당 레슨번호에 해당하는 레슨조회 (후기조회때문에 select를 따로받아야됨)
	@Override
	public Lesson selectByLsnNo(int lsnNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectLsnNoSQL = "SELECT l.lsn_no, l.lsn_title, l.lsn_intro, l.lsn_star_ppl_cnt, "
							  + "l.lsn_lv, l.lsn_price, l.user_id 프로아이디, l.loc_no, l.lsn_cnt_sum, "
							  + "l.lsn_per_time, l.lsn_days, TRUNC((l.lsn_star_sum / l.lsn_star_ppl_cnt), 2) 레슨별점, "
							  + "TRUNC((SELECT SUM(lsn_star_sum/lsn_star_ppl_cnt)/COUNT(lsn_no) "
							  		 + "FROM lesson "
							  		 + "WHERE user_id= l.user_id), 2) 프로별점, "
							  + "ui.user_name 프로명,"
							  + "pi.pro_career, "
							  + "ll.user_id 후기작성자아이디, ll.lsn_line_no, "
							  + "lr.review, "
							  + "lr.review_dt "
				+ "FROM lesson l JOIN user_info ui ON (l.user_id = ui.user_id) "
							  + "JOIN pro_info pi ON (ui.user_id = pi.user_id) "
							  + "JOIN lesson_line ll ON (l.lsn_no = ll.lsn_no) "
							  + "JOIN lesson_review lr ON (ll.lsn_line_no = lr.lsn_line_no) "
				+ "WHERE l.lsn_no = ?";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLsnNoSQL);
			pstmt.setInt(1, lsnNo);
			rs = pstmt.executeQuery();

//			List<LessonLine> lines = new ArrayList<>(); //레슨내역들
			//레슨정보 등록
			Lesson lesson = null; //레슨객체 
			List<LessonLine> lines = new ArrayList<>();
			while(rs.next()) {
				//첫행일경우만 레슨객체정보설정
				if(lesson == null) {
					lesson = new Lesson();
					
					//레슨에 레슨라인들 설정
					lesson.setLines(lines); 
					
					lesson.setLsnNo(lsnNo);
					lesson.setLsnTitle(rs.getString("lsn_title"));
					lesson.setLsnIntro(rs.getString("lsn_intro"));
					lesson.setLsnStarPplCnt(rs.getInt("lsn_star_ppl_cnt"));
					lesson.setLsnLv(rs.getString("lsn_lv"));
					lesson.setLsnPrice(rs.getInt("lsn_price"));
					lesson.setLocNo(rs.getString("loc_no"));
					lesson.setLsnPerTime(rs.getInt("lsn_per_time"));
					lesson.setLsnDays(rs.getInt("lsn_days"));
					lesson.setLsnStarScore(rs.getFloat("레슨별점"));
					lesson.setProStarScore(rs.getFloat("프로별점"));
					lesson.setLsnCntSum(rs.getInt("lsn_cnt_sum"));
					
					User user = new User();
					user.setUserID(rs.getString("프로아이디"));
					user.setUserName(rs.getString("프로명"));
					lesson.setUser(user);
					
					Pro pro = new Pro();
					pro.setProCareer(rs.getString("pro_career"));
					lesson.setPro(pro);
					
				}
				
				//레슨라인1개
				LessonLine line = new LessonLine();
				line.setLsnLineNo(rs.getInt("lsn_line_no"));
				User user = new User();
				user.setUserID(rs.getString("후기작성자아이디"));
				line.setUser(user);
				
				//후기1개
				LessonReview review = new LessonReview();
				review.setReview(rs.getString("review"));
				review.setReviewDt(rs.getDate("review_dt"));
						
				//후기를 라인에 설정
				line.setLsnReview(review);
				
				//라인을 레슨라이들에 추가
				lines.add(line);
			}
			
			if(lesson == null) {
				throw new FindException(lsnNo + "번 레슨이 존재하지 않습니다");
			}
			
			return lesson;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	
	@Override
	// 기능메소드 : userId에 해당하는 user_type을 반환한다 (0, 1)
	public int selectTypeById(String userId) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectLessonReview = 
				"SELECT user_type FROM user_info WHERE user_id = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonReview);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();		
			if(rs.next()) {
				int userType = rs.getInt("user_type"); 
				System.out.println(userType);
				return userType;
			}
			throw new FindException(userId + "아이디가 존재하지 않습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	
	@Override
	public List<Lesson> selectAll() {
		// DB와의 연결준비를 한다.
		Connection con = null;
		List<Lesson> lsnList = new ArrayList<>();
		try {
			con = MyConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			// 각 레슨의 별점은 자바단에서 계산하는것이 더 낫다고 하셔서 계산에 필요한 두 칼럼을 가져온후 계산함. 
			String selectAllLsnSQL = "SELECT lsn_no, loc_no, lsn_title, lsn_upload_dt, lsn_star_sum, lsn_star_ppl_cnt, ui.user_name 프로명\n"
					+ "FROM lesson l JOIN user_info ui ON(l.user_id = ui.user_id)\n"
					+ "ORDER BY 1 DESC";
			pstmt = con.prepareStatement(selectAllLsnSQL);
			rs = pstmt.executeQuery();
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
//				String locSido = rs.getString("시도");
//				String locSigungu = rs.getString("시군구");
				// 게산하는거 맵형식으로 해보기
				
				//레슨 한줄한줄을 읽어서 레슨객체에 저장함.
				User user = new User(userName);				
				
//				Location location = new Location();
//				location.setSido(locSido);
//				location.setSigungu(locSigungu);
				
				Lesson lsn = new Lesson(lsnNo, lsnTitle, lsnUploadDt, lsnStarPoint, user, locNo); // 생성자로 고칠수 있는 부분
				// 레슨객체를 레슨리스트객체에 추가시킴
//				lsn.toString(); 
				lsnList.add(lsn);
				System.out.println("lsn객체 만들어짐");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	    	MyConnection.close(null, con);
		}
		return lsnList;//list를 반환 
	}
	
	public List<Lesson> filterLesson() {
		Connection con = null;
		List<Lesson> lsnList = new ArrayList<>();
		return lsnList;
		
	}
}
