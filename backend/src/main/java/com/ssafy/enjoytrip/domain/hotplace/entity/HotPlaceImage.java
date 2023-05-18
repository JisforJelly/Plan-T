package com.ssafy.enjoytrip.domain.hotplace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="hot_place_image")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hotPlaceImageId;
		
	@Column(name = "imagePath")
	private String imagePath;
	
	
}
