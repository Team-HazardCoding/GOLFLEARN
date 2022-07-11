package com.golflearn.repository;

import java.sql.SQLException;
import java.util.List;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.LessonClsfc;
import com.golflearn.exception.AddException;

public interface AddLessonRepository {
	/**
	 * 레슨을 등록한다
	 * @param Lesson 레슨객체
	 * @throws AddException 레슨번호가 중복될 경우 "이미 존재하는 레슨입니다" 상세메시지를 갖는 예외가 발생한다
	 */
	public void insert(Lesson l) throws AddException;
//	public void insertLsnInfo(int locNo, String lsnTitle, int lsnPrice, int lsnLv,
//			int lsnCntSum, int lsnPerTime, String lsnIntro, int lsnDays, java.sql.Date lsnUploadDt) throws AddException;
//	public void insertLsnInfo2(Lesson l) throws AddException;
	
//	public void insertLsnClassification(List<LessonClsfc> lsnClsfc, int clubNo) throws AddException;
}
