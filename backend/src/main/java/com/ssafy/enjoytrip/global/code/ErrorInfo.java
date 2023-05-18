package com.ssafy.enjoytrip.global.code;

public enum ErrorInfo {
	
	//User Exception
	EXIST_LOGIN_ID(520, "이미 존재하는 유저입니다."),
	IS_NOT_VALID_LOGIN(521, "아이디 또는 패스워드가 일치하지 않습니다.");
	
	private int code;
	private String message;
	
	ErrorInfo(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
}
