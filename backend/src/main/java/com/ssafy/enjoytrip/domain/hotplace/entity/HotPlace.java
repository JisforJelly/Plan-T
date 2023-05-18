package com.ssafy.enjoytrip.domain.hotplace.entity;

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

@Entity(name="hot_place")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotPlace extends BaseEntity {
	
	private static final long serialVersionUID = -927310723758624669L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hotPlaceId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "connent")
	private String connent;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "hashTag")
	private String hashTag;
	
	@Column(name = "contentType")
	private String contentType;
}
