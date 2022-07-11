package com.golflearn.dto;

import java.util.Date;

public class LessonReview {
	private LessonLine line;
//	private int lsnLineNo;
	private int myStarScore;
	private String review;
	private Date reviewDt;
	private Date reviewEditDt;
	
	public LessonLine getLine() {
		return line;
	}
	public void setLine(LessonLine line) {
		this.line = line;
	}
	//	public int getLsnLineNo() {
//		return lsnLineNo;
//	}
//	public void setLsnLineNo(int lsnLineNo) {
//		this.lsnLineNo = lsnLineNo;
//	}
	public int getMyStarScore() {
		return myStarScore;
	}
	public void setMyStarScore(int myStarScore) {
		this.myStarScore = myStarScore;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Date getReviewDt() {
		return reviewDt;
	}
	public void setReviewDt(Date reviewDt) {
		this.reviewDt = reviewDt;
	}
	public Date getReviewEditDt() {
		return reviewEditDt;
	}
	public void setReviewEditDt(Date reviewEditDt) {
		this.reviewEditDt = reviewEditDt;
	}
}
