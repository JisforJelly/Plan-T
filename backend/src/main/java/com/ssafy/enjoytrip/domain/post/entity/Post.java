package com.ssafy.enjoytrip.domain.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.enjoytrip.domain.user.entity.User;
import com.ssafy.enjoytrip.global.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="post")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
	
	private static final long serialVersionUID = -8351129308510483275L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content", length=4096)
	private String content;
	
	@Column(name = "view_cnt")
	private Integer viewCnt;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public void updateTitle(String title) {
		this.title = title;
	}
	
	public void updateContent(String content) {
		this.content = content;
	}
	
	public void updateViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
}
