package com.ssafy.enjoytrip.domain.trip.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.enjoytrip.global.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="trip_plan_time_line")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripPlanTimeLine extends BaseEntity {

	private static final long serialVersionUID = -7264503092516700915L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tripPlanTimeLineId;
	
	@Column
	private Integer orders;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripPlanId")
	private TripPlan tripPlan;
	
	@Column(name="place_name")
	private String placeName;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "location")
	private String location;
	
	@Column(name="content", length=4096)
	private String content;
	
	@Column(name="img_path")
	private String imgPath;
	
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
}
