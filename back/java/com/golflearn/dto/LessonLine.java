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
	private ArrayList<Lesson> lsn;
	private ArrayList<LessonHistory> lsnHistory;
	
}
