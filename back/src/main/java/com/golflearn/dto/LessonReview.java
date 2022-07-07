package com.golflearn.dto;

import java.util.Date;

public class LessonReview {
	private int lsnLineNo;
	private int myStarScore;
	private String review;
	private Date reviewDt;
	private Date reviewEditDt;
	//세터 게터 메서드 
	//레슨내역번호
	
	public String toString(int lsnLineNo, int myStarScore, String review) {
		return "레슨내역번호 : " + this.lsnLineNo 
				+ "레슨총횟수 : " + this.myStarScore
				+ "레슨후기 : " + this.review
				;
	}
	public String toString(int myStarScore) {
		return "내가준별점 : " + this.myStarScore;
	}
	
	public int getLsnLineNo() {
		return this.lsnLineNo;
	}
	public void setLsnLineNo(int lsnLineNo) {
		this.lsnLineNo = lsnLineNo;
	}
	
	//별점 
	public int getMyStarScore() {
		return this.myStarScore;
	}
	public void setMyStarScore(int myStarScore) {
		this.myStarScore = myStarScore;
	}
	
	//후기 
	public String getReview() {
		return this.review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	//후기일자
	public Date getReviewDt() {
		return this.reviewDt;
	}
	public void setReviewDt(Date reviewDt) {
		this.reviewDt = reviewDt;
	}
	
	//후기수정일자
	public Date getReviewEditDt() {
		return this.reviewEditDt;
	}
	public void setReviewEditDt(Date reviewEditDt) {
		this.reviewEditDt = reviewEditDt;
	}
	

}
