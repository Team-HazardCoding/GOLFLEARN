package com.golflearn.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonHistory;
import com.golflearn.dto.LessonLine;
import com.golflearn.dto.User;
import com.golflearn.exception.FindException;
import com.golflearn.sql.MyConnection;

public class LessonHistoryRepository{
	public List<LessonLine> selectByLsnNo(int lessonNo) throws FindException{
		
		List<LessonLine> lines = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectLessonLineNo = 
				"SELECT ll.lsn_no \"레슨번호\", \r\n"
				+ "       le.lsn_title \"레슨명\",\r\n"
				+ "       ui.user_name \"수강생이름\", \r\n"
				+ "       ui.user_phone \"연락처\", \r\n"
				+ "       ui.user_email \"이메일\",\r\n"
				+ "       MIN(lh.lsn_chk_dt) \"시작일자\", \r\n"
				+ "       ll.lsn_exp_dt \"종료일자\", \r\n"
				+ "       COUNT(lh.lsn_chk_dt) \"현재수업횟수\", \r\n"
				+ "       le.lsn_cnt_sum \"총수업횟수\", \r\n"
				+ "       ll.stdt_lsn_status \"수강생레슨진행상태\"\r\n"
				+ "FROM lesson_line ll JOIN user_info ui ON (ui.user_id = ll.user_id)\r\n"
				+ "                    LEFT JOIN lesson_history lh ON (lh.lsn_line_no = ll.lsn_line_no)\r\n"
				+ "                    JOIN lesson le ON (le.lsn_no = ll.lsn_no) \r\n"
				+ "WHERE ll.lsn_no = ?\r\n"
				+ "GROUP BY ll.lsn_no, ui.user_name, le.lsn_title, \r\n"
				+ "        ui.user_phone, ui.user_email, \r\n"
				+ "        ll.lsn_exp_dt, le.lsn_cnt_sum, \r\n"
				+ "        ll.stdt_lsn_status";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonLineNo);
			pstmt.setInt(1, lessonNo);
			rs = pstmt.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("yyyy년-MM월-dd일");
			while(rs.next()) {
				int lsnNo = rs.getInt("레슨번호");
				String lsnTitle = rs.getString("레슨명");
				String userName = rs.getString("수강생이름");
				String userPhone = rs.getString("연락처");
				String userEmail = rs.getString("이메일");
				Date lsnStartDt = rs.getDate("시작일자");
				Date lsnExpDt = rs.getDate("종료일자");
				int currentLsnCnt = rs.getInt("현재수업횟수");
				int lsnCntSum = rs.getInt("총수업횟수");
				int stdtLsnStatus = rs.getInt("수강생레슨진행상태");
				String strLsnExpDt = format.format(lsnExpDt);
				String strLsnStartDt;
				
				if (lsnStartDt == null) {
					strLsnStartDt = "수강예정";
				}else {
					strLsnStartDt = format.format(lsnStartDt); 
				};
				
				Lesson l = new Lesson();
				l.setLsnCntSum(lsnCntSum);
				l.setLsnTitle(lsnTitle);
				
				LessonHistory lh = new LessonHistory();
//				lh.setLsnStartDt(lsnStartDt);
				lh.setStrLsnStartDt(strLsnStartDt);
				lh.setCurrentLsnCnt(currentLsnCnt);
			
				User ui = new User();
				ui.setUserName(userName);
				ui.setUserPhone(userPhone);
				ui.setUserEmail(userEmail);
				
				LessonLine ll = new LessonLine();
				ll.setLsnNo(lsnNo);
//				ll.setLsnExpDt(lsnExpDt);
				ll.setStrLsnExpDt(strLsnExpDt);
				ll.setStdtLsnStatus(stdtLsnStatus);
				
				ll.setLsn(l);
				ll.setLsnHistory(lh);
				ll.setUser(ui);
				
				lines.add(ll);
			}
			return lines;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
}

