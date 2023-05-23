package com.ssafy.enjoytrip.domain.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.enjoytrip.global.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="user")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	private static final long serialVersionUID = 9015626021526291845L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "login_id", unique = true)
	private String loginId;
	
	@Column(name = "password", length=1024)
	private String password;
	
	@Column(name = "last_login_at")
	private LocalDateTime lastLogin;
	
	@Column(name = "wrong_cnt")
	private int wrongCnt;
	
	@Column(name ="profile_img_path")
	private String profileImgPath;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public void setLastLogin(LocalDateTime loginTime) {
		this.lastLogin = loginTime;
	} 
	
	public void setWrongCng(int cnt) {
		this.wrongCnt = cnt;
	}
	
	public void updateName(String name) {
		this.name = name;
	}
	
	public void updateImg(String path) {
		this.profileImgPath = path;
	}
}
