package com.golflearn.dto;

import java.util.Date;

public class User {
	private String userID;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userSsn;
	private Date userJoinDt;
	private Date userQuitDt;
	private int userType;
	private Pro pro;
	
	public User(String userName) {
		this.userName = userName;
	}
	
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
}
