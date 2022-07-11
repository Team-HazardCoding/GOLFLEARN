package com.golflearn.dto;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
	private int lsnNo;
	private String lsnTitle;
	private String lsnIntro;
	private int lsnLv;	
	private int lsnDays;
	private int lsnPrice;
	private int lsnPerTime;
	private int lsnCntSum;
	private int lsnStarSum;
	private int lsnStarPplCnt;
	private float lsnStarScore;	//레슨별점 : pjw
	private float proStarScore;	//프로별점 : pjw
	private int lsnStatus;
	private int locationNo;	//지역테이블없애기로해서 생성 : 여기에 지역정보넣으라고하심 : pjw
	private User user;
	private java.sql.Date lsnUploadDt;
	private List<LessonClsfc> lsnClsfcs;
	private List<LessonLine> lines;
//	private Location location;	//지역테이블없애기로함
	private Pro pro;
	private LessonReview lsnReview;
	
	public Lesson(){}
	
	

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

	public int getLsnLv() {
		return lsnLv;
	}

	public void setLsnLv(int lsnLv) {
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

	public int getLocationNo() {
		return locationNo;
	}

	public void setLocationNo(int locationNo) {
		this.locationNo = locationNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public java.sql.Date getLsnUploadDt() {
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
		return lines;
	}

	public void setLines(List<LessonLine> lines) {
		this.lines = lines;
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
//		return "Lesson [lsnNo=" + lsnNo + ", lsnTitle=" + lsnTitle + ", lsnIntro=" + lsnIntro + ", lsnLv=" + lsnLv
//				+ ", lsnDays=" + lsnDays + ", lsnPrice=" + lsnPrice + ", lsnPerTime=" + lsnPerTime + ", lsnCntSum="
//				+ lsnCntSum + ", lsnStarSum=" + lsnStarSum + ", lsnStarPplCnt=" + lsnStarPplCnt + ", lsnStarScore="
//				+ lsnStarScore + ", proStarScore=" + proStarScore + ", lsnStatus=" + lsnStatus + ", locationNo="
//				+ locationNo + ", user=" + user + ", lsnUploadDt=" + lsnUploadDt + ", lsnClsfcs=" + lsnClsfcs
//				+ ", lines=" + lines + ", pro=" + pro + ", lsnReview=" + lsnReview + "]";
		return lsnClsfcs + "입니다";
	}
}
