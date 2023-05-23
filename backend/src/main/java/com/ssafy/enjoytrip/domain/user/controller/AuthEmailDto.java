package com.ssafy.enjoytrip.domain.user.controller;

import lombok.Data;

public class AuthEmailDto {
	
	@Data
	public static class FindLoginIdRequest {
		private String email;
	}

	@Data
	public static class FindPasswordRequest {
		private String email;
		private String loginId;
	}
}
