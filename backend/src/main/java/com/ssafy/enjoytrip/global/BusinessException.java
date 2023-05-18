package com.ssafy.enjoytrip.global;

import com.ssafy.enjoytrip.global.code.ErrorInfo;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -6191192647727628119L;
	
	private ErrorInfo info;
	
	public BusinessException(ErrorInfo info) {
		this.info = info;
	}
	
	public ErrorInfo getErrorInfo() {
		return this.info;
	}
}
