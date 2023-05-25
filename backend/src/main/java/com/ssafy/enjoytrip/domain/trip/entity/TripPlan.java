package com.ssafy.enjoytrip.domain.trip.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ssafy.enjoytrip.domain.user.entity.User;
import com.ssafy.enjoytrip.global.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="trip_plan")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripPlan extends BaseEntity {

	private static final long serialVersionUID = 6193071227688118466L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tripPlanId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name="title", length=64)
	private String title;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tripPlan", cascade= {CascadeType.REMOVE, CascadeType.PERSIST })
	private List<TripPlanTimeLine> tripPlanTimeLine;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tripPlan", cascade= {CascadeType.REMOVE})
	private List<TripPlanGroup> tripPlanGroup;
	
	public void setTripPlanTimeLine(List<TripPlanTimeLine> tripPlanTimeLine) {
		this.tripPlanTimeLine = tripPlanTimeLine;
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updateStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
}
