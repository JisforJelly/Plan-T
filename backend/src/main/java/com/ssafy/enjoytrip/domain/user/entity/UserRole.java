package com.ssafy.enjoytrip.domain.user.entity;

public enum UserRole {
	
	USER("user"),
	ADMIN("admin");
	
	private String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
}
