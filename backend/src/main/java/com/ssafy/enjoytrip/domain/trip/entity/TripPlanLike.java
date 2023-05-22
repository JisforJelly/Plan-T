package com.ssafy.enjoytrip.domain.trip.entity;

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

@Entity(name="trip_plan_like")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripPlanLike extends BaseEntity {

	private static final long serialVersionUID = -137074419441395873L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tripPlanLikeId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripPlanId")
	private TripPlan tripPlan;
}
