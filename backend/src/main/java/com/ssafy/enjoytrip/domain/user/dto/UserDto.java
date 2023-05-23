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
    	private int userId;
    	private String loginId;
    	private String email;
    	private String phone;
    }
    
    @Data
    public static class AuthInfo {
    	private Integer userId;
    	private String loginId;
    	private String name;
    	private String userRole;
    	private String profileImgPath;
    	
    	public AuthInfo(int userId, String loginId, String name, String userRole, String profileImgPath) {
    		this.userId = userId;
    		this.loginId = loginId;
    		this.name = name;
    		this.userRole = userRole;
    		this.profileImgPath = profileImgPath;
    	}
    }
}