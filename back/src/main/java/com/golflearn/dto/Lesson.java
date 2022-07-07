package com.golflearn.dto;

import java.util.ArrayList;
import java.util.Date;

public class Lesson {
	private String lsnNo;
	private String lsnTitle;
	private String lsnIntro;
	private int lsnLv;	
	private int lsnDays;
	private int lsnPrice;
	private int lsnPerTime;
	private int lsnCntSum;
	private int lsnStarSum;
	private int lsnStarPplCnt;
	private float lsnStarScore;	//레슨별점
	private float proStarScore;	//프로별점
	private int lsnStatus;
	private int UploadDt;
	private User user;
	private Date lsnUploadDt;
	private ArrayList<LessonClsfc> lsnClfc;	// 1.ArrayList로 지정할 필요가 있을까?
	private LessonLine lsnLine;
	private Location location;
	private Pro pro;
	private LessonReview lsnReview;
	
	public Lesson(){};
	
	//생성자 호출로 가져온 파라미터를 데이터 input할 시
//	public Lesson(int lsnNo, String lsnTitle, String lsnIntro, int lsnReviewCnt, int lsnLv, int lsnPrice,
//		int lsnPerTime, int lsnDays, float lsnStarScore, float proStarScore, String proName, String locSido,
//		String locSigungu, String proIntro, String reviewUserId, String review, Date reviewDt) {
//		
//		this.lsnNo = lsnNo;
//		this.lsnTitle = lsnTitle;
//		this.lsnIntro = lsnIntro;
//		this.lsnStarPplCnt = lsnReviewCnt; // 리뷰의 갯수와 별점준 인원수는 같음 
//		this.lsnLv = lsnLv;
//		this.lsnPrice = lsnPrice;
//		this.lsnPerTime = lsnPerTime;
//		this.lsnDays = lsnDays;
//		this.lsnStarScore = lsnStarScore;
//		this.proStarScore = proStarScore;
//		
//		//2.한번 타는 경우
//		this.프로명 = proName; 			// User의 userName(ui.user_name)
//		this.시도 = locSido; 	 			// Location의 locSido(lc.loc_sido)		
//		this.시군구 = locSigungu;			// Location의 locSigungu(lc.loc_sigungu)
//		this.프로소개 = proIntro;			// Pro의 proCareer(pi.pro_career)
//		this.후기작성자아이디 = reviewUserId;// LessonLine의 userId(ll.user_id)	// 두번 타는경우
//		this.후기내용 = review;			// LessonReview의 review(lr.review)
//		this.후기작성일자 = reviewDt;		// LessonReview의 reviewDt(lr.review_dt)
//		
//		this.user.setUserName(proName);
//		this.location.setLocSido(locSido);
//		this.location.setLocSigungu(locSigungu);
//		this.pro.setProCareer(proIntro);
//		this.lsnReview.setReview(review);
//		this.lsnReview.setReviewDt(reviewDt);
//		//3. 타고 타는경우
//		this.lsnLines.
//	}

	
//	public String toString(uitostring의 반환, lc, pr, ll, lr + 나머진 변수들 ) {
//		return this.lsnNo + this.lsnTitle  + " dk";
//	}
	//게터 세터 메서드 
	//1. 레슨번호
	public String getLsnNo() {
		return this.lsnNo;
	}
	public void setLsnNo(String lsnNo) {
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
	public LessonLine getLsnLine() {
		return this.lsnLine;
	}
	public void setLsnLine(LessonLine lsnLine) {
		this.lsnLine = lsnLine;
	}
	//16. 지역
	public Location getLocation() {
		return this.location;
	}
	public void setLsnLocation(Location lsnLocation) {
		this.location = lsnLocation;
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

	public int getUploadDt() {
		return UploadDt;
	}

	public void setUploadDt(int uploadDt) {
		UploadDt = uploadDt;
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

	public void setLocation(Location location) {
		this.location = location;
	}

	// 수정필요
	@Override
	public String toString() {
		return "Lesson [lsnNo=" + lsnNo + ", lsnTitle=" + lsnTitle + ", lsnIntro=" + lsnIntro + ", lsnLv=" + lsnLv
				+ ", lsnDays=" + lsnDays + ", lsnPrice=" + lsnPrice + ", lsnPerTime=" + lsnPerTime + ", lsnCntSum="
				+ lsnCntSum + ", lsnStarSum=" + lsnStarSum + ", lsnStarPplCnt=" + lsnStarPplCnt + ", lsnStarScore="
				+ lsnStarScore + ", proStarScore=" + proStarScore + ", lsnStatus=" + lsnStatus + ", UploadDt="
				+ UploadDt + ", user=" + user + ", lsnUploadDt=" + lsnUploadDt + ", lsnClfc=" + lsnClfc + ", lsnLines="
				+ lsnLine + ", location=" + location + ", pro=" + pro + ", lsnReview=" + lsnReview + "]";
	}
}
