package com.golflearn.dto;

import java.util.ArrayList;
import java.util.Date;

public class LessonLine {
	private int lsnLineNo;
	private int stdtLsnStatus;
	private Date lsnExpDt;
	private Date lsnAplyDt;
	private User user;
	private LessonReview lsnReview;
	private StdtManage stdtManage;
	private Lesson lsn;
	private ArrayList<LessonHistory> lsnHistory;
	
	public int getLsnLineNo() {
		return lsnLineNo;
	}
	public void setLsnLineNo(int lsnLineNo) {
		this.lsnLineNo = lsnLineNo;
	}
	public int getStdtLsnStatus() {
		return stdtLsnStatus;
	}
	public void setStdtLsnStatus(int stdtLsnStatus) {
		this.stdtLsnStatus = stdtLsnStatus;
	}
	public Date getLsnExpDt() {
		return lsnExpDt;
	}
	public void setLsnExpDt(Date lsnExpDt) {
		this.lsnExpDt = lsnExpDt;
	}
	public Date getLsnAplyDt() {
		return lsnAplyDt;
	}
	public void setLsnAplyDt(Date lsnAplyDt) {
		this.lsnAplyDt = lsnAplyDt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LessonReview getLsnReview() {
		return lsnReview;
	}
	public void setLsnReview(LessonReview lsnReview) {
		this.lsnReview = lsnReview;
	}
	public StdtManage getStdtManage() {
		return stdtManage;
	}
	public void setStdtManage(StdtManage stdtManage) {
		this.stdtManage = stdtManage;
	}
	public Lesson getLsn() {
		return lsn;
	}
	public void setLsn(Lesson lsn) {
		this.lsn = lsn;
	}
	public ArrayList<LessonHistory> getLsnHistory() {
		return lsnHistory;
	}
	public void setLsnHistory(ArrayList<LessonHistory> lsnHistory) {
		this.lsnHistory = lsnHistory;
	}
}
