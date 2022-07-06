package com.golflearn.dto;

import java.sql.Date;
import java.util.ArrayList;

public class Lesson {
	private int lsnNo;
	//private String lsnName;
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
	private int UploadDt;
	private ArrayList<LessonClsfc> lsnClfc;
	private ArrayList<LessonLine> lsnLines;
	private Location location;
	
	User u = new User();
	
	public Lesson(int lsnNo, String lsnName, int lsnReviewCnt, String lsnUserId, int lsnLv, int lsnPrice,
			int lsnPerTime, int lsnDays, int lsnStarScore, int proStarScore, String proName, String locSido,
			String locSigungu, String proIntro, String reviewUserId, String review, Date reviewDt) {
		this.lsnNo = lsnNo;
		//userName -> User의 get, set 완성되면 으로 User에서 가져오기
		//
	}
	
}
