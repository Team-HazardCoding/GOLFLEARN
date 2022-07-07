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
import com.golflearn.exception.FindException;
import com.golflearn.sql.MyConnection;

public class LessonLineRepository {
	//어떤 기능이 있어야 할까 
	/*
	 * 1. 수강생 마이페이지 로딩할때 정보 주는 기능
	 * 2. 강사 마이페이지 로딩할때 정보 주는 기능 
	 * 3. 수강후기 작성 페이지 로딩 ( 내가 클릭한 레슨번호만 받아오기) 
	 * 4. 수강후기 수정 페이지 로딩
	 * 5. 수강후기 작성 후 제출
	 * 6. 수강후기 수정 후 제출
	 * 7. 현재수강생정보 페이지 로딩 할때 정보 주기 
	 */

	/**
	 * 마이페이지 로딩되었을 시 나타낼 정보 
	 * 93saewoo는 입력된 아이디로 바꾸기 
	 * 유저아이디와 같은 레슨내역 보여주기 
	 * @param userId
	 * @return
	 * @throws FindException 
	 */
	public List<LessonLine> selectById(String userId) throws FindException{
		
		//user_id 받아오기 
		List<LessonLine> lines = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//lesson_line 테이블에서 lesson_line가 ?인 모든 컬럼을 가져오기  -> viewmypage
		//유저아이디(어트리뷰트)가 같은 레슨라인들을 쭉 불러오고 
		String selectLessonLineNo = 
				"SELECT ll.lsn_line_no \"레슨내역번호\", le.lsn_no \"레슨번호\", le.lsn_title \"레슨명\", ll.lsn_exp_dt \"종료일자\",\r\n"
				+ "     ll.stdt_lsn_status \"레슨진행상태\", lr.my_star_score \"내가준별점\"\r\n"
				+ "     ,(SELECT COUNT(lsn_chk_dt) FROM lesson_history WHERE lsn_line_no=ll.lsn_line_no ) \"레슨진행횟수\"\r\n"
				+ "     ,le.lsn_cnt_sum \"총레슨횟수\"  \r\n"
				+ "FROM lesson_line ll JOIN lesson le ON (ll.lsn_no = le.lsn_no)\r\n"
				+ "                    LEFT JOIN lesson_review lr ON(ll.lsn_line_no = lr.lsn_line_no)\r\n"
				+ "WHERE ll.user_id = ? \r\n"
				+ "ORDER BY ll.lsn_line_no";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonLineNo);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
//				int lsnLineNo = rs.getInt("lsn_line_no");
				int lsnLineNo = rs.getInt("레슨내역번호");
				int lsnNo = rs.getInt("레슨번호");
				String lsnTitle = rs.getString("레슨명");
				Date lsnExpDt = rs.getDate("종료일자");
				int stdtLsnStatus = rs.getInt("레슨진행상태");
				int myStarScore = rs.getInt("내가준별점");
				int crntLsnCnt = rs.getInt("레슨진행횟수");  //나중에 꼭 구현해야함 
				int lsnCntSum = rs.getInt("총레슨횟수");
				
//				System.out.println(lsnLineNo);
//				System.out.println(lsnNo);
//				System.out.println(lsnTitle);
//				System.out.println(lsnExpDt);
//				System.out.println(stdtLsnStatus);
//				System.out.println(myStarScore);
//				System.out.println(crntLsnCnt);
//				System.out.println(lsnCntSum);
//				System.out.println("--------------");
				
				//여기에 crntLsnCnt없음 
				Lesson le = new Lesson();
				le.setLsnTitle(lsnTitle);
				le.setLsnCntSum(lsnCntSum);
				
				LessonReview lr = new LessonReview();
				lr.setMyStarScore(myStarScore);
				//레슨진행횟수 
				
				LessonLine ll = new LessonLine();
				ll.setLsnNo(lsnNo);
				ll.setLsnLineNo(lsnLineNo);
				ll.setLsnExpDt(lsnExpDt);
				ll.setStdtLsnStatus(stdtLsnStatus);
				ll.setLsn(le);
				ll.setLsnReview(lr);
				
				System.out.println(lr.toString(myStarScore));
				System.out.println(ll.toString(le.toString(lsnTitle,lsnCntSum), lr.toString(myStarScore)));
//				System.out.println(lines.get(0));
				lines.add(ll);
				//쌤예시 
//				Map<String,Object> map1 = new HashMap<>();
//				map1.put("prod_no", prod_no);
//				map1.put("prod_name", prod_name);
//				map1.put("prod_price", prod_price);
//				sample.add(map1); //List타입의 sample변수에 map을 저장해보았음 
				return lines;
				
			}
			throw new FindException(userId +"가 없습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	public List<LessonLine> selectByProId(String userProId) throws FindException{
		
		//user_id 받아오기 
		List<LessonLine> lines = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectLessonLineNo = 
				"SELECT lsn_title, lsn_status\r\n"
				+ "FROM lesson\r\n"
				+ "WHERE user_id = ? ";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonLineNo);
			pstmt.setString(1, userProId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String lsnTitle = rs.getString("lsn_title");
				int lsnStatus = rs.getInt("lsn_status");
				
				Lesson le = new Lesson();
				le.setLsnTitle(lsnTitle);
				le.setLsnStatus(lsnStatus);
				
				LessonLine ll = new LessonLine();
				ll.setLsn(le);
				lines.add(ll);
				return lines;
			}
			throw new FindException(userProId + "가 없습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	public int selectTypeById(String userId) throws FindException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectLessonReview = 
				"SELECT user_type\r\n"
				+ "FROM user_info\r\n"
				+ "WHERE user_id = ?";
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
			throw new FindException(userId + "가 없습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

}