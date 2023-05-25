package com.ssafy.enjoytrip.domain.user.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.enjoytrip.domain.user.entity.User;

import lombok.Builder;
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
    
    @Data
    public static class UserList {
    	private List<UserListItem> users;
    	
    	public UserList(List<UserListItem> users) {
    		this.users = users;
    	}
    	
    	public static UserList ofList(List<User> users) {
    		List<UserListItem> dtos = users
    				.stream()
    				.map(UserListItem::from)
    				.collect(Collectors.toList());
    		return new UserList(dtos);
    	}
    }
    
    @Data
    @Builder
    public static class UserListItem {
    	private Integer userId;
    	private String email;
    	private String imgPath;
    	
    	public static UserListItem from(User user) {
    		return UserListItem.builder()
    				.userId(user.getUserId())
    				.email(user.getEmail())
    				.imgPath(user.getProfileImgPath())
    				.build();
    	}
    }
}