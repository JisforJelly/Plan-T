package com.ssafy.enjoytrip.domain.hotplace.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "connent")
	private String content;
	
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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="hotplace", cascade= { CascadeType.REMOVE, CascadeType.PERSIST })
	private List<HotPlaceImage> images;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="hotplace", cascade=CascadeType.REMOVE)
	private List<HotPlaceLike> likes;

	public void updateContent(String content) {
		this.content = content;
	}
	
	public void updateLocationInfo(String location, Double latitude, Double longitude) {
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void updateHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	
	public void updateContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setImages(List<HotPlaceImage> images) {
		this.images = images;
	}
}
