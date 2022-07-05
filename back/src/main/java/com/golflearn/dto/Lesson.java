package com.golflearn.dto;

import java.util.ArrayList;

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
	private int UploadDt;
	private ArrayList<LessonClsfc> lsnClfc;
	private ArrayList<LessonLine> lsnLines;
	private Location location;
	
}
