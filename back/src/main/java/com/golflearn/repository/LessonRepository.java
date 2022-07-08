package com.golflearn.repository;

import java.util.List;

import com.golflearn.dto.Lesson;
import com.golflearn.dto.Pro;
import com.golflearn.exception.AddException;
import com.golflearn.exception.FindException;

public interface LessonRepository {
	/**
	 * 레슨을 등록한다
	 * @param Lesson 레슨객체
	 * @throws AddException 레슨번호가 중복될 경우 "이미 존재하는 레슨입니다" 상세메시지를 갖는 예외가 발생한다
	 */
	public void insert(Lesson l) throws AddException;
	
	/**
	 * 레슨을 조회한다
	 * @param lsn_no 레슨번호
	 * @return Lesson 레슨객체
	 * @throws FindException 레슨번호에 해당하는 레슨이 없으면 "레슨을 찾을 수 없습니다" 상세메시지를 갖는 예외가 발생한다
	 */
	public Lesson selectByLsnNo(String lsn_no) throws FindException;
	
	/**
	 * 해당 아이디에 user_type을 조회한다
	 * @param user_id 아이디
	 * @return user_type 유저타입
	 * @throws FindException 아이디가 존재하지 않으면 "아이디를 찾을 수 없습니다" 상세메시지를 갖는 예외가 발생한다
	 */
	public int selectTypeById(String userId) throws FindException;
}
