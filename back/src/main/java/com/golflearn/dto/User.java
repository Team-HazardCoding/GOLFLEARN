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
	
	public String toString() {
		return userType + "입니다";
	}
	
	public String getUserID(){
		return this.userID;
	}
	public void setUserID(String userID){
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserSsn() {
		return userSsn;
	}
	public void setUserSsn(String userSsn) {
		this.userSsn = userSsn;
	}
	public Date getUserJoinDt() {
		return userJoinDt;
	}
	public void setUserJoinDt(Date userJoinDt) {
		this.userJoinDt = userJoinDt;
	}
	public Date getUserQuitDt() {
		return userQuitDt;
	}
	public void setUserQuitDt(Date userQuitDt) {
		this.userQuitDt = userQuitDt;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Pro getPro() {
		return pro;
	}
	public void setPro(Pro pro) {
		this.pro = pro;
	}
	
}
