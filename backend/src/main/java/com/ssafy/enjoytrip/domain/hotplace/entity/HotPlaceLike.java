package com.ssafy.enjoytrip.domain.hotplace.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity(name="hot_place_like")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceLike extends BaseEntity {
	
	private static final long serialVersionUID = -3534903409653797698L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hotPlaceLikeId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hotPlaceId")
	private HotPlace hotplace;
}
