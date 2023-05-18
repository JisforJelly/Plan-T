package com.ssafy.enjoytrip.domain.comment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.enjoytrip.domain.post.entity.Post;
import com.ssafy.enjoytrip.domain.user.entity.User;
import com.ssafy.enjoytrip.global.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="comment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
	
	private static final long serialVersionUID = -8550429492650993513L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;
	
	@Column
	private String content;
	
	
	@ManyToOne
	@JoinColumn(name="postId")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public void updateContent(String content) {
		this.content = content;
	}
}
