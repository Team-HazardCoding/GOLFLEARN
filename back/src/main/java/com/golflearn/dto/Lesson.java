package com.golflearn.dto;

import java.util.ArrayList;
import java.util.Date;

public class Lesson {
	private int lsnNo;
	private User user;
	private String lsnTitle;
	private String lsnIntro;
	private int lsnLv;
	private int lsnDays;
	private int lsnPrice;
	private int lsnPerTime;
	private int lsnCntSum;
	private int lsnStarSum;
	private int lsnStarPplCnt;
	private int lsnStatus;
	private Date lsnUploadDt;
	private ArrayList<LessonClsfc> lsnClfc;
	private ArrayList<LessonLine> lsnLines;
	private Location location;
	
	//ViewLessonReview용 toString
	public String toString() {
		return "레슨제목 : " + lsnTitle;
	}
	
	//ViewMyPage용 toString
	public String toString(String lsnTitle, int lsnCntSum) {
		return "레슨제목 : " + this.lsnTitle +
			   "레슨총횟수 : " + this.lsnCntSum ;
	}
	//게터 세터 메서드 
	//1. 레슨번호
	public int getLsnNo() {
		return this.lsnNo;
	}
	public void setLsnNo(int lsnNo) {
		this.lsnNo = lsnNo;
	}
	//2. 유저
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//3. 레슨명
	public String getLsnTitle() {
		return this.lsnTitle;
	}
	public void setLsnTitle(String lsnTitle) {
		this.lsnTitle = lsnTitle;
	}
	//4. 레슨소개 
	public String getLsnIntro() {
		return this.lsnIntro;
	}
	public void setLsnIntro(String lsnIntro) {
		this.lsnIntro = lsnIntro;
	}
	//5. 레슨레벨
	public int getLsnLv() {
		return this.lsnLv;
	}
	public void setLsnLv(int lsnLv) {
		this.lsnLv = lsnLv;
	}
	//6. 레슨유효기간
	public int getLsnDays() {
		return this.lsnDays;
	}
	public void setLsnDays(int lsnDays) {
		this.lsnDays = lsnDays;
	}
	//7. 레슨가격
	public int getLsnPrice() {
		return this.lsnPrice;
	}
	public void setLsnPrice(int lsnPrice) {
		this.lsnPrice = lsnPrice;
	}
	//8. 레슨회당시간
	public int getLsnPerTime() {
		return this.lsnPerTime;
	}
	public void setLsnPerTime(int lsnPerTime) {
		this.lsnPerTime = lsnPerTime;
	}
	//9. 레슨총횟수
	public int getLsnCntSum() {
		return this.lsnCntSum;
	}
	public void setLsnCntSum(int lsnCntSum) {
		this.lsnCntSum = lsnCntSum;
	}
	//10. 레슨총별점
	public int getLsnStarSum() {
		return this.lsnStarSum;
	}
	public void setLsnStarSum(int lsnStarSum) {
		this.lsnStarSum = lsnStarSum;
	}
	//11. 레슨별점총인원
	public int getLsnStarPplCnt() {
		return this.lsnStarPplCnt;
	}
	public void setLsnStarPplCnt(int lsnStarPplCnt) {
		this.lsnStarPplCnt = lsnStarPplCnt;
	}
	//12. 레슨상태
	public int getLsnStatus() {
		return this.lsnStatus;
	}
	public void setLsnStatus(int lsnStatus) {
		this.lsnStatus = lsnStatus;
	}
	//13. 레슨업로드일자
	public Date getLsnUploadDt() {
		return this.lsnUploadDt;
	}
	public void setLsnUploadDt(Date lsnUploadDt) {
		this.lsnUploadDt = lsnUploadDt;
	}
	//14. 레슨분류 필요?
	public ArrayList<LessonClsfc> getLsnClfc() {
		return this.lsnClfc;
	}
	public void setLsnClfc(ArrayList<LessonClsfc> lsnClfc) {
		this.lsnClfc = lsnClfc;
	}
	//15. 레슨내역 필요?
	public ArrayList<LessonLine> getLsnLines() {
		return this.lsnLines;
	}
	public void setLsnLines(ArrayList<LessonLine> lsnLines) {
		this.lsnLines = lsnLines;
	}
	//16. 지역
	public Location getLocation() {
		return this.location;
	}
	public void setLsnLocation(Location lsnLocation) {
		this.location = lsnLocation;
	}

}