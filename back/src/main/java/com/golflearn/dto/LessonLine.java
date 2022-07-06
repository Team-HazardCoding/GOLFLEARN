package com.golflearn.dto;

import java.util.ArrayList;
import java.util.Date;

public class LessonLine {
	private int lsnLineNo;
	private int lsnNo;
	private int stdtLsnStatus;
	private Date lsnExpDt;
	private Date lsnAplyDt;
	private User user;
	private String userId;
	private LessonReview lsnReview;
	private StdtManage stdtManage;
	private ArrayList<Lesson> lsn;
	private ArrayList<LessonHistory> lsnHistory;
	//레슨내역번호
	public int getLsnLineNo() {
		return this.lsnLineNo;
	}
	public void setLsnLineNo(int lsnLineNo) {
		this.lsnLineNo = lsnLineNo;
	}
	//레슨번호
	public int getLsnNo() {
		return this.lsnNo;
	}
	public void setLsnNo(int lsnNo) {
		this.lsnNo = lsnNo;
	}
	
	//수강생 레슨상태
	public int getStdtLsnStatus() {
		return this.stdtLsnStatus;
	}
	public void setStdtLsnStatus(int stdtLsnStatus) {
		this.stdtLsnStatus = stdtLsnStatus;
	}
	
	//레슨만료일 
	public Date getLsnExpDt() {
		return this.lsnExpDt;
	}
	public void setLsnExpDt(Date lsnExpDt) {
		this.lsnExpDt = lsnExpDt;
	}
	
	//레슨신청일
	public Date getLsnAplyDt() {
		return this.lsnAplyDt;
	}
	public void setLsnAplyDt(Date lsnAplyDt) {
		this.lsnAplyDt = lsnAplyDt;
	}
	
	//유저 
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//유저아이디? 
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	//레슨후기
	public LessonReview getLsnReview() {
		return this.lsnReview;
	}
	public void setLsnReview(LessonReview lsnReview) {
		this.lsnReview = lsnReview;
	}
	
	//수강생관리
	public StdtManage getStdtManage() {
		return this.stdtManage;
	}
	public void setStdtManage(StdtManage stdtManage) {
		this.stdtManage = stdtManage;
	}
	
	//레슨
	public ArrayList<Lesson> getLsn() {
		return this.lsn;
	}
	public void setLsn(ArrayList<Lesson> lsn) {
		this.lsn = lsn;
	}
	
	//레슨기록
	public ArrayList<LessonHistory> getLsnHistory() {
		return this.lsnHistory;
	}
	public void setLsnHistory(ArrayList<LessonHistory> lsnHistory) {
		this.lsnHistory = lsnHistory;
	}
	
	
	
	
	
}
