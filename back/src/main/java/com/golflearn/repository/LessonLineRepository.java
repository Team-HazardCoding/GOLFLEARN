package com.golflearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.golflearn.dto.LessonLine;
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
	 * lsn_line_no 정렬순대로 나타내게 하기 
	 * 93saewoo는 입력된 아이디로 바꾸기 
	 * @param lessonLineNo
	 * @return
	 */
	public List<LessonLine> selectByLessonLineNo(String lessonLineNo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//lesson_line 테이블에서 lesson_line가 ?인 모든 컬럼을 가져오기 
		String selectLessonLineNo = 
				"SELECT *\r\n"
				+ "FROM lesson_line \r\n"
				+ "WHERE lsn_line_no = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectLessonLineNo);
			pstmt.setString(1, lessonLineNo);
			rs = pstmt.executeQuery();
			
			





		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; //임시 리턴 


	}

}