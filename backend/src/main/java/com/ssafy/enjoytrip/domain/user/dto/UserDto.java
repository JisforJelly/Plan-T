package com.ssafy.enjoytrip.domain.user.dto;

import lombok.Data;

public class UserDto {

    @Data
    public static class SignInRequest {
        private String loginId;
        private String password;
    }

    @Data
    public static class SignUpRequest {
        private String loginId;
        private String name;
    	private String email;
        private String password;
    }

    @Data
    public static class UpdateRequest {
        private int userId;
        private String email;
        private String phone;
    }

    @Data
    public static class UserInfoResponse {
        int userId;
        String loginId;
        String email;
        String phone;
    }
    
    @Data
    public static class AuthInfo {
    	Integer userId;
    	String loginId;
    	String name;
    	String userRole;
    	
    	public AuthInfo(int userId, String loginId, String name, String userRole) {
    		this.userId = userId;
    		this.loginId = loginId;
    		this.name = name;
    		this.userRole = userRole;
    	}
    }
}