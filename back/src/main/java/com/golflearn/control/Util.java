package com.golflearn.control;

import oracle.security.crypto.core.MessageDigest;

public class Util {

	//SHA-256을 실행하는 메소드 생성
	public static String getHash(String input) { //input(입력값)이 들어왔을 때 hash값을 구해주는 것
		StringBuffer result = new StringBuffer(); //문자열을 추가하거나 변경 할 때 주로 사용하는 자료형
		MessageDigest md = MessageDigest.getInstance("SHA-256");	
		md.update(input.getBytes());
		byte bytes[] = md.diges
	}
}
