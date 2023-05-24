package com.ssafy.enjoytrip.domain.trip.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTimeLineItem;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTripPlan;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanDetail;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanList;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlan;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlanTimeLine;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanGroupRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanLikeRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripPlanService {
	
	private final TripPlanRepository tripPlanRepository;
	private final TripPlanGroupRepository tripPlanGroupRepository;
	private final TripPlanLikeRepository tripPlanLikeRepository;
	private final UserRepository userRepository;

	public TripPlanList getTripPlans(Pageable pageable) {
		return TripPlanList.ofPage(tripPlanRepository.findAll(pageable));
	}

	public void insertTripPlan(EditTripPlan dto, Integer userId) {
		LocalDate startDate = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		TripPlan tripPlan = TripPlan.builder()
				.title(dto.getTitle())
				.startDate(startDate)
				.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
				.build();
		
		List<TripPlanTimeLine> tripPlanTimeLine = new LinkedList<>();
		List<EditTimeLineItem> timeLineDtos = dto.getTimeLines();
		for(EditTimeLineItem plan: timeLineDtos) {
			tripPlanTimeLine.add(TripPlanTimeLine.builder()
					.orders(plan.getOrder())
					.tripPlan(tripPlan)
					.placeName(plan.getPlaceName())
					.latitude(plan.getLatitude())
					.longitude(plan.getLongitude())
					.location(plan.getLocation())
					.content(plan.getContent())
					.startDate(startDate)
					.endDate(startDate = startDate.plusDays(plan.getDay()))
					.imgPath(plan.getImgPath()) 
					.build());
		}
		
		tripPlan.setTripPlanTimeLine(tripPlanTimeLine);
		tripPlanRepository.save(tripPlan);
	}
	
	public TripPlanDetail getTripPlan(Integer tripPlanId) {
		return TripPlanDetail.from(tripPlanRepository.findById(tripPlanId).orElseThrow(IllegalArgumentException::new));
	}
	
	public void togglerTripPlanLike() {}
	
	public void inviteTripPlan() {}
	
	public void deleteTripPlan() {}
}
