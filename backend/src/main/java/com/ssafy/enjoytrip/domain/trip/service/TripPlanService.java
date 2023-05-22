package com.ssafy.enjoytrip.domain.trip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTripPlan;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlan;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlanTimeLine;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanGroupRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanLikeRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanTimeLineRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripPlanService {
	
	private final TripPlanRepository tripPlanRepository;
	private final TripPlanTimeLineRepository tripPlanTimeLineRepository;
	private final TripPlanGroupRepository tripPlanGroupRepository;
	private final TripPlanLikeRepository tripPlanLikeRepository;
	private final UserRepository userRepository;

	public void getTripPlans() {
		
	}
	
	public void getTripPlan() {
		
	}
	
	public void insertTripPlan(EditTripPlan dto, Integer userId) {
		
		TripPlan tripPlan = TripPlan.builder()
				.title(dto.getTitle())
				.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
				.build();
		
		// TODO : startDate endDate Parsing
		List<TripPlanTimeLine> tripPlanTimeLine = dto.getTimeLines()
				.stream().map(plan->TripPlanTimeLine.builder()
						.order(plan.getOrder())
						.tripPlan(tripPlan)
						.placeName(plan.getPlaceName())
						.latitude(plan.getLatitude())
						.longitude(plan.getLongitude())
						.location(plan.getLocation())
						.content(plan.getContent())
						.imgPath(plan.getImgPath()) 
						.build())
				.collect(Collectors.toList());
		
		tripPlan.setTripPlanTimeLine(tripPlanTimeLine);
		tripPlanRepository.save(tripPlan);
	}
	
	
	public void togglerTripPlanLike() {}
	
	public void inviteTripPlan() {}
	
	public void deleteTripPlan() {}
}
