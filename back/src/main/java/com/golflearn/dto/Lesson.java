package com.golflearn.dto;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Lesson {
	private int lsnNo;
	private String lsnTitle;
	private String lsnIntro;
	private String lsnLv;	
	private int lsnDays;
	private int lsnPrice;
	private int lsnPerTime;
	private int lsnCntSum;
	private int lsnStarSum;
	private int lsnStarPplCnt;
	private float lsnStarScore;	//레슨별점 : pjw
	private float proStarScore;	//프로별점 : pjw
	private int lsnStatus;
	private int UploadDt;
	private Date lsnUploadDt;
	private List<LessonClsfc> lsnClsfcs;
	private List<LessonLine> lsnLines;
	private String locNo;
	private int lsnStarPoint;
	private User user;
	private Pro pro;
	private LessonLine lsnLine;
	private LessonReview lsnReview;
	
	public Lesson(){}
	// 메인페이지의 레슨목록 보여줄때 필요한 생성자
	public Lesson(int lsnNo, String lsnTitle, Date lsnUploadDt, int lsnStarPoint, User user, String locNo) {
		this.lsnNo = lsnNo;
		this.lsnTitle = lsnTitle;
		this.lsnUploadDt = lsnUploadDt;
		this.lsnStarPoint = lsnStarPoint;
		this.user = user;
		this.locNo = locNo;
				
	}
	
	public void toString(int lsnNo, String lsnTitle, Date lsnUploadDt, int lsnStarPoint, User user, String locNo) {
		System.out.println(lsnTitle + ", " + lsnUploadDt + ", " + lsnStarPoint + ", " + user.toString() + ", " + locNo);
	}
	
	//게터 세터 메서드 
	//1. 레슨번호

	public int getLsnNo() {
		return lsnNo;
	}

	public void setLsnNo(int lsnNo) {
		this.lsnNo = lsnNo;
	}

	public String getLsnTitle() {
		return lsnTitle;
	}

	public void setLsnTitle(String lsnTitle) {
		this.lsnTitle = lsnTitle;
	}

	public String getLsnIntro() {
		return lsnIntro;
	}

	public void setLsnIntro(String lsnIntro) {
		this.lsnIntro = lsnIntro;
	}

	public String getLsnLv() {
		return lsnLv;
	}

	public void setLsnLv(String lsnLv) {
		this.lsnLv = lsnLv;
	}

	public int getLsnDays() {
		return lsnDays;
	}

	public void setLsnDays(int lsnDays) {
		this.lsnDays = lsnDays;
	}

	public int getLsnPrice() {
		return lsnPrice;
	}

	public void setLsnPrice(int lsnPrice) {
		this.lsnPrice = lsnPrice;
	}

	public int getLsnPerTime() {
		return lsnPerTime;
	}

	public void setLsnPerTime(int lsnPerTime) {
		this.lsnPerTime = lsnPerTime;
	}

	public int getLsnCntSum() {
		return lsnCntSum;
	}

	public void setLsnCntSum(int lsnCntSum) {
		this.lsnCntSum = lsnCntSum;
	}

	public int getLsnStarSum() {
		return lsnStarSum;
	}

	public void setLsnStarSum(int lsnStarSum) {
		this.lsnStarSum = lsnStarSum;
	}

	public int getLsnStarPplCnt() {
		return lsnStarPplCnt;
	}

	public void setLsnStarPplCnt(int lsnStarPplCnt) {
		this.lsnStarPplCnt = lsnStarPplCnt;
	}

	public float getLsnStarScore() {
		return lsnStarScore;
	}

	public void setLsnStarScore(float lsnStarScore) {
		this.lsnStarScore = lsnStarScore;
	}

	public float getProStarScore() {
		return proStarScore;
	}

	public void setProStarScore(float proStarScore) {
		this.proStarScore = proStarScore;
	}

	public int getLsnStatus() {
		return lsnStatus;
	}

	public void setLsnStatus(int lsnStatus) {
		this.lsnStatus = lsnStatus;
	}

	public String getLocNo() {
		return locNo;
	}

	public void setLocNo(String locNo) {
		this.locNo = locNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLsnUploadDt() {
		return lsnUploadDt;
	}

	public void setLsnUploadDt(java.sql.Date lsnUploadDt) {
		this.lsnUploadDt = lsnUploadDt;
	}


	public List<LessonClsfc> getLsnClsfcs() {
		return lsnClsfcs;
	}

	public void setLsnClsfcs(List<LessonClsfc> lsnClsfcs) {
		this.lsnClsfcs = lsnClsfcs;
	}

	public List<LessonLine> getLines() {
		return lsnLines;
	}

	public void setLines(List<LessonLine> lines) {
		this.lsnLines = lines;
	}
	
	public void setLsnLine(LessonLine lsnLine) {
		this.lsnLine = lsnLine;
	}

	public Pro getPro() {
		return pro;
	}

	public void setPro(Pro pro) {
		this.pro = pro;
	}

	public LessonReview getLsnReview() {
		return lsnReview;
	}

	public void setLsnReview(LessonReview lsnReview) {
		this.lsnReview = lsnReview;
	}

	@Override
	public String toString() {
		return "Lesson [lsnNo=" + lsnNo + ", lsnTitle=" + lsnTitle + ", lsnIntro=" + lsnIntro + ", lsnLv=" + lsnLv
				+ ", lsnDays=" + lsnDays + ", lsnPrice=" + lsnPrice + ", lsnPerTime=" + lsnPerTime + ", lsnCntSum="
				+ lsnCntSum + ", lsnStarSum=" + lsnStarSum + ", lsnStarPplCnt=" + lsnStarPplCnt + ", lsnStarScore="
				+ lsnStarScore + ", proStarScore=" + proStarScore + ", lsnStatus=" + lsnStatus + ", locNo=" + locNo
				+ ", user=" + user + ", lsnUploadDt=" + lsnUploadDt + ", lsnClsfcs=" + lsnClsfcs + ", lines=" + lsnLines
				+ ", pro=" + pro + ", lsnReview=" + lsnReview + "]";
	}
}
